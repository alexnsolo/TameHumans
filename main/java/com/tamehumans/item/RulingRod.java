package com.tamehumans.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class RulingRod extends Item {

    private ToolMaterial material;

    public RulingRod(ToolMaterial material) {
        this.material = material;
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("rulingRod" + this.material.name());
        setTextureName("tamehumans:rulingRod"  + this.material.name());
    }

    public ToolMaterial getMaterial() {
        return material;
    }
}

