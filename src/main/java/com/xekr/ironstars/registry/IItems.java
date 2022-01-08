package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.item.WrenchItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xekr.ironstars.registry.CreativeTab.*;

@SuppressWarnings("unused")
public class IItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronStars.ID);

    public static final RegistryObject<Item> CHIP = ITEMS.register("chip", () -> new Item(getProperties()));
    public static final RegistryObject<Item> CIRCUIT_BOARD = ITEMS.register("circuit_board", () -> new Item(getProperties()));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new Item(getProperties()));
    public static final RegistryObject<Item> HARDENED_RESIN = ITEMS.register("hardened_resin", () -> new Item(getProperties()));
    public static final RegistryObject<Item> MAGNET = ITEMS.register("magnet", () -> new Item(getProperties()));
    public static final RegistryObject<Item> RESIN = ITEMS.register("resin", () -> new Item(getProperties()));
    public static final RegistryObject<Item> SILICON = ITEMS.register("silicon", () -> new Item(getProperties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(getProperties()));
    public static final RegistryObject<Item> STICKY_RESIN = ITEMS.register("sticky_resin", () -> new Item(getProperties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_INGOT = ITEMS.register("titanium_alloy_ingot", () -> new Item(getProperties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(getProperties()));
    public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench", () -> new WrenchItem(getProperties()));

    public static final RegistryObject<Item> ACCELERATOR = ITEMS.register("accelerator", () -> new BlockItem(IBlocks.ACCELERATOR.get(), getProperties()));
    public static final RegistryObject<Item> CENTRIFUGE = ITEMS.register("centrifuge", () -> new BlockItem(IBlocks.CENTRIFUGE.get(), getProperties()));
    public static final RegistryObject<Item> CHEMICAL_ACCUMULATOR = ITEMS.register("chemical_accumulator", () -> new BlockItem(IBlocks.CHEMICAL_ACCUMULATOR.get(), getProperties()));
    public static final RegistryObject<Item> COIL = ITEMS.register("coil", () -> new BlockItem(IBlocks.COIL.get(), getProperties()));
//    public static final RegistryObject<Item> ELECTRIC_WIRE = ITEMS.register("electric_wire", () -> new BlockItem(IBlocks.ELECTRIC_WIRE.get(), getProperties()));
    public static final RegistryObject<Item> FISH_TANK = ITEMS.register("fish_tank", () -> new BlockItem(IBlocks.FISH_TANK.get(), getProperties(DECO)));
    public static final RegistryObject<Item> INTERACTOR = ITEMS.register("interactor", () -> new BlockItem(IBlocks.INTERACTOR.get(), getProperties()));
    public static final RegistryObject<Item> ITEM_RAIL = ITEMS.register("item_rail", () -> new BlockItem(IBlocks.ITEM_RAIL.get(), getProperties()));
    public static final RegistryObject<Item> MAGNET_BLOCK = ITEMS.register("magnet_block", () -> new BlockItem(IBlocks.MAGNET_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> MOON_DUST = ITEMS.register("moon_dust", () -> new BlockItem(IBlocks.MOON_DUST.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> MOON_ROCK = ITEMS.register("moon_rock", () -> new BlockItem(IBlocks.MOON_ROCK.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> MOON_SOIL = ITEMS.register("moon_soil", () -> new BlockItem(IBlocks.MOON_SOIL.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> MOTOR = ITEMS.register("motor", () -> new BlockItem(IBlocks.MOTOR.get(), getProperties()));
    public static final RegistryObject<Item> MOTOR_LAY = ITEMS.register("motor_lay", () -> new BlockItem(IBlocks.MOTOR_LAY.get(), getProperties()));
    public static final RegistryObject<Item> SILICON_BLOCK = ITEMS.register("silicon_block", () -> new BlockItem(IBlocks.SILICON_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block", () -> new BlockItem(IBlocks.STEEL_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> STEEL_TILES = ITEMS.register("steel_tiles", () -> new BlockItem(IBlocks.STEEL_TILES.get(), getProperties(DECO)));
    public static final RegistryObject<Item> TITANIUM_ALLOY_BLOCK = ITEMS.register("titanium_alloy_block", () -> new BlockItem(IBlocks.TITANIUM_ALLOY_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> TITANIUM_BLOCK = ITEMS.register("titanium_block", () -> new BlockItem(IBlocks.TITANIUM_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> TITANIUM_ORE = ITEMS.register("titanium_ore", () -> new BlockItem(IBlocks.TITANIUM_ORE.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> TURBINE = ITEMS.register("turbine", () -> new BlockItem(IBlocks.TURBINE.get(), getProperties()));
    public static final RegistryObject<Item> WINDMILL = ITEMS.register("windmill", () -> new BlockItem(IBlocks.WINDMILL.get(), getProperties()));
    public static final RegistryObject<Item> COPPER_PRESSURE_PLATE = ITEMS.register("copper_pressure_plate", () -> new BlockItem(IBlocks.COPPER_PRESSURE_PLATE.get(), getProperties()));

    private static Item.Properties getProperties() {
        return getProperties(BASE);
    }

    private static Item.Properties getProperties(CreativeModeTab tab) {
        return new Item.Properties().tab(tab);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
