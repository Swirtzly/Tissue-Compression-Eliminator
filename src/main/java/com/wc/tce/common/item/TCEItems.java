package com.wc.tce.common.item;

import com.wc.tce.TCEMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TCEItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TCEMod.MODID);
    public static final RegistryObject<Item> TCE = ITEMS.register("tce", ItemTCE::new);
    public static final RegistryObject<Item> BLOCK_PLACER = ITEMS.register("block_placer", TCEBlockItem::new);

}
