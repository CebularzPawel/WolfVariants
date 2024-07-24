package net.cebularz.additioanltrialeffects.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ExplosionHazard extends MobEffect {

    protected ExplosionHazard(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor, ParticleTypes.EXPLOSION);

    }

    @Override
    public void onMobRemoved(LivingEntity pLivingEntity, int pAmplifier, Entity.RemovalReason pReason) {
        if (pReason == Entity.RemovalReason.KILLED) {
            pLivingEntity.level().explode(pLivingEntity,pLivingEntity.getX(), pLivingEntity.getY() + 0.5, pLivingEntity.getZ(),2.5f, Level.ExplosionInteraction.NONE);
        }
    }
}
