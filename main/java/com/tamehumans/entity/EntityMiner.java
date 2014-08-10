package com.tamehumans.entity;

import com.tamehumans.entity.ai.EntityAIMineOre;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMiner extends EntityHumanBase {

    public EntityMiner(World p_i1683_1_) {
        super(p_i1683_1_);

        this.goldNeededToTame += this.rand.nextInt(20);

        int taskPriority = 0;
        this.tasks.addTask(++taskPriority, new EntityAISwimming(this));
        this.tasks.addTask(++taskPriority, new EntityAIMineOre(this, 1.0D, 10.0F, 20));
        this.tasks.addTask(++taskPriority, new EntityAIFollowOwner(this, 1.0D, 10.0F, 4.0F));
        this.tasks.addTask(++taskPriority, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(++taskPriority, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(++taskPriority, new EntityAILookIdle(this));
    }

    public String getMyName() {
        return "Miner";
    }

    public int getMyInventorySize() {
        return 18;
    }

    public Item getDefaultEquipment() {
        return Items.iron_pickaxe;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
    }

}
