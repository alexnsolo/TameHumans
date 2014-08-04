package com.tamehumans;

import com.tamehumans.client.renderer.entity.RenderArcher;
import com.tamehumans.client.renderer.entity.RenderMiner;
import com.tamehumans.client.renderer.entity.RenderWarrior;
import com.tamehumans.entity.EntityArcher;
import com.tamehumans.entity.EntityMiner;
import com.tamehumans.entity.EntityWarrior;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityWarrior.class, new RenderWarrior());
        RenderingRegistry.registerEntityRenderingHandler(EntityArcher.class, new RenderArcher());
        RenderingRegistry.registerEntityRenderingHandler(EntityMiner.class, new RenderMiner());
    }

    @Override
    public void registerSound() {
    }
}