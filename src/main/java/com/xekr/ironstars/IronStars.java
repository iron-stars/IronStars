package com.xekr.ironstars;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IronStars.ID)
public class IronStars {

    public static final String ID = "iron-stars";
    public static final String NAME = "Iron Stars";
    public static final String VERSION = "1.0";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public IronStars() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(AllBlocks.magnetBlock, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AllBlocks.motor, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AllBlocks.motorLay, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AllBlocks.turbine, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(AllBlocks.windmill, RenderType.getCutout());
    }

}
