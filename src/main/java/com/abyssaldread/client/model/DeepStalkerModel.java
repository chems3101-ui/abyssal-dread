package com.abyssaldread.client.model;

import com.abyssaldread.AbyssalDread;
import com.abyssaldread.entity.DeepStalker;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

/** Anguille géante à 4 segments, mâchoire articulée. Texture 64x64. */
public class DeepStalkerModel extends EntityModel<DeepStalker> {
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation(AbyssalDread.MODID, "deep_stalker"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart jaw;
    private final ModelPart s1;
    private final ModelPart s2;
    private final ModelPart s3;
    private final ModelPart s4;

    public DeepStalkerModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        ModelPart head = body.getChild("head");
        this.jaw = head.getChild("jaw");
        this.s1 = body.getChild("s1");
        this.s2 = s1.getChild("s2");
        this.s3 = s2.getChild("s3");
        this.s4 = s3.getChild("s4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootDef = mesh.getRoot();

        PartDefinition body = rootDef.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -8.0F, 6.0F, 5.0F, 8.0F),
                PartPose.ZERO);
        head.addOrReplaceChild("jaw",
                CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F),
                PartPose.offset(0.0F, 2.5F, -1.0F));

        PartDefinition s1 = body.addOrReplaceChild("s1",
                CubeListBuilder.create()
                        .texOffs(28, 0).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 7.0F)
                        .texOffs(36, 34).addBox(-0.5F, -6.5F, -1.0F, 1.0F, 4.0F, 10.0F),
                PartPose.ZERO);
        PartDefinition s2 = s1.addOrReplaceChild("s2",
                CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F),
                PartPose.offset(0.0F, 0.0F, 7.0F));
        PartDefinition s3 = s2.addOrReplaceChild("s3",
                CubeListBuilder.create().texOffs(24, 22).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 7.0F),
                PartPose.offset(0.0F, 0.0F, 7.0F));
        s3.addOrReplaceChild("s4",
                CubeListBuilder.create()
                        .texOffs(0, 34).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 7.0F)
                        .texOffs(20, 34).addBox(-0.5F, -3.0F, 3.0F, 1.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 7.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(DeepStalker entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        float t = ageInTicks * 0.25F;
        float speedBoost = 1.0F + Math.min(limbSwingAmount, 1.0F) * 1.5F;
        t *= speedBoost;

        this.body.xRot = headPitch * ((float) Math.PI / 180F);
        this.s1.yRot = (float) Math.sin(t) * 0.20F;
        this.s2.yRot = (float) Math.sin(t - 0.9F) * 0.32F;
        this.s3.yRot = (float) Math.sin(t - 1.8F) * 0.45F;
        this.s4.yRot = (float) Math.sin(t - 2.7F) * 0.55F;

        float open = entity.isAggressive() ? 0.55F + 0.2F * (float) Math.sin(ageInTicks * 0.5F) : 0.12F;
        this.jaw.xRot = open;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int light, int overlay,
                               float r, float g, float b, float a) {
        this.root.render(poseStack, buffer, light, overlay, r, g, b, a);
    }
}
