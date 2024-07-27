package net.cebularz.morewolfs.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.WolfVariant;

public interface IWolfVariants
{
    ResourceLocation getVariant();

    Holder<WolfVariant> getVariantHolder();

    void setWolfVariant(Holder<WolfVariant> newVariant);
}
