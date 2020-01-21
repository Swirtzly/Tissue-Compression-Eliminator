package me.swirtzly.tce.item;

import me.swirtzly.tce.entity.OverideItemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 20:32
 */
public class OverrideItem extends Item implements OverideItemEntity.IEntityOverride {

    public OverrideItem(Properties properties) {
        super(properties);
    }

    @Override
    public void update(OverideItemEntity itemOverride) {

    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        OverideItemEntity item = new OverideItemEntity(world, location.posX, location.posY, location.posZ, itemstack);
        item.setEntitySize(item.getHeight(), item.getWidth());
        item.setMotion(location.getMotion());
        return item;
    }

    @Override
    public boolean shouldDie(ItemStack stack) {
        if (stack.getTag() != null) {
            return !stack.getTag().contains("live");
        }
        return true;
    }


}