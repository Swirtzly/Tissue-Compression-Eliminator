package com.wc.tce.common.tiles;

import com.wc.tce.TCEMod;
import com.wc.tce.common.block.TCEBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class TCETiles {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TCEMod.MODID);

    public static final RegistryObject<TileEntityType<TCETile>> MOB = TILES.register("tcetile", () -> registerTiles(TCETile::new, TCEBlocks.MOB.get()));

    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        return TileEntityType.Builder.create(tile, validBlock).build(null);
    }
}
