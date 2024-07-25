package net.cebularz.additioanltrialeffects.client.renderer;

import net.cebularz.additioanltrialeffects.util.IWolfVariants;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import org.jetbrains.annotations.NotNull;

public class ModWolfRenderer extends WolfRenderer
{

    public ModWolfRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Wolf pEntity) {
        if (pEntity instanceof IWolfVariants) {
            return ((IWolfVariants) pEntity).getVariant();
        }
        return super.getTextureLocation(pEntity);
    }
}
