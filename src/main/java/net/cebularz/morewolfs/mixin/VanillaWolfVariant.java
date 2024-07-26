package net.cebularz.morewolfs.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Wolf.class)
public abstract class VanillaWolfVariant
{
    @Shadow
    public abstract void setVariant(Holder<WolfVariant> p_332660_);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(EntityType pEntityType, Level pLevel, CallbackInfo ci) {
        if (!((Wolf) (Object) this).getPersistentData().contains("CustomVariant")) {
            RandomSource rand = RandomSource.create();
            Holder<WolfVariant> vanillaVariant = pLevel.registryAccess().registryOrThrow(Registries.WOLF_VARIANT)
                    .getRandom(rand).orElseThrow();
            this.setVariant(vanillaVariant);
        }
    }
}
