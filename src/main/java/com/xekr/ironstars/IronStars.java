package com.xekr.ironstars;

import com.xekr.ironstars.registry.AllBlocks;
import com.xekr.ironstars.registry.AllBlockEntities;
import com.xekr.ironstars.registry.AllCapabilities;
import com.xekr.ironstars.registry.AllFluids;
import com.xekr.ironstars.registry.AllItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IronStars.ID)
public class IronStars {

    public static final String ID = "ironstars";
    public static final String NAME = "Iron Stars";
    private static final Logger LOGGER = LogManager.getLogger();

    public IronStars() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(AllCapabilities::register);
        AllBlocks.register(bus);
        AllBlockEntities.register(bus);
        AllItems.register(bus);
        AllFluids.register(bus);
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(ID, name);
    }
}
