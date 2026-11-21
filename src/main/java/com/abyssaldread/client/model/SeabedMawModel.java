package com.abyssaldread.client.model;

import com.abyssaldread.AbyssalDread;
import com.abyssaldread.entity.SeabedMaw;
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

/** Baudroie abyssale : corps massif, grande gueule dentée, leurre lumineux. Texture 64x64. */
public class SeabedMawModel extends EntityModel<SeabedMaw> {
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation(AbyssalDread.MODID, "seabed_maw"), "main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart upperJaw;
    private final ModelPart lowerJaw;
    private final ModelPart rod;
    private final ModelPart bulb;
    private final ModelPart tail;
    private final ModelPart finL;
    private final ModelPart finR;

    public SeabedMawModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.upperJaw = body.getChild("upper_jaw");
        this.lowerJaw = body.getChild("lower_jaw");
        this.rod = body.getChild("rod");
        this.bulb = rod.getChild("bulb");
        this.tail = body.getChild("tail");
        this.finL = body.getChild("fin_l");
        this.finR = body.getChild("fin_r");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootDef = mesh.getRoot();

        PartDefinition body = rootDef.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.5F, -6.0F, 10.0F, 9.0F, 12.0F),
                PartPose.offset(0.0F, 16.0F, 0.0F));

        body.addOrReplaceChild("upper_jaw",
                CubeListBuilder.create()
                        .texOffs(0, 22).addBox(-5.0F, -1.5F, -8.0F, 10.0F, 3.0F, 8.0F)
                        .texOffs(30, 46).addBox(-4.5F, 1.5F, -7.5F, 1.0F, 2.0F, 1.0F)
                        .addBox(-1.5F, 1.5F, -7.5F, 1.0F, 2.0F, 1.0F)
                        .addBox(0.5F, 1.5F, -7.5F, 1.0F, 2.0F, 1.0F)
                        .addBox(3.5F, 1.5F, -7.5F, 1.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 1.0F, -6.0F));

        body.addOrReplaceChild("lower_jaw",
                CubeListBuilder.create()
                        .texOffs(0, 34).addBox(-5.0F, -1.5F, -8.0F, 10.0F, 3.0F, 8.0F)
                        .texOffs(30, 46).addBox(-4.5F, -3.5F, -7.5F, 1.0F, 2.0F, 1.0F)
                        .addBox(-1.5F, -3.5F, -7.5F, 1.0F, 2.0F, 1.0F)
                        .addBox(0.5F, -3.5F, -7.5F, 1.0F, 2.0F, 1.0F)
                        .addBox(3.5F, -3.5F, -7.5F, 1.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 3.0F, -6.0F));

        PartDefinition rod = body.addOrReplaceChild("rod",
                CubeListBuilder.create().texOffs(40, 22).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 6.0F, 1.0F),
                PartPose.offset(0.0F, -4.5F, -4.0F));
        rod.addOrReplaceChild("bulb",
                CubeListBuilder.create().texOffs(48, 22).addBox(-1.5F, -3.0F, -1.5F, 3.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, -6.0F, 0.0F));

        body.addOrReplaceChild("tail",
                CubeListBuilder.create().texOffs(40, 34).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 6.0F));

        body.addOrReplaceChild("fin_l",
                CubeListBuilder.create().texOffs(0, 46).addBox(0.0F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F),
                PartPose.offset(5.0F, 1.0F, -1.0F));
        body.addOrReplaceChild("fin_r",
                CubeListBuilder.create().texOffs(14, 46).addBox(-1.0F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F),
                PartPose.offset(-5.0F, 1.0F, -1.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(SeabedMaw entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.xRot = headPitch * ((float) Math.PI / 180F);

        float open = entity.isAggressive() ? 0.55F + 0.2F * (float) Math.sin(ageInTicks * 0.4F) : 0.10F;
        this.lowerJaw.xRot = open;
        this.upperJaw.xRot = -open * 0.5F;

        this.rod.xRot = 0.5F + (float) Math.sin(ageInTicks * 0.08F) * 0.12F;
        this.bulb.xRot = (float) Math.sin(ageInTicks * 0.11F) * 0.3F;

        this.tail.yRot = (float) Math.sin(ageInTicks * 0.15F) * 0.4F;
        float flap = (float) Math.sin(ageInTicks * 0.2F) * 0.35F;
        this.finL.zRot = flap;
        this.finR.zRot = -flap;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int light, int overlay,
                               float r, float g, float b, float a) {
        this.root.render(poseStack, buffer, light, overlay, r, g, b, a);
    }
}
