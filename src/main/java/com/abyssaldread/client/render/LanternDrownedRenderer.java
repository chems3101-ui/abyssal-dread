package com.abyssaldread.client.render;

import com.abyssaldread.AbyssalDread;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;

public class LanternDrownedRenderer extends DrownedRenderer {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AbyssalDread.MODID, "textures/entity/lantern_drowned.png");
    private static final ResourceLocation GLOW =
            new ResourceLocation(AbyssalDread.MODID, "textures/entity/lantern_drowned_glow.png");

    public LanternDrownedRenderer(EntityRendererProvider.Context context) {
        super(context);
        // On retire la couche extérieure du noyé vanilla pour garder notre propre texture.
        this.layers.removeIf(layer -> layer instanceof DrownedOuterLayer);
        this.addLayer(new EyesLayer<Drowned, DrownedModel<Drowned>>(this) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(GLOW);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        return TEXTURE;
    }
}
