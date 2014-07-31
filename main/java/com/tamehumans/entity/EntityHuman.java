package com.tamehumans.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class EntityHuman extends EntityTameable {

    public EntityHuman(World p_i1683_1_) {
        super(p_i1683_1_);
        this.setSize(0.9F, 1.3F);
        this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
        this.tasks.addTask(1, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return new EntityHuman(this.worldObj);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack playerItem = player.inventory.getCurrentItem();

        if (this.isTamed()) {

        }
        else if (playerItem != null && playerItem.getItem() == Items.gold_nugget) {
            if (!player.capabilities.isCreativeMode) {
                --playerItem.stackSize;
            }

            if (playerItem.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote) {
                if (this.rand.nextInt(10) == 0) {  // 1 in 10 chance to tame
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity) null);
                    this.setAttackTarget((EntityLivingBase) null);
                    this.func_152115_b(player.getUniqueID().toString()); // not sure what this does. er...
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(player);
    }
}
