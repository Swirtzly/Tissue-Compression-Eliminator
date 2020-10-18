package com.wc.tce.common.block;

import com.wc.tce.TCEMod;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TCEBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TCEMod.MODID);
    public static final RegistryObject<Block> MOB = BLOCKS.register("mob", TCEBlock::new);

}
