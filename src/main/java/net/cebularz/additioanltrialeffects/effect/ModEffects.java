package net.cebularz.additioanltrialeffects.effect;

import net.cebularz.additioanltrialeffects.AdditionalTrialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AdditionalTrialEffects.MOD_ID);

    public static final RegistryObject<MobEffect> EXPLOSIVE_HAZARD = MOB_EFFECTS.register("explosive_hazard",
            ()-> new ExplosionHazard(MobEffectCategory.HARMFUL,1396919));
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
