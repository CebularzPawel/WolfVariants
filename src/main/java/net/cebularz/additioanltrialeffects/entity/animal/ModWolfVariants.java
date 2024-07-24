package net.cebularz.additioanltrialeffects.entity.animal;

import net.cebularz.additioanltrialeffects.AdditionalTrialEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.Optional;

public class ModWolfVariants {








    public ModWolfVariants() {
    }
    public static final DeferredRegister<WolfVariant> WOLF_VARIANTS
            = DeferredRegister.create(Registries.WOLF_VARIANT, AdditionalTrialEffects.MOD_ID);

    public static final ResourceLocation whitespottednormal = ResourceLocation.withDefaultNamespace("entity/wolf/" + "white_spotted");
    public static final ResourceLocation whitespottedtamed = ResourceLocation.withDefaultNamespace("entity/wolf/" + "white_spotted" + "_tame");
    public static final ResourceLocation whitespottedangry = ResourceLocation.withDefaultNamespace("entity/wolf/" + "white_spotted" + "_angry");

    public static final RegistryObject<WolfVariant> WHITE_SPOTTED = WOLF_VARIANTS.register("wolf_white_spotted",
            ()-> new WolfVariant(whitespottednormal,whitespottedtamed,whitespottedangry,Biomes.SNOWY_TAIGA));
    public static void register(IEventBus eventBus) {
        WOLF_VARIANTS.register(eventBus);
    }

}
