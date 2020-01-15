package me.swirtzly.tce.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 19:41
 */
public class TCEUtil {

    public static RayTraceResult rayTraceWithEntities(Entity entityIn, double distance, RayTraceContext.BlockMode blockModeIn, RayTraceContext.FluidMode fluidModeIn) {
        Vec3d lookVec = entityIn.getLookVec();
        Vec3d startVec = entityIn.getPositionVec().add(0, entityIn.getEyeHeight(), 0);
        Vec3d endVec = startVec.add(entityIn.getLookVec().scale(distance));
        RayTraceResult blockResult = entityIn.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, blockModeIn, fluidModeIn, entityIn));
        RayTraceResult entityResult = null;

        for (int i = 0; i < distance * 2; i++) {
            if (entityResult != null)
                break;
            float scale = i / 2F;
            Vec3d pos = startVec.add(lookVec.scale(scale));

            Vec3d min = pos.add(0.25F, 0.25F, 0.25F);
            Vec3d max = pos.add(-0.25F, -0.25F, -0.25F);
            for (Entity entity : entityIn.world.getEntitiesWithinAABBExcludingEntity(entityIn, new AxisAlignedBB(min.x, min.y, min.z, max.x, max.y, max.z))) {
                entityResult = new EntityRayTraceResult(entity, pos);
                break;
            }
        }

        if (entityResult != null && entityResult.getHitVec().distanceTo(startVec) <= blockResult.getHitVec().distanceTo(startVec)) {
            return entityResult;
        } else {
            return blockResult;
        }
    }

    public static Entity createEntity(CompoundNBT compoundNBT, World world) {
        return EntityType.func_220335_a(compoundNBT, world, entity -> entity);
    }


}
