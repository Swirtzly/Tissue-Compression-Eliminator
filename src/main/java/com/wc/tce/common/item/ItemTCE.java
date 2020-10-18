package com.wc.tce.common.item;

import com.wc.tce.common.block.TCEBlocks;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class ItemTCE extends Item {
    public ItemTCE() {
        super(new Item.Properties());
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (!player.world.isRemote) {
            player.world.setBlockState(target.getPosition(), TCEBlocks.MOB.get().getDefaultState());
            TCETile tile = (TCETile) player.world.getTileEntity(target.getPosition());
            if (tile != null) {
                tile.setEntity(target);
            }
            target.remove(false);
        }
        return super.itemInteractionForEntity(stack, player, target, hand);
    }

}
