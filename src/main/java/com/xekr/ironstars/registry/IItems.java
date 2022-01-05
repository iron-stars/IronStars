package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    public static final RegistryObject<Item> coil = ITEMS.register("coil", () -> new BlockItem(IBlocks.COIL.get(), getProperties()));
    public static final RegistryObject<Item> electric_wire = ITEMS.register("electric_wire", () -> new BlockItem(IBlocks.ELECTRIC_WIRE.get(), getProperties()));
    public static final RegistryObject<Item> magnet_block = ITEMS.register("magnet_block", () -> new BlockItem(IBlocks.MAGNET.get(), getProperties()));
    public static final RegistryObject<Item> moon_dust = ITEMS.register("moon_dust", () -> new BlockItem(IBlocks.MOON_DUST.get(), getProperties()));
    public static final RegistryObject<Item> moon_rock = ITEMS.register("moon_rock", () -> new BlockItem(IBlocks.MOON_ROCK.get(), getProperties()));
    public static final RegistryObject<Item> moon_soil = ITEMS.register("moon_soil", () -> new BlockItem(IBlocks.MOON_SOIL.get(), getProperties()));
    public static final RegistryObject<Item> motor = ITEMS.register("motor", () -> new BlockItem(IBlocks.MOTOR.get(), getProperties()));
    public static final RegistryObject<Item> motor_lay = ITEMS.register("motor_lay", () -> new BlockItem(IBlocks.MOTOR_LAY.get(), getProperties()));
    public static final RegistryObject<Item> silicon_block = ITEMS.register("silicon_block", () -> new BlockItem(IBlocks.SILICON.get(), getProperties()));
    public static final RegistryObject<Item> steel_block = ITEMS.register("steel_block", () -> new BlockItem(IBlocks.STEEL.get(), getProperties()));
    public static final RegistryObject<Item> steel_tiles = ITEMS.register("steel_tiles", () -> new BlockItem(IBlocks.STEEL_TILES.get(), getProperties()));
    public static final RegistryObject<Item> titanium_alloy_block = ITEMS.register("titanium_alloy_block", () -> new BlockItem(IBlocks.TITANIUM_ALLOY.get(), getProperties()));
    public static final RegistryObject<Item> titanium_block = ITEMS.register("titanium_block", () -> new BlockItem(IBlocks.TITANIUM.get(), getProperties()));
    public static final RegistryObject<Item> titanium_ore = ITEMS.register("titanium_ore", () -> new BlockItem(IBlocks.TITANIUM_ORE.get(), getProperties()));
    public static final RegistryObject<Item> turbine = ITEMS.register("turbine", () -> new BlockItem(IBlocks.TURBINE.get(), getProperties()));
    public static final RegistryObject<Item> windmill = ITEMS.register("windmill", () -> new BlockItem(IBlocks.WINDMILL.get(), getProperties()));


    private static Item.Properties getProperties() {
        return new Item.Properties().tab(IronStars.CREATIVE_MODE_TAB);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
