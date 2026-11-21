package com.abyssaldread;

import com.abyssaldread.registry.ModEntities;
import com.abyssaldread.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AbyssalDread.MODID)
public class AbyssalDread {
    public static final String MODID = "abyssaldread";

    public AbyssalDread() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITIES.register(bus);
        ModItems.ITEMS.register(bus);
        bus.addListener(this::buildCreativeTabs);
    }

    private void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.LANTERN_DROWNED_SPAWN_EGG);
            event.accept(ModItems.DEEP_STALKER_SPAWN_EGG);
            event.accept(ModItems.SEABED_MAW_SPAWN_EGG);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.ABYSSAL_PEARL);
        }
    }
}
