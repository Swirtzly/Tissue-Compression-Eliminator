package me.swirtzly.tce;

import me.swirtzly.tce.entity.ScaledEntity;
import me.swirtzly.tce.renders.RenderScaledEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("tisselm")
public class Tisselm {
    public static final String MODID = "tisselm";
    private static final Logger LOGGER = LogManager.getLogger();

    public Tisselm() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        System.out.println("WHY LORD");
        RenderingRegistry.registerEntityRenderingHandler(ScaledEntity.class, RenderScaledEntity::new);
    }
}
