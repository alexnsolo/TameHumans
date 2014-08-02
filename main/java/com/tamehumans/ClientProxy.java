package com.tamehumans;

import com.tamehumans.client.renderer.entity.RenderWarrior;
import com.tamehumans.entity.EntityWarrior;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityWarrior.class, new RenderWarrior());
    }

    @Override
    public void registerSound() {
    }
}