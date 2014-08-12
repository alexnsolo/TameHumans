package com.tamehumans.entity;

import com.tamehumans.gui.GuiCommandHuman;
import com.tamehumans.item.RulingRod;
import com.tamehumans.utils.InventoryUtils;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHumanBase extends EntityTameable implements IInvBasic {

    public InventoryBasic inventory;
    protected int goldNeededToTame = 10;

    public EntityHumanBase(World p_i1683_1_) {
        super(p_i1683_1_);

        if (getDefaultEquipment() != null) {
            this.setCurrentItemOrArmor(0, new ItemStack(getDefaultEquipment()));
        }
        this.setSize(0.9F, 1.3F);
        this.inventory = new InventoryBasic(getMyName(), false, getMyInventorySize());
    }

    public String getMyName() {
        return "Human";
    }

    public int getMyInventorySize() {
        return 9;
    }

    public Item getDefaultEquipment() {
        return null;
    }

    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return new EntityHumanBase(this.worldObj);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean interact(EntityPlayer player) {
        ItemStack playerItem = player.inventory.getCurrentItem();

        if (this.isTamed() && playerItem != null && playerItem.getItem() instanceof RulingRod) {
            FMLClientHandler.instance().displayGuiScreen(player, new GuiCommandHuman(player.inventory, this));
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
                    this.setPathToEntity(null);
                    this.setAttackTarget(null);
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

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        int i = this.isTamed() ? 4 : 2;
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }

    public void onInventoryChanged(InventoryBasic p_76316_1_) { }

    public void writeEntityToNBT(NBTTagCompound ntb) {
        super.writeEntityToNBT(ntb);
        InventoryUtils.writeToNBT(ntb, this.inventory);
    }

    public void readEntityFromNBT(NBTTagCompound ntb) {
        super.readEntityFromNBT(ntb);
        InventoryUtils.readFromNBT(ntb, this.inventory);
    }

    protected Item getDropItem() {
        return Items.gold_nugget;
    }

    protected void dropFewItems(boolean hitByPlayer, int lootingLevel) {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLevel);
        for (int k = 0; k < j; ++k) {
            this.dropItem(getDropItem(), 1);
        }

        if (getDefaultEquipment() != null) {
            this.dropItem(getDefaultEquipment(), 1);
        }
    }

    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);

        if (!this.worldObj.isRemote) {
            this.dropInventory();
        }
    }

    private void dropInventory() {
        for (int i = 0; i < this.inventory.getSizeInventory(); ++i) {
            ItemStack itemStack = this.inventory.getStackInSlot(i);
            if (itemStack != null) {
                this.entityDropItem(itemStack, 0.0F);
            }
        }
    }
}
