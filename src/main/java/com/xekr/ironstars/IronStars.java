package com.xekr.ironstars;

import com.xekr.ironstars.registry.AllBiomes;
import com.xekr.ironstars.recipe.AnvilRecipeTypes;
import com.xekr.ironstars.registry.AllBlocks;
import com.xekr.ironstars.registry.AllBlockEntities;
import com.xekr.ironstars.registry.AllCapabilities;
import com.xekr.ironstars.registry.AllDimensions;
import com.xekr.ironstars.registry.AllFeatures;
import com.xekr.ironstars.registry.AllFluids;
import com.xekr.ironstars.registry.AllItems;
import com.xekr.ironstars.registry.AllSounds;
import com.xekr.ironstars.registry.AllTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IronStars.ID)
public class IronStars {

    public static final String ID = "ironstars";
    public static final String NAME = "Iron Stars";
    public static final Logger LOGGER = LogManager.getLogger();

    public IronStars() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(AllCapabilities::register);
        AllTags.init();
        AllSounds.register(bus);
        AllBlocks.register(bus);
        AllBlockEntities.register(bus);
        AllItems.register(bus);
        AllFluids.register(bus);
        AllFeatures.register();
        AllBiomes.register(bus);
        AllDimensions.init();
        AnvilRecipeTypes.init();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(ID, name);
    }
}
