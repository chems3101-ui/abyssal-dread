package com.abyssaldread.registry;

import com.abyssaldread.AbyssalDread;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AbyssalDread.MODID);

    public static final RegistryObject<Item> ABYSSAL_PEARL =
            ITEMS.register("abyssal_pearl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LANTERN_DROWNED_SPAWN_EGG =
            ITEMS.register("lantern_drowned_spawn_egg", () ->
                    new ForgeSpawnEggItem(ModEntities.LANTERN_DROWNED, 0x16343a, 0xffd36b, new Item.Properties()));

    public static final RegistryObject<Item> DEEP_STALKER_SPAWN_EGG =
            ITEMS.register("deep_stalker_spawn_egg", () ->
                    new ForgeSpawnEggItem(ModEntities.DEEP_STALKER, 0x050d17, 0x38f2ff, new Item.Properties()));

    public static final RegistryObject<Item> SEABED_MAW_SPAWN_EGG =
            ITEMS.register("seabed_maw_spawn_egg", () ->
                    new ForgeSpawnEggItem(ModEntities.SEABED_MAW, 0x1b0f1f, 0x7dff9e, new Item.Properties()));
}
