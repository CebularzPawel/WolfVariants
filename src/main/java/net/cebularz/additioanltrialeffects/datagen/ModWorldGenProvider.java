package net.cebularz.additioanltrialeffects.datagen;

import net.cebularz.additioanltrialeffects.AdditionalTrialEffects;
import net.cebularz.additioanltrialeffects.entity.animal.ModWolfVariants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider
{
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.WOLF_VARIANT, ModWolfVariants::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AdditionalTrialEffects.MOD_ID));}
}
