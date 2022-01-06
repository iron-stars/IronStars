package com.xekr.ironstars;

import com.xekr.ironstars.registry.IBlocks;
import com.xekr.ironstars.registry.IItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod(IronStars.ID)
public class IronStars {

    public static final String ID = "ironstars";
    public static final String NAME = "Iron Stars";
    private static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(IronStars.ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(IItems.CHIP.get());
        }
    };

    public IronStars() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        IBlocks.register(bus);
        IItems.register(bus);
    }
}
