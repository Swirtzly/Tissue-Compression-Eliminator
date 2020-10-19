package com.wc.tce.common.item;

import com.wc.tce.common.block.TCEBlocks;
import com.wc.tce.common.sounds.TCESounds;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TranslationTextComponent;

public class ItemTCE extends Item {
    public ItemTCE() {
        super(new Item.Properties());
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {

        if (!player.world.isRemote) {
            if (!(target instanceof ServerPlayerEntity)) {
                boolean canContinue = false;
                for (ItemStack itemStack : player.inventory.mainInventory) {
                    if (itemStack.getItem() == Items.REDSTONE) {
                        itemStack.shrink(1);
                        canContinue = true;
                    }
                }
                if (canContinue) {
                    player.world.setBlockState(target.getPosition(), TCEBlocks.MOB.get().getDefaultState());
                    TCETile tile = (TCETile) player.world.getTileEntity(target.getPosition());
                    if (tile != null) {
                        tile.setEntity(target);
                    }
                    target.remove(false);
                    player.world.playSound(null, player.getPosition(), TCESounds.TCE.get(), SoundCategory.BLOCKS, 0.5F, 1F);
                } else {
                    //TODO Localise and well, make a better immersive message
                    player.sendStatusMessage(new TranslationTextComponent("No Redstone found"), true);
                }
            }
        }
        return super.itemInteractionForEntity(stack, player, target, hand);
    }

}
