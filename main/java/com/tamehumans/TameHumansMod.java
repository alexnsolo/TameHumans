package com.tamehumans;

import com.tamehumans.entity.EntityArcher;
import com.tamehumans.entity.EntityHumanBase;
import com.tamehumans.entity.EntityMiner;
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
    public static final String VERSION = "0.0.2";

    @Mod.Instance(value = "mod")
    public static TameHumansMod instance;

    @SidedProxy(clientSide = "com.tamehumans.ClientProxy", serverSide = "com.tamehumans.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenderThings();
        proxy.registerSound();

        int id = 0;
        EntityRegistry.registerModEntity(EntityWarrior.class, "EntityWarrior", ++id, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityArcher.class, "EntityArcher", ++id, this, 80, 1, true);
        EntityRegistry.registerModEntity(EntityMiner.class, "EntityMiner", ++id, this, 80, 1, true);

        addSpawn(EntityWarrior.class, 100);
        addSpawn(EntityArcher.class, 100);
        addSpawn(EntityMiner.class, 100);
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
