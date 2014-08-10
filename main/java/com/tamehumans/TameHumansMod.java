package com.tamehumans;

import com.tamehumans.entity.EntityArcher;
import com.tamehumans.entity.EntityHumanBase;
import com.tamehumans.entity.EntityMiner;
import com.tamehumans.entity.EntityWarrior;
import com.tamehumans.item.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

@Mod(modid = TameHumansMod.MODID, version = TameHumansMod.VERSION)
public class TameHumansMod {

    public static final String MODID = "tamehumans";
    public static final String VERSION = "0.0.3";

    @Mod.Instance(value = "mod")
    public static TameHumansMod instance;

    @SidedProxy(clientSide = "com.tamehumans.ClientProxy", serverSide = "com.tamehumans.CommonProxy")
    public static CommonProxy proxy;

    public static Item rulingRodWood;
    public static Item rulingRodStone;
    public static Item rulingRodIron;
    public static Item rulingRodGold;
    public static Item rulingRodDiamond;

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

        rulingRodWood = new RulingRodWood();
        rulingRodStone = new RulingRodStone();
        rulingRodIron = new RulingRodIron();
        rulingRodGold = new RulingRodGold();
        rulingRodDiamond = new RulingRodDiamond();

        GameRegistry.registerItem(rulingRodWood, "ruling_rod_wood");
        GameRegistry.registerItem(rulingRodStone, "ruling_rod_stone");
        GameRegistry.registerItem(rulingRodIron, "ruling_rod_iron");
        GameRegistry.registerItem(rulingRodGold, "ruling_rod_gold");
        GameRegistry.registerItem(rulingRodDiamond, "ruling_rod_diamond");
        GameRegistry.addRecipe(new ItemStack(rulingRodWood), "  #", " # ", "#  ", '#', Items.stick);
        GameRegistry.addRecipe(new ItemStack(rulingRodStone), "  #", " # ", "#  ", '#', Blocks.stone);
        GameRegistry.addRecipe(new ItemStack(rulingRodIron), "  #", " # ", "#  ", '#', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(rulingRodGold), "  #", " # ", "#  ", '#', Items.gold_ingot);
        GameRegistry.addRecipe(new ItemStack(rulingRodDiamond), "  #", " # ", "#  ", '#', Items.diamond);
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
