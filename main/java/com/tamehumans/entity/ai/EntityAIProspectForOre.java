package com.tamehumans.entity.ai;

import com.tamehumans.entity.EntityMiner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;

import javax.vecmath.Vector3f;

public class EntityAIProspectForOre extends EntityAIBase {

    private EntityMiner miner;
    private float maxSearchDistance;

    public EntityAIProspectForOre(EntityMiner miner, float maxSearchDistance) {
        this.miner = miner;
        this.maxSearchDistance = maxSearchDistance;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        int minX = MathHelper.floor_double(this.miner.posX - maxSearchDistance);
        int minY = MathHelper.floor_double(this.miner.posY - maxSearchDistance);
        int minZ = MathHelper.floor_double(this.miner.posZ - maxSearchDistance);
        int maxX = MathHelper.floor_double(this.miner.posX + maxSearchDistance);
        int maxY = MathHelper.floor_double(this.miner.posY + maxSearchDistance);
        int maxZ = MathHelper.floor_double(this.miner.posZ + maxSearchDistance);

        for (int x = minX; x <= maxX; ++x)
        {
            for (int y = minY; y <= maxY; ++y)
            {
                for (int z = minZ; z <= maxZ; ++z)
                {
                    Block block = this.miner.worldObj.getBlock(x, y, z);
                    if (block instanceof BlockOre)
                    {
                        PathEntity path = this.miner.getNavigator().getPathToXYZ(x, y, z);
                        if (path != null) {
                            this.miner.oreLocations.add(new Vector3f(x, y ,z));
                            System.out.println("Miner found ore! Now has " + this.miner.oreLocations.size());
                        }
                    }
                }
            }
        }

        return false;
    }
}
