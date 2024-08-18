package net.cebularz.morewolfs.mixin;

import net.cebularz.morewolfs.entity.animal.ModWolfVariants;
import net.cebularz.morewolfs.util.IWolfVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.WolfVariants;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Wolf.class)
public abstract class MoreWolfVarsMixin implements IWolfVariants {

    @Unique
    private Holder<WolfVariant> currentVariantHolder;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(EntityType<?> type, Level level, CallbackInfo ci) {
        if (this.currentVariantHolder == null) {
            Random rand = new Random();
            Holder<WolfVariant>[] variants = new Holder[]{
                    level.registryAccess().registryOrThrow(Registries.WOLF_VARIANT).getHolderOrThrow(ModWolfVariants.WHITESPOTTED)
            };
            this.currentVariantHolder = variants[rand.nextInt(variants.length)];
            this.setWolfVariant(this.currentVariantHolder);
            ((Wolf)(Object)this).getPersistentData().putBoolean("CustomVariant", true);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (this.currentVariantHolder != null) {
            compound.putString("Variant", this.currentVariantHolder.unwrapKey().orElseThrow().location().toString());
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void readAdditionalSaveData(CompoundTag pCompound, CallbackInfo ci) {
        if (pCompound.contains("Variant")) {
            Level level = ((Wolf) (Object) this).getCommandSenderWorld();
            ResourceLocation variantLocation = ResourceLocation.parse(pCompound.getString("Variant"));
            this.currentVariantHolder = level.registryAccess().registryOrThrow(Registries.WOLF_VARIANT)
                    .getHolder(ResourceKey.create(Registries.WOLF_VARIANT, variantLocation)).orElseThrow();
            this.setWolfVariant(this.currentVariantHolder);
        }
    }




    @Override
    public ResourceLocation getVariant() {
        return this.currentVariantHolder.unwrapKey().orElseThrow().location();
    }

    @Override
    public void setWolfVariant(Holder<WolfVariant> variant) {
        this.currentVariantHolder = variant;
        this.setVariant(variant);
    }

    @Unique
    @Override
    public Holder<WolfVariant> getVariantHolder() {
        return this.currentVariantHolder;
    }

    @Shadow
    public abstract void setVariant(Holder<WolfVariant> p_332660_);
}
