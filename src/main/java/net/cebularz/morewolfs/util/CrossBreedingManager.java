package net.cebularz.morewolfs.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = "morewolfs", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CrossBreedingManager extends SimpleJsonResourceReloadListener {

    private static final Map<String, Map<String, String>> CROSSBREED_RULES = new HashMap<>();

    public CrossBreedingManager() {
        super(new com.google.gson.Gson(), "crossbreeds");
    }


    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        CROSSBREED_RULES.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : resourceLocationJsonElementMap.entrySet()) {
            JsonObject rootObject = GsonHelper.convertToJsonObject(entry.getValue(), "crossbreeding_rules");
            JsonObject crossbreedingRules = GsonHelper.getAsJsonObject(rootObject, "crossbreeding_rules");

            for (String parent1 : crossbreedingRules.keySet()) {
                JsonObject pairs = crossbreedingRules.getAsJsonObject(parent1);

                Map<String, String> pairMap = new HashMap<>();
                for (String parent2 : pairs.keySet()) {
                    String result = pairs.get(parent2).getAsString();
                    pairMap.put(parent2, result);
                }

                CROSSBREED_RULES.put(parent1, pairMap);
            }
        }

        System.out.println("Loaded crossbreed rules: " + CROSSBREED_RULES);
    }


    @SubscribeEvent
    public static void onRegisterReloadListener(AddReloadListenerEvent event) {
        event.addListener(new CrossBreedingManager());
    }

    // Method to get the crossbreed result
    public static String getCrossbreedResult(String parent1, String parent2) {
        if (CROSSBREED_RULES.containsKey(parent1) && CROSSBREED_RULES.get(parent1).containsKey(parent2)) {
            return CROSSBREED_RULES.get(parent1).get(parent2);
        }
        if (CROSSBREED_RULES.containsKey(parent2) && CROSSBREED_RULES.get(parent2).containsKey(parent1)) {
            return CROSSBREED_RULES.get(parent2).get(parent1);
        }
        return null; // No match found
    }
}
