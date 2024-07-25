package net.cebularz.additioanltrialeffects.entity.animal;

import net.cebularz.additioanltrialeffects.AdditionalTrialEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.WolfVariants;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class ModWolfVariants extends WolfVariants
{
    public static final ResourceKey<WolfVariant> CUS_SPOTTED = createKey("cus_spotted");
    private static ResourceKey<WolfVariant> createKey(String pName) {
        return ResourceKey.create(Registries.WOLF_VARIANT, ResourceLocation.fromNamespaceAndPath(AdditionalTrialEffects.MOD_ID,pName));
    }

    static void register(BootstrapContext<WolfVariant> pContext, ResourceKey<WolfVariant> pKey, String pName, ResourceKey<Biome> pSpawnBiome) {
        register(pContext, pKey, pName, (HolderSet)HolderSet.direct(new Holder[]{pContext.lookup(Registries.BIOME).getOrThrow(pSpawnBiome)}));
    }

    static void register(BootstrapContext<WolfVariant> pContext, ResourceKey<WolfVariant> pKey, String pName, TagKey<Biome> pSpawnBiomes) {
        register(pContext, pKey, pName, (HolderSet)pContext.lookup(Registries.BIOME).getOrThrow(pSpawnBiomes));
    }

    static void register(BootstrapContext<WolfVariant> pContext, ResourceKey<WolfVariant> pKey, String pName, HolderSet<Biome> pSpawnBiomes) {
        ResourceLocation $$4 = ResourceLocation.fromNamespaceAndPath(AdditionalTrialEffects.MOD_ID,"entity/wolf/" + pName);
        ResourceLocation $$5 = ResourceLocation.fromNamespaceAndPath(AdditionalTrialEffects.MOD_ID,"entity/wolf/" + pName + "_tame");
        ResourceLocation $$6 = ResourceLocation.fromNamespaceAndPath(AdditionalTrialEffects.MOD_ID,"entity/wolf/" + pName + "_angry");
        pContext.register(pKey, new WolfVariant($$4, $$5, $$6, pSpawnBiomes));
    }

    public static void bootstrap(BootstrapContext<WolfVariant> pContext) {
        register(pContext, CUS_SPOTTED, "wolf_cus_spotted", Biomes.TAIGA);
    }
}
