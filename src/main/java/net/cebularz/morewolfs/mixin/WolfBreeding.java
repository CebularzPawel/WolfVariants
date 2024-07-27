package net.cebularz.morewolfs.mixin;

import net.cebularz.morewolfs.entity.animal.ModWolfVariants;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.WolfVariants;
import org.checkerframework.common.reflection.qual.Invoke;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Wolf.class)
public abstract class WolfBreeding {
    private static boolean isInArray(ResourceKey<WolfVariant>[] array, ResourceKey<WolfVariant> number) {
        for (ResourceKey<WolfVariant> element : array) {
            if (element.equals(number)) {
                return true;
            }
        }
        return false;
    }

    @Shadow public abstract Holder<WolfVariant> getVariant();
    @SuppressWarnings("unchecked")
    private static final ResourceKey<WolfVariant>[] WHITE_SPOTTED_PARENTS =
            (ResourceKey<WolfVariant>[]) new ResourceKey<?>[] {
                    WolfVariants.SPOTTED,
                    WolfVariants.PALE
            };
    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Wolf;",at = @At("TAIL"))
    public void getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent, CallbackInfoReturnable<Wolf> cir) {
        Wolf wolf = (Wolf)EntityType.WOLF.create(pLevel);
        System.out.println("ZERO STEP");
        if (wolf != null && pOtherParent instanceof Wolf wolf1) {
            System.out.println("FIRST STEP");
                if(wolf.getVariant()!=wolf1.getVariant()){
                    ResourceKey<WolfVariant> variantwolf =(ResourceKey<WolfVariant>) wolf.getVariant();
                    ResourceKey<WolfVariant> variantwolf1 =(ResourceKey<WolfVariant>) wolf1.getVariant();
                    System.out.println("SOMETHING BEFORE");
                    if(isInArray(WHITE_SPOTTED_PARENTS, variantwolf)&&isInArray(WHITE_SPOTTED_PARENTS, variantwolf1)){
                        Holder<WolfVariant> whitespottedvariant =(Holder<WolfVariant>) ModWolfVariants.WHITESPOTTED;

                        wolf.setVariant(whitespottedvariant);
                        System.out.println("CHANGING TYPE");
                    }
                }
            }
    }

}
