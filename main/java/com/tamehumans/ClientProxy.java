package com.tamehumans;

import com.tamehumans.client.renderer.entity.RenderHuman;
import com.tamehumans.entity.EntityHuman;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, new RenderHuman());
    }

    @Override
    public void registerSound() {
    }
}