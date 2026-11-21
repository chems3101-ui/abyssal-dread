package com.abyssaldread.client.render;

import com.abyssaldread.AbyssalDread;
import com.abyssaldread.client.model.SeabedMawModel;
import com.abyssaldread.entity.SeabedMaw;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class SeabedMawRenderer extends MobRenderer<SeabedMaw, SeabedMawModel> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AbyssalDread.MODID, "textures/entity/seabed_maw.png");
    private static final ResourceLocation GLOW =
            new ResourceLocation(AbyssalDread.MODID, "textures/entity/seabed_maw_glow.png");

    public SeabedMawRenderer(EntityRendererProvider.Context context) {
        super(context, new SeabedMawModel(context.bakeLayer(SeabedMawModel.LAYER)), 1.0F);
        this.addLayer(new EyesLayer<SeabedMaw, SeabedMawModel>(this) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(GLOW);
            }
        });
    }

    @Override
    protected void scale(SeabedMaw entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SeabedMaw entity) {
        return TEXTURE;
    }
}
