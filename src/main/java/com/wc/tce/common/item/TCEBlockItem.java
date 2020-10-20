package com.wc.tce.common.item;

import com.wc.tce.common.block.TCEBlocks;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TCEBlockItem extends BlockItem {

    public TCEBlockItem() {
        super(TCEBlocks.MOB.get(), new Properties());
    }

    @Override
    public ActionResultType tryPlace(BlockItemUseContext context) {
        ActionResultType result = super.tryPlace(context);
        if (result != ActionResultType.SUCCESS) return ActionResultType.FAIL;
        return ActionResultType.PASS;
    }


    @Override
    protected boolean onBlockPlaced(BlockPos pos, World worldIn, PlayerEntity player, ItemStack stack, BlockState state) {
        super.onBlockPlaced(pos, worldIn, player, stack, state);
        TCETile tile = (TCETile) player.world.getTileEntity(pos);
        if (tile != null) {
            tile.setEntity(getEntity(stack));
            tile.sendUpdates();

            if (!player.world.isRemote) {
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
            }

            return true;
        }
        return false;
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
        if (stack.getTag() != null) {
            return (CompoundNBT) stack.getTag().get("entity");
        }
        return null;
    }
}
