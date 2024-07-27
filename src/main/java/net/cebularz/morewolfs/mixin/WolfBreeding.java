package net.cebularz.morewolfs.mixin;

import net.cebularz.morewolfs.entity.animal.ModWolfVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.WolfVariants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(Wolf.class)
public abstract class WolfBreeding {

    @Shadow public abstract Holder<WolfVariant> getVariant();

    private static final List<ResourceKey<WolfVariant>> WHITE_SPOTTED_PARENTS = Arrays.asList(
            WolfVariants.SPOTTED.unwrapKey().orElseThrow(),
            WolfVariants.PALE.unwrapKey().orElseThrow()
    );

    private static boolean isInArray(ResourceKey<WolfVariant>[] array, ResourceKey<WolfVariant> key) {
        for (ResourceKey<WolfVariant> element : array) {
            if (element.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Inject(method = "getBreedOffspring", at = @At("HEAD"), cancellable = true)
    public void getBreedOffspring(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<Wolf> cir) {
        if (otherParent instanceof Wolf wolf1) {
            Holder<WolfVariant> variantHolder = this.getVariant();
            Holder<WolfVariant> variantHolder1 = wolf1.getVariant();

            ResourceKey<WolfVariant> variant = variantHolder.unwrapKey().orElseThrow();
            ResourceKey<WolfVariant> variant1 = variantHolder1.unwrapKey().orElseThrow();

            if (!variant.equals(variant1)) {
                if (isInArray(WHITE_SPOTTED_PARENTS, variant) && isInArray(WHITE_SPOTTED_PARENTS, variant1)) {
                    Wolf offspring = EntityType.WOLF.create(level);
                    if (offspring != null) {
                        Holder<WolfVariant> whitespottedVariant = level.registryAccess()
                                .registryOrThrow(Registries.WOLF_VARIANT)
                                .getHolderOrThrow(ModWolfVariants.WHITESPOTTED.unwrapKey().orElseThrow());
                        offspring.setVariant(whitespottedVariant);
                        cir.setReturnValue(offspring);
                        cir.cancel();
                    }
                }
            }
        }
    }
}