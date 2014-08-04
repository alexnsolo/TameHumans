package com.tamehumans.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderHumanBase extends RenderBiped {

    private static final ResourceLocation texture = new ResourceLocation("tamehumans:textures/entity/steve.png");

    public RenderHumanBase() {
        super(new ModelBiped(), 0);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }
}
