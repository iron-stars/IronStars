package com.xekr.ironstars;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class Register {
    @SubscribeEvent
    public static void onBlocksRegister(RegistryEvent.Register<Block> event){
        event.getRegistry().register(AllBlocks.coil);
        event.getRegistry().register(AllBlocks.electricWire);
        event.getRegistry().register(AllBlocks.magnetBlock);
        event.getRegistry().register(AllBlocks.moonDust);
        event.getRegistry().register(AllBlocks.moonRock);
        event.getRegistry().register(AllBlocks.moonSoil);
        event.getRegistry().register(AllBlocks.motor);
        event.getRegistry().register(AllBlocks.motorLay);
        event.getRegistry().register(AllBlocks.siliconBlock);
        event.getRegistry().register(AllBlocks.steelBlock);
        event.getRegistry().register(AllBlocks.steelTiles);
        event.getRegistry().register(AllBlocks.titaniumAlloyBlock);
        event.getRegistry().register(AllBlocks.titaniumBlock);
        event.getRegistry().register(AllBlocks.titaniumOre);
        event.getRegistry().register(AllBlocks.turbine);
        event.getRegistry().register(AllBlocks.windmill);

    }
    @SubscribeEvent
    public static void onItemsRegister(RegistryEvent.Register<Item> event){
        //BlockItems
        event.getRegistry().register(AllItems.coil.setRegistryName(Objects.requireNonNull(AllBlocks.coil.getRegistryName())));
        event.getRegistry().register(AllItems.electric_wire.setRegistryName(Objects.requireNonNull(AllBlocks.electricWire.getRegistryName())));
        event.getRegistry().register(AllItems.magnet_block.setRegistryName(Objects.requireNonNull(AllBlocks.magnetBlock.getRegistryName())));
        event.getRegistry().register(AllItems.moon_dust.setRegistryName(Objects.requireNonNull(AllBlocks.moonDust.getRegistryName())));
        event.getRegistry().register(AllItems.moon_rock.setRegistryName(Objects.requireNonNull(AllBlocks.moonRock.getRegistryName())));
        event.getRegistry().register(AllItems.moon_soil.setRegistryName(Objects.requireNonNull(AllBlocks.moonSoil.getRegistryName())));
        event.getRegistry().register(AllItems.motor.setRegistryName(Objects.requireNonNull(AllBlocks.motor.getRegistryName())));
        event.getRegistry().register(AllItems.motor_lay.setRegistryName(Objects.requireNonNull(AllBlocks.motorLay.getRegistryName())));
        event.getRegistry().register(AllItems.silicon_block.setRegistryName(Objects.requireNonNull(AllBlocks.siliconBlock.getRegistryName())));
        event.getRegistry().register(AllItems.steel_block.setRegistryName(Objects.requireNonNull(AllBlocks.steelBlock.getRegistryName())));
        event.getRegistry().register(AllItems.steel_tiles.setRegistryName(Objects.requireNonNull(AllBlocks.steelTiles.getRegistryName())));
        event.getRegistry().register(AllItems.titanium_alloy_block.setRegistryName(Objects.requireNonNull(AllBlocks.titaniumAlloyBlock.getRegistryName())));
        event.getRegistry().register(AllItems.titanium_block.setRegistryName(Objects.requireNonNull(AllBlocks.titaniumBlock.getRegistryName())));
        event.getRegistry().register(AllItems.titanium_ore.setRegistryName(Objects.requireNonNull(AllBlocks.titaniumOre.getRegistryName())));
        event.getRegistry().register(AllItems.turbine.setRegistryName(Objects.requireNonNull(AllBlocks.turbine.getRegistryName())));
        event.getRegistry().register(AllItems.windmill.setRegistryName(Objects.requireNonNull(AllBlocks.windmill.getRegistryName())));

        //Items
        event.getRegistry().register(AllItems.chip);
        event.getRegistry().register(AllItems.circuit_board);
        event.getRegistry().register(AllItems.copper_wire);
        event.getRegistry().register(AllItems.hardened_resin);
        event.getRegistry().register(AllItems.magnet);
        event.getRegistry().register(AllItems.resin);
        event.getRegistry().register(AllItems.silicon);
        event.getRegistry().register(AllItems.steel_ingot);
        event.getRegistry().register(AllItems.sticky_resin);
        event.getRegistry().register(AllItems.titanium_alloy_ingot);
        event.getRegistry().register(AllItems.titanium_ingot);
    }

}
