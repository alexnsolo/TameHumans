package com.tamehumans.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHumanBase extends EntityTameable {

    protected InventoryBasic myInventory;
    protected int goldNeededToTame = 10;

    public EntityHumanBase(World p_i1683_1_) {
        super(p_i1683_1_);
        this.setSize(0.9F, 1.3F);
        this.myInventory = new InventoryBasic(getMyName(), false, getMyInventorySize());
    }

    public String getMyName() {
        return "Human";
    }

    public int getMyInventorySize() {
        return 9;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return new EntityHumanBase(this.worldObj);
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
            player.displayGUIChest(this.myInventory);
        }
        else if (playerItem != null && playerItem.getItem() == Items.gold_nugget) {
            --playerItem.stackSize;

            if (playerItem.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            this.goldNeededToTame--;

            if (!this.worldObj.isRemote) {
                if (this.goldNeededToTame <= 0) {
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

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {
        int i = this.isTamed() ? 4 : 2;
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }
}
