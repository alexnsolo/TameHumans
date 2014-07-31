package com.tamehumans.client.renderer.entity;

import com.tamehumans.entity.EntityHuman;
import com.tamehumans.client.model.entity.ModelHuman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


@SideOnly(Side.CLIENT)
public class RenderHuman extends RenderLiving {

    private static final ResourceLocation steveTexture = new ResourceLocation("examplemod:textures/entity/warrior.png");

    public RenderHuman()
    {
        super(new ModelHuman(), 0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHuman p_110775_1_)
    {
        return steveTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityHuman)p_110775_1_);
    }
}
