package com.wc.tce.common.block;

import com.wc.tce.common.item.TCEBlockItem;
import com.wc.tce.common.item.TCEItems;
import com.wc.tce.common.tiles.TCETile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TCEBlock extends Block {
    public TCEBlock() {
        super(Block.Properties.create(Material.IRON, MaterialColor.ADOBE).notSolid());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TCETile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(player.getHeldItemMainhand().isEmpty()) {
            ItemStack itemStack = new ItemStack(TCEItems.BLOCK_PLACER.get());
            if (worldIn.getTileEntity(pos) instanceof TCETile) {
                TCETile tile = (TCETile) player.world.getTileEntity(pos);
                if (tile != null) {
                    TCEBlockItem.setEntity(itemStack, tile.getEntity());
                    worldIn.removeBlock(pos, false);
                }
                player.setHeldItem(Hand.MAIN_HAND, itemStack);
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
