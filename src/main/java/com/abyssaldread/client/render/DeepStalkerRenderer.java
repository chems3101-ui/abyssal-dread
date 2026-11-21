package com.abyssaldread.client.render;

import com.abyssaldread.AbyssalDread;
import com.abyssaldread.client.model.DeepStalkerModel;
import com.abyssaldread.entity.DeepStalker;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class DeepStalkerRenderer extends MobRenderer<DeepStalker, DeepStalkerModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AbyssalDread.MODID, "textures/entity/deep_stalker.png");
    private static final ResourceLocation GLOW =
            new ResourceLocation(AbyssalDread.MODID, "textures/entity/deep_stalker_glow.png");

    public DeepStalkerRenderer(EntityRendererProvider.Context context) {
        super(context, new DeepStalkerModel(context.bakeLayer(DeepStalkerModel.LAYER)), 0.6F);
        this.addLayer(new EyesLayer<DeepStalker, DeepStalkerModel>(this) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(GLOW);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(DeepStalker entity) {
        return TEXTURE;
    }
}
