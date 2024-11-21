package net.cebularz.morewolfs.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "morewolfs")
public class CrossBreedingManager {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<String, Map<String, String>> crossbreedingRules = new HashMap<>();

    // Load the crossbreeding rules when the mod is initialized
    public static void loadCrossbreedingRules() {
        try {
            // Define the path to your JSON file inside the resources folder
            ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath("morewolfs", "data/morewolfs/crossbreeds/crossbreeding_rules.json");

            // Use Minecraft's ResourceManager to load the file from your mod's resources
            InputStreamReader reader = new InputStreamReader(
                    Minecraft.getInstance().getResourceManager()
                            .getResource(resourceLocation)
                            .orElseThrow(() -> new IllegalArgumentException("File not found"))
                            .open()
            );

            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            // Parse the crossbreeding rules
            JsonObject rules = json.getAsJsonObject("crossbreeding_rules");
            Gson gson = new Gson();
            for (String parent1 : rules.keySet()) {
                Map<String, String> offspringVariants = new HashMap<>();
                JsonObject parent1Rules = rules.getAsJsonObject(parent1);
                for (String parent2 : parent1Rules.keySet()) {
                    offspringVariants.put(parent2, parent1Rules.get(parent2).getAsString());
                }
                crossbreedingRules.put(parent1, offspringVariants);
            }
            LOGGER.info("Crossbreeding rules loaded successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to load crossbreeding rules from JSON", e);
        }
    }

    public static String getOffspringVariant(String parent1, String parent2) {
        // Check if the parent1 variant exists in the crossbreeding rules
        if (crossbreedingRules.containsKey(parent1)) {
            // Check if the parent2 variant exists within the parent1's crossbreeding rules
            if (crossbreedingRules.get(parent1).containsKey(parent2)) {
                // Return the offspring variant if both parents are found in the rules
                return crossbreedingRules.get(parent1).get(parent2);
            }
        }

        // Return an empty string if no valid rule is found
        return "";
    }
}
