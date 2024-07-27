package net.cebularz.morewolfs.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.WolfVariant;

public interface IWolfVariants
{
    ResourceLocation wolfVariants$getVariant();

    Holder<WolfVariant> wolfVariants$getVariantHolder();

    void wolfVariants$setWolfVariant(Holder<WolfVariant> newVariant);
}
