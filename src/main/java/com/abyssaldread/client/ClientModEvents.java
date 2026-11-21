package com.abyssaldread.client;

import com.abyssaldread.AbyssalDread;
import com.abyssaldread.client.model.DeepStalkerModel;
import com.abyssaldread.client.model.SeabedMawModel;
import com.abyssaldread.client.render.DeepStalkerRenderer;
import com.abyssaldread.client.render.LanternDrownedRenderer;
import com.abyssaldread.client.render.SeabedMawRenderer;
import com.abyssaldread.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientModEvents {

    @Mod.EventBusSubscriber(modid = AbyssalDread.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModBus {
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.LANTERN_DROWNED.get(), LanternDrownedRenderer::new);
            event.registerEntityRenderer(ModEntities.DEEP_STALKER.get(), DeepStalkerRenderer::new);
            event.registerEntityRenderer(ModEntities.SEABED_MAW.get(), SeabedMawRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(DeepStalkerModel.LAYER, DeepStalkerModel::createBodyLayer);
            event.registerLayerDefinition(SeabedMawModel.LAYER, SeabedMawModel::createBodyLayer);
        }
    }

    /** Shader sous-marin : actif dès que les yeux du joueur sont dans l'eau. */
    @Mod.EventBusSubscriber(modid = AbyssalDread.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class AbyssShader {
        private static final ResourceLocation CHAIN =
                new ResourceLocation(AbyssalDread.MODID, "shaders/post/abyss.json");

        private static boolean isOurs(PostChain chain) {
            return chain != null && chain.getName().equals(CHAIN.toString());
        }

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase != TickEvent.Phase.END) {
                return;
            }
            Minecraft mc = Minecraft.getInstance();
            boolean underwater = mc.player != null && mc.level != null
                    && mc.player.isEyeInFluid(FluidTags.WATER);
            PostChain current = mc.gameRenderer.currentEffect();

            if (underwater && current == null) {
                mc.gameRenderer.loadEffect(CHAIN);
            } else if (!underwater && isOurs(current)) {
                mc.gameRenderer.shutdownEffect();
            }
        }

        @SubscribeEvent
        public static void onRenderTick(TickEvent.RenderTickEvent event) {
            if (event.phase != TickEvent.Phase.START) {
                return;
            }
            Minecraft mc = Minecraft.getInstance();
            PostChain current = mc.gameRenderer.currentEffect();
            if (mc.level != null && isOurs(current)) {
                float seconds = ((mc.level.getGameTime() % 6000L) + event.renderTickTime) / 20.0F;
                current.setUniform("GameTime", seconds);
            }
        }
    }
}
