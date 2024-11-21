package net.cebularz.morewolfs.mixin;

import net.cebularz.morewolfs.entity.animal.ModWolfVariants;
import net.cebularz.morewolfs.util.CrossBreedingManager;
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
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mixin(Wolf.class)
public abstract class MoreWolfVarsMixin  {






    @Overwrite
    @Nullable
    public Wolf getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        // Create a new wolf for the offspring
        Wolf wolf = (Wolf) EntityType.WOLF.create(pLevel);

        // Check if both parents are golden
        if (pOtherParent instanceof Wolf otherWolf) {
            Wolf firstwolf = ((Wolf) (Object) this);
            CompoundTag firstparentnbt = firstwolf.getPersistentData();
            CompoundTag secondparentnbt = otherWolf.getPersistentData();


            String firstParentVariant =  "morewolfs:golden";
            String secondParentVariant = "morewolfs:fluffy";


            String crossbreed = CrossBreedingManager.getOffspringVariant(firstParentVariant,secondParentVariant);
            System.out.println("\nnewvariant: "+crossbreed+" parent1variant: "+firstParentVariant+ " parent2variant: "+secondParentVariant);

            //if(crossbreed!="") {

                CompoundTag nbt = new CompoundTag();
                nbt.putString("variant", crossbreed);

                // Apply the variant to the offspring
                wolf.readAdditionalSaveData(nbt);
            //}

        }


        return wolf;
    }

}