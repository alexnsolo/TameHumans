package com.tamehumans;

import com.tamehumans.entity.EntityHuman;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import java.awt.*;

@Mod(modid = TameHumansMod.MODID, version = TameHumansMod.VERSION)
public class TameHumansMod
{
    public static final String MODID = "tamehumans";
    public static final String VERSION = "0.0.1";

    @Mod.Instance(value = "mod")
    public static TameHumansMod instance;

    @SidedProxy(clientSide = "com.tamehumans.ClientProxy", serverSide = "com.tamehumans.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.registerRenderThings();
        proxy.registerSound();

        int eggColor = (Color.blue.getRed() << 16) + (Color.blue.getGreen() << 8) + Color.blue.getBlue();
        EntityRegistry.registerGlobalEntityID(EntityHuman.class, "EntityHuman", 0, eggColor, eggColor);
    }
}
