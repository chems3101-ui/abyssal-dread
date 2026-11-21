package com.abyssaldread.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

/**
 * Le Noyé-Lanterne : un noyé dont la lanterne attire les marins.
 * Toutes les 5 secondes, sa lanterne éclate d'une lumière aveuglante.
 */
public class LanternDrowned extends Drowned {

    public LanternDrowned(EntityType<? extends Drowned> type, Level level) {
        super(type, level);
        this.xpReward = 12;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MAX_HEALTH, 36.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide) {
            if (this.isInWater() && this.random.nextInt(4) == 0) {
                this.level().addParticle(ParticleTypes.GLOW,
                        this.getRandomX(0.6D), this.getY() + 1.2D, this.getRandomZ(0.6D), 0.0D, 0.01D, 0.0D);
            }
        } else {
            LivingEntity target = this.getTarget();
            if (target != null && this.tickCount % 100 == 0
                    && this.distanceToSqr(target) < 100.0D && this.hasLineOfSight(target)) {
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 50, 0), this);
                this.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE, 1.5F, 0.5F);
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hit = super.doHurtTarget(target);
        if (hit && target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1), this);
        }
        return hit;
    }
}
