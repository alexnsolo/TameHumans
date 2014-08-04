package com.tamehumans.entity.ai;

import com.tamehumans.entity.ITameableRangedAttackMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAIDefensiveArrowAttack extends EntityAIBase {
    /** The entity the AI instance has been applied to */
    private final EntityLiving entityHost;
    /**
     * The entity (as a RangedAttackMob) the AI instance has been applied to.
     */
    private final ITameableRangedAttackMob rangedAttackEntityHost;
    private EntityLivingBase attackTarget;
    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int rangedAttackTime;
    private int minRangedAttackTime;
    /**
     * The maximum time the AI has to wait before performing another ranged attack.
     */
    private int maxRangedAttackTime;
    private float maxAttackDistance;
    private float maxAttackDistanceSq;
    private float followOwnerDistanceSq;

    public EntityAIDefensiveArrowAttack(ITameableRangedAttackMob hostEntity, int minRangedAttackTime, int maxRangedAttackTime, float maxAttackDistance, float followOwnerDistance)
    {
        this.rangedAttackTime = -1;

        if (!(hostEntity instanceof EntityLivingBase))
        {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        else
        {
            this.rangedAttackEntityHost = hostEntity;
            this.entityHost = (EntityLiving)hostEntity;
            this.minRangedAttackTime = minRangedAttackTime;
            this.maxRangedAttackTime = maxRangedAttackTime;
            this.maxAttackDistance = maxAttackDistance;
            this.maxAttackDistanceSq = maxAttackDistance * maxAttackDistance;
            this.followOwnerDistanceSq = followOwnerDistance * followOwnerDistance;
            this.setMutexBits(3);
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else
        {
            this.attackTarget = entitylivingbase;

            // Must see target
            boolean canSee = this.entityHost.getEntitySenses().canSee(this.attackTarget);
            if (!canSee) return false;

            // Must be within range of target
            double distanceToTargetSq = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
            if (distanceToTargetSq > (double)this.maxAttackDistanceSq) return false;

            // Must not be out of follow range of owner
            if (rangedAttackEntityHost.getOwner() != null) {
                Entity owner = rangedAttackEntityHost.getOwner();
                double distanceToOwnerSq = this.entityHost.getDistanceSq(owner.posX, owner.boundingBox.minY, owner.posZ);
                if (distanceToOwnerSq > (double)this.followOwnerDistanceSq) return false;
            }

            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.shouldExecute();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.attackTarget = null;
        this.rangedAttackTime = -1;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);

        double distanceToTargetSq = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
        float distanceFactor;

        if (--this.rangedAttackTime == 0)
        {
            if (distanceToTargetSq > (double)this.maxAttackDistanceSq)
            {
                return;
            }

            distanceFactor = MathHelper.sqrt_double(distanceToTargetSq) / this.maxAttackDistance;
            float fixedDistanceFactor = distanceFactor;

            if (distanceFactor < 0.1F)
            {
                fixedDistanceFactor = 0.1F;
            }

            if (fixedDistanceFactor > 1.0F)
            {
                fixedDistanceFactor = 1.0F;
            }

            this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, fixedDistanceFactor);
            this.rangedAttackTime = MathHelper.floor_float(distanceFactor * (float)(this.maxRangedAttackTime - this.minRangedAttackTime) + (float)this.minRangedAttackTime);
        }
        else if (this.rangedAttackTime < 0)
        {
            distanceFactor = MathHelper.sqrt_double(distanceToTargetSq) / this.maxAttackDistance;
            this.rangedAttackTime = MathHelper.floor_float(distanceFactor * (float)(this.maxRangedAttackTime - this.minRangedAttackTime) + (float)this.minRangedAttackTime);
        }
    }
}
