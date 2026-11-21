package com.abyssaldread.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * La Gueule du Fond : une baudroie colossale, lente mais lourde.
 * Sa lueur attire les proies : toutes les 3 secondes, elle aspire sa cible vers sa gueule.
 */
public class SeabedMaw extends AbyssalSwimmer {

    public SeabedMaw(EntityType<? extends AbyssalSwimmer> type, Level level) {
        super(type, level);
        this.xpReward = 30;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 70.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            LivingEntity target = this.getTarget();
            if (target != null && this.tickCount % 60 == 0 && target.isInWater()) {
                double dist = this.distanceToSqr(target);
                if (dist > 16.0D && dist < 324.0D) {
                    Vec3 pull = this.position().subtract(target.position()).normalize().scale(0.6D);
                    target.setDeltaMovement(target.getDeltaMovement().add(pull));
                    target.hurtMarked = true;
                    this.playSound(SoundEvents.ELDER_GUARDIAN_CURSE, 2.0F, 0.6F);
                }
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ELDER_GUARDIAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ELDER_GUARDIAN_DEATH;
    }
}
