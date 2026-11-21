package com.abyssaldread;

import com.abyssaldread.entity.AbyssalSwimmer;
import com.abyssaldread.entity.DeepStalker;
import com.abyssaldread.entity.LanternDrowned;
import com.abyssaldread.entity.SeabedMaw;
import com.abyssaldread.registry.ModEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AbyssalDread.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void onAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LANTERN_DROWNED.get(), LanternDrowned.createAttributes().build());
        event.put(ModEntities.DEEP_STALKER.get(), DeepStalker.createAttributes().build());
        event.put(ModEntities.SEABED_MAW.get(), SeabedMaw.createAttributes().build());
    }

    @SubscribeEvent
    public static void onSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.LANTERN_DROWNED.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.OCEAN_FLOOR, AbyssalSwimmer::checkAbyssSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
        event.register(ModEntities.DEEP_STALKER.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.OCEAN_FLOOR, AbyssalSwimmer::checkAbyssSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
        event.register(ModEntities.SEABED_MAW.get(), SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.OCEAN_FLOOR, AbyssalSwimmer::checkAbyssSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
    }
}
