package me.swirtzly.tce.entity;

import me.swirtzly.tce.handlers.RegHandler;
import me.swirtzly.tce.util.TCEUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Created by Swirtzly
 * on 15/01/2020 @ 20:20
 */
public class ScaledEntity extends Entity {

    private static final DataParameter<CompoundNBT> ENTITYDATA = EntityDataManager.createKey(ScaledEntity.class, DataSerializers.COMPOUND_NBT);

    public ScaledEntity(World worldIn, double x, double y, double z, Entity stack) {
        this(worldIn);
        this.setPosition(x, y, z);
        this.setEntity(stack);
        this.rotationYaw = (float) (Math.random() * 360.0D);
    }

    public ScaledEntity(World worldIn, double x, double y, double z, Entity stack, float height, float width) {
        this(worldIn);
        this.setPosition(x, y, z);
        this.setEntity(stack);
        this.rotationYaw = (float) (Math.random() * 360.0D);
    }

    public ScaledEntity(EntityType type, World world) {
        this(world);
    }

    public ScaledEntity(World worldIn) {
        super(RegHandler.EntityEntries.ENTITY_SHRUNK, worldIn);
    }

    @Override
    protected void registerData() {
        this.getDataManager().register(ENTITYDATA, new CompoundNBT());
    }

    @Override
    public void remove() {
        super.remove();
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound) {
        setEntity(TCEUtil.createEntity(compound.getCompound("entity_shrunk"), world));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.put("entity_shrunk", getEntityTag());
    }

    public CompoundNBT getEntityTag() {
        return this.getDataManager().get(ENTITYDATA);
    }

    public void setEntity(Entity stack) {
        this.getDataManager().set(ENTITYDATA, stack.serializeNBT());
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    /**
     * Will deal the specified amount of fire damage to the entity if the entity isn't immune to fire damage.
     */
    @Override
    protected void dealFireDamage(int amount) {
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed() {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return this.isAlive();
    }

}