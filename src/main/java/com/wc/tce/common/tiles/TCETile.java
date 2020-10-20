package com.wc.tce.common.tiles;

import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class TCETile extends TileEntity {

    private CompoundNBT entity = null;

    public TCETile() {
        super(TCETiles.MOB.get());
    }

    public static Entity createEntity(CompoundNBT compoundNBT, World world) {
        Optional<Entity> entityInstance = EntityType.loadEntityUnchecked(compoundNBT, world);
        return entityInstance.orElse(null);
    }

    public CompoundNBT getEntity() {
        return entity;
    }

    public void setEntity(Entity renderEntity) {
        entity = new CompoundNBT();
        renderEntity.writeUnlessRemoved(entity); //TODO: Fix crash with Endermen Class casting
        sendUpdates();
    }

    public void setEntity(CompoundNBT entity) {
        this.entity = entity;
       // sendUpdates();
    }

    public void sendUpdates() {
        world.updateComparatorOutputLevel(pos, getBlockState().getBlock());
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        markDirty();
    }


    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 3, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(getBlockState(), pkt.getNbtCompound());
    }


    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        if (nbt.contains("entity")) {
            entity = (CompoundNBT) nbt.get("entity");
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (entity != null) {
            compound.put("entity", entity);
        }
        return super.write(compound);
    }
}
