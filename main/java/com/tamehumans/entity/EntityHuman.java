package com.tamehumans.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityHuman extends EntityTameable {

    private ItemStack sword = new ItemStack(Item.getItemById(267));

    public EntityHuman(World p_i1683_1_) {
        super(p_i1683_1_);
        this.setSize(0.9F, 1.3F);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return new EntityHuman(this.worldObj);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }

    public ItemStack getHeldItem()
    {
        return sword;
    }
}
