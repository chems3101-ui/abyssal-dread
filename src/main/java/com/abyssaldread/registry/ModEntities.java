package com.abyssaldread.registry;

import com.abyssaldread.AbyssalDread;
import com.abyssaldread.entity.DeepStalker;
import com.abyssaldread.entity.LanternDrowned;
import com.abyssaldread.entity.SeabedMaw;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AbyssalDread.MODID);

    public static final RegistryObject<EntityType<LanternDrowned>> LANTERN_DROWNED =
            ENTITIES.register("lantern_drowned", () ->
                    EntityType.Builder.of(LanternDrowned::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F).clientTrackingRange(10)
                            .build(AbyssalDread.MODID + ":lantern_drowned"));

    public static final RegistryObject<EntityType<DeepStalker>> DEEP_STALKER =
            ENTITIES.register("deep_stalker", () ->
                    EntityType.Builder.of(DeepStalker::new, MobCategory.MONSTER)
                            .sized(1.1F, 0.9F).clientTrackingRange(10)
                            .build(AbyssalDread.MODID + ":deep_stalker"));

    public static final RegistryObject<EntityType<SeabedMaw>> SEABED_MAW =
            ENTITIES.register("seabed_maw", () ->
                    EntityType.Builder.of(SeabedMaw::new, MobCategory.MONSTER)
                            .sized(1.9F, 1.6F).clientTrackingRange(10)
                            .build(AbyssalDread.MODID + ":seabed_maw"));
}
