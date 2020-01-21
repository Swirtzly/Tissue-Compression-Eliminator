package me.swirtzly.tce.item;

import me.swirtzly.tce.entity.ScaledEntity;
import me.swirtzly.tce.handlers.RegHandler;
import me.swirtzly.tce.util.TCEUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.server.ServerWorld;

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
                CompoundNBT nbt = entity.serializeNBT();
                System.out.println(nbt);
                BlockPos oldEntityPos = entity.getPosition();
                ServerWorld serverworld = (ServerWorld) player.getEntityWorld();
                ScaledEntity item = (ScaledEntity) RegHandler.EntityEntries.ENTITY_SHRUNK.create(serverworld);
                item.setEntity(entity);
                item.setPosition(oldEntityPos.getX() + 0.5, oldEntityPos.getY(), oldEntityPos.getZ() + 0.5);
                serverworld.summonEntity(item);
                player.world.playSound(null, oldEntityPos.getX(), oldEntityPos.getY(), oldEntityPos.getY(), RegHandler.Sounds.TCE_SHRINK, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entity.remove();
            }
        }

        return super.itemInteractionForEntity(stack, player, entityIn, hand);
    }

    @Override
    public boolean shouldDie(ItemStack stack) {
        return false;
    }
}
