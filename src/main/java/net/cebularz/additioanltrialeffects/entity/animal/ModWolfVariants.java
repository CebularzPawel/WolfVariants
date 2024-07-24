package net.cebularz.additioanltrialeffects.entity.animal;

import net.cebularz.additioanltrialeffects.AdditionalTrialEffects;
import net.cebularz.additioanltrialeffects.effect.ExplosionHazard;
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
    public static final DeferredRegister<WolfVariant> WOLF_VARIANTS
            = DeferredRegister.create(Registries.WOLF_VARIANT, AdditionalTrialEffects.MOD_ID);
    public static final ResourceKey<WolfVariant> WHITESPOTTED = createKey("white_spotted");
    private static ResourceKey<WolfVariant> createKey(String pName) {
        return ResourceKey.create(Registries.WOLF_VARIANT, ResourceLocation.withDefaultNamespace(pName));
    }
    public static final RegistryObject<MobEffect> WHITE_SPOTTED = WOLF_VARIANTS.register("explosive_hazard",
            ()-> new WolfVariant(0,WHITESPOTTED,"wolf_white_spotted",Biomes.SNOWY_TAIGA);
    public static void register(IEventBus eventBus) {
        WOLF_VARIANTS.register(eventBus);
    }

}
