package me.swirtzly.tce.item;

import me.swirtzly.tce.util.TCEUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 19:33
 */
public class TCEItem extends OverrideItem {

    public TCEItem(Item.Properties itemProperties) {
        super(itemProperties);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entityIn, Hand hand) {

        if (!player.world.isRemote) {
            RayTraceResult rayTraceResult = TCEUtil.rayTraceWithEntities(player, 5, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE);

            if (rayTraceResult.getType() == RayTraceResult.Type.ENTITY) {
                EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) rayTraceResult;
                Entity entity = entityRayTraceResult.getEntity();
                if(entity instanceof LivingEntity){
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.canUpdate(false);
                    livingEntity.setInvulnerable(true);

                }
            }
        }

        return super.itemInteractionForEntity(stack, player, entityIn, hand);
    }

    @Override
    public boolean shouldDie(ItemStack stack) {
        return false;
    }
}
