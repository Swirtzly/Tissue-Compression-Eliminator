package com.wc.tce.common.item;

import com.wc.tce.common.block.TCEBlocks;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TCEBlockItem extends Item {

    public TCEBlockItem() {
        super(new Properties());
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        BlockPos checkPos = pos.up();
        if (!player.world.isRemote) {
            //TODO Support replaceable blocks (Leaves etc)
            if (player.world.isAirBlock(checkPos)) {
                player.world.setBlockState(checkPos, TCEBlocks.MOB.get().getDefaultState());
                TCETile tile = (TCETile) player.world.getTileEntity(checkPos);
                if (tile != null) {
                    System.out.println(context.getItem() + " ||" + getEntity(context.getItem()));
                    tile.setEntity(getEntity(context.getItem()));
                    tile.sendUpdates();
                }
                if(!player.isCreative()){
                    context.getItem().shrink(1);
                }
            }
        }
        return super.onItemUse(context);
    }


    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (getEntity(stack) == null) {
            stack.setCount(0);
        }
    }

    public static void setEntity(ItemStack stack, CompoundNBT compoundNBT) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.put("entity", compoundNBT);
    }

    public static CompoundNBT getEntity(ItemStack stack) {
        return (CompoundNBT) stack.getTag().get("entity");
    }
}
