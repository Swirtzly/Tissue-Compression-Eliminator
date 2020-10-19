package com.wc.tce;

import com.wc.tce.client.RenderTCE;
import com.wc.tce.common.block.TCEBlocks;
import com.wc.tce.common.item.TCEItems;
import com.wc.tce.common.sounds.TCESounds;
import com.wc.tce.common.tiles.TCETiles;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(TCEMod.MODID)
public class TCEMod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tce_mod";

    public TCEMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TCETiles.MOB.get(), RenderTCE::new);
        RenderTypeLookup.setRenderLayer(TCEBlocks.MOB.get(), RenderType.getCutout());
    }

    @SubscribeEvent
    public void onRegisterNewRegistries(RegistryEvent.NewRegistry e) {
        TCEItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TCEBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TCETiles.TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TCESounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
