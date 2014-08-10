package com.tamehumans.entity;

import com.tamehumans.entity.ai.EntityAITamedNearestAttackableTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityWarrior extends EntityHumanBase {

    public EntityWarrior(World p_i1683_1_) {
        super(p_i1683_1_);
        this.goldNeededToTame += this.rand.nextInt(10);
        this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));

        int taskPriority = 0;
        this.tasks.addTask(++taskPriority, new EntityAISwimming(this));
        this.tasks.addTask(++taskPriority, new EntityAIAvoidEntity(this, EntityCreeper.class, 4.0F, 1.0D, 1.2D));
        this.tasks.addTask(++taskPriority, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(++taskPriority, new EntityAIFollowOwner(this, 1.0D, 10.0F, 3.0F));
        this.tasks.addTask(++taskPriority, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(++taskPriority, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(++taskPriority, new EntityAILookIdle(this));

        int targetPriority = 0;
        this.targetTasks.addTask(++targetPriority, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(++targetPriority, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(++targetPriority, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(++targetPriority, new EntityAITamedNearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(++targetPriority, new EntityAITamedNearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.targetTasks.addTask(++targetPriority, new EntityAITamedNearestAttackableTarget(this, EntitySpider.class, 0, true));
        this.targetTasks.addTask(++targetPriority, new EntityAITamedNearestAttackableTarget(this, EntityCaveSpider.class, 0, true));
        this.targetTasks.addTask(++targetPriority, new EntityAITamedNearestAttackableTarget(this, EntityBlaze.class, 0, true));
        this.targetTasks.addTask(++targetPriority, new EntityAITamedNearestAttackableTarget(this, EntityWither.class, 0, true));
    }

    public String getMyName() {
        return "Warrior";
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        int i = this.isTamed() ? 6 : 4;
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }
}
