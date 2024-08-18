package net.cebularz.morewolfs.entity.animal;

import net.cebularz.morewolfs.MoreWolfs;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.WolfVariants;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class ModWolfVariants extends WolfVariants
{
    public static final ResourceKey<WolfVariant> WHITESPOTTED = createKey("white_spotted");
    public static final ResourceKey<WolfVariant> MOUNTAIN = createKey("mountain");

    public static final ResourceKey<WolfVariant> GOLDEN = createKey("golden");

    public static final ResourceKey<WolfVariant> PATCH = createKey("patch");

    public static final ResourceKey<WolfVariant> FLUFFY = createKey("fluffy");

    public static final ResourceKey<WolfVariant> WHITE_FLUFFY = createKey("white_fluffy");

    public static final ResourceKey<WolfVariant> BLACK_FLUFFY = createKey("black_fluffy");


    public static final ResourceKey<WolfVariant> GINGER = createKey("ginger");

    public static final ResourceKey<WolfVariant> TRICOLOR = createKey("tricolor");



    private static ResourceKey<WolfVariant> createKey(String pName) {
        return ResourceKey.create(Registries.WOLF_VARIANT, ResourceLocation.fromNamespaceAndPath(MoreWolfs.MOD_ID,pName));
    }

    static void register(BootstrapContext<WolfVariant> pContext, ResourceKey<WolfVariant> pKey, String pName, ResourceKey<Biome> pSpawnBiome) {
        register(pContext, pKey, pName, (HolderSet)HolderSet.direct(new Holder[]{pContext.lookup(Registries.BIOME).getOrThrow(pSpawnBiome)}));
    }

    static void register(BootstrapContext<WolfVariant> pContext, ResourceKey<WolfVariant> pKey, String pName, TagKey<Biome> pSpawnBiomes) {
        register(pContext, pKey, pName, (HolderSet)pContext.lookup(Registries.BIOME).getOrThrow(pSpawnBiomes));
    }

    static void register(BootstrapContext<WolfVariant> pContext, ResourceKey<WolfVariant> pKey, String pName, HolderSet<Biome> pSpawnBiomes) {
        ResourceLocation $$4 = ResourceLocation.fromNamespaceAndPath(MoreWolfs.MOD_ID,"entity/wolf/" + pName);
        ResourceLocation $$5 = ResourceLocation.fromNamespaceAndPath(MoreWolfs.MOD_ID,"entity/wolf/" + pName + "_tame");
        ResourceLocation $$6 = ResourceLocation.fromNamespaceAndPath(MoreWolfs.MOD_ID,"entity/wolf/" + pName + "_angry");
        pContext.register(pKey, new WolfVariant($$4, $$5, $$6, pSpawnBiomes));
    }

    public static void bootstrap(BootstrapContext<WolfVariant> pContext) {
        register(pContext, WHITESPOTTED, "wolf_white_spotted", Biomes.STONY_SHORE);
        register(pContext, MOUNTAIN, "wolf_mountain", Biomes.MEADOW);
        register(pContext, GOLDEN, "wolf_golden", Biomes.FLOWER_FOREST);
        register(pContext, PATCH, "wolf_patch", Biomes.PLAINS);
        register(pContext, FLUFFY, "wolf_fluffy", Biomes.BIRCH_FOREST);
        register(pContext, WHITE_FLUFFY, "wolf_white_fluffy", Biomes.SNOWY_PLAINS);
        register(pContext, BLACK_FLUFFY, "wolf_black_fluffy", Biomes.DARK_FOREST);
        register(pContext, GINGER, "wolf_ginger", Biomes.CHERRY_GROVE);
        register(pContext, TRICOLOR, "wolf_tricolor", Biomes.SUNFLOWER_PLAINS);

    }
}
