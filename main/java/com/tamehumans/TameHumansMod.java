package com.tamehumans;

import com.tamehumans.entity.EntityHumanBase;
import com.tamehumans.entity.EntityWarrior;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import java.awt.*;

@Mod(modid = TameHumansMod.MODID, version = TameHumansMod.VERSION)
public class TameHumansMod {

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
        EntityRegistry.registerGlobalEntityID(EntityWarrior.class, "Warrior", 0, eggColor, eggColor);
        LanguageRegistry.instance().addStringLocalization("Warrior", "en_US", "Warrior");

        addSpawn(EntityWarrior.class, 100);
    }

    private void addSpawn(Class<? extends EntityHumanBase> entityClass, int weightedProb) {
        EntityRegistry.addSpawn(entityClass, weightedProb, 0, 1, EnumCreatureType.creature,
                BiomeGenBase.plains,
                BiomeGenBase.beach,
                BiomeGenBase.birchForest,
                BiomeGenBase.birchForestHills,
                BiomeGenBase.coldBeach,
                BiomeGenBase.coldTaiga,
                BiomeGenBase.desert,
                BiomeGenBase.desertHills,
                BiomeGenBase.extremeHills,
                BiomeGenBase.extremeHillsEdge,
                BiomeGenBase.extremeHillsPlus,
                BiomeGenBase.forest,
                BiomeGenBase.forestHills,
                BiomeGenBase.frozenRiver,
                BiomeGenBase.frozenOcean,
                BiomeGenBase.iceMountains,
                BiomeGenBase.icePlains,
                BiomeGenBase.jungle,
                BiomeGenBase.jungleEdge,
                BiomeGenBase.plains,
                BiomeGenBase.jungleHills,
                BiomeGenBase.megaTaiga,
                BiomeGenBase.megaTaigaHills,
                BiomeGenBase.mesa,
                BiomeGenBase.mesaPlateau,
                BiomeGenBase.river,
                BiomeGenBase.roofedForest,
                BiomeGenBase.savanna,
                BiomeGenBase.savannaPlateau,
                BiomeGenBase.stoneBeach,
                BiomeGenBase.swampland,
                BiomeGenBase.taiga,
                BiomeGenBase.taigaHills
        );
    }
}
