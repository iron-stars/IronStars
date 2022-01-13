package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.item.BreathingHelmet;
import com.xekr.ironstars.item.WrenchItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xekr.ironstars.registry.CreativeTab.*;

@SuppressWarnings("unused")
public class AllItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronStars.ID);

    //base
    public static final RegistryObject<Item> ACCELERATOR = ITEMS.register("accelerator", () -> new BlockItem(AllBlocks.ACCELERATOR.get(), getProperties()));
    public static final RegistryObject<Item> ACCELERATOR_LAY = ITEMS.register("accelerator_lay", () -> new BlockItem(AllBlocks.ACCELERATOR_LAY.get(), getProperties()));
    public static final RegistryObject<Item> CENTRIFUGE = ITEMS.register("centrifuge", () -> new BlockItem(AllBlocks.CENTRIFUGE.get(), getProperties()));
    public static final RegistryObject<Item> CHEMICAL_ACCUMULATOR = ITEMS.register("chemical_accumulator", () -> new BlockItem(AllBlocks.CHEMICAL_ACCUMULATOR.get(), getProperties()));
    public static final RegistryObject<Item> COIL = ITEMS.register("coil", () -> new BlockItem(AllBlocks.COIL.get(), getProperties()));
//    public static final RegistryObject<Item> ELECTRIC_WIRE = ITEMS.register("electric_wire", () -> new BlockItem(IBlocks.ELECTRIC_WIRE.get(), getProperties()));
    public static final RegistryObject<Item> INTERACTOR = ITEMS.register("interactor", () -> new BlockItem(AllBlocks.INTERACTOR.get(), getProperties()));
    public static final RegistryObject<Item> ITEM_RAIL = ITEMS.register("item_rail", () -> new BlockItem(AllBlocks.ITEM_RAIL.get(), getProperties()));
    public static final RegistryObject<Item> MOTOR = ITEMS.register("motor", () -> new BlockItem(AllBlocks.MOTOR.get(), getProperties()));
    public static final RegistryObject<Item> MOTOR_LAY = ITEMS.register("motor_lay", () -> new BlockItem(AllBlocks.MOTOR_LAY.get(), getProperties()));
    public static final RegistryObject<Item> TURBINE = ITEMS.register("turbine", () -> new BlockItem(AllBlocks.TURBINE.get(), getProperties()));
    public static final RegistryObject<Item> WINDMILL = ITEMS.register("windmill", () -> new BlockItem(AllBlocks.WINDMILL.get(), getProperties()));
    public static final RegistryObject<Item> COPPER_PRESSURE_PLATE = ITEMS.register("copper_pressure_plate", () -> new BlockItem(AllBlocks.COPPER_PRESSURE_PLATE.get(), getProperties()));
    public static final RegistryObject<Item> TITANIUM_PRESSURE_PLATE = ITEMS.register("titanium_pressure_plate", () -> new BlockItem(AllBlocks.TITANIUM_PRESSURE_PLATE.get(), getProperties()));
    public static final RegistryObject<Item> STEEL_PRESSURE_PLATE = ITEMS.register("steel_pressure_plate", () -> new BlockItem(AllBlocks.STEEL_PRESSURE_PLATE.get(), getProperties()));
    public static final RegistryObject<Item> NETHERITE_PRESSURE_PLATE = ITEMS.register("netherite_pressure_plate", () -> new BlockItem(AllBlocks.NETHERITE_PRESSURE_PLATE.get(), getProperties().fireResistant()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_PRESSURE_PLATE = ITEMS.register("titanium_alloy_pressure_plate", () -> new BlockItem(AllBlocks.TITANIUM_ALLOY_PRESSURE_PLATE.get(), getProperties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_PRESSURE_PLATE = ITEMS.register("tungsten_pressure_plate", () -> new BlockItem(AllBlocks.TUNGSTEN_PRESSURE_PLATE.get(), getProperties().fireResistant()));
    public static final RegistryObject<Item> MAGNET_BLOCK = ITEMS.register("magnet_block", () -> new BlockItem(AllBlocks.MAGNET_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> SILICON_BLOCK = ITEMS.register("silicon_block", () -> new BlockItem(AllBlocks.SILICON_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> STEEL_BLOCK = ITEMS.register("steel_block", () -> new BlockItem(AllBlocks.STEEL_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_BLOCK = ITEMS.register("titanium_alloy_block", () -> new BlockItem(AllBlocks.TITANIUM_ALLOY_BLOCK.get(), getProperties()));
    public static final RegistryObject<Item> TITANIUM_BLOCK = ITEMS.register("titanium_block", () -> new BlockItem(AllBlocks.TITANIUM_BLOCK.get(), getProperties()));
    //deco
    public static final RegistryObject<Item> STEEL_TILES = ITEMS.register("steel_tiles", () -> new BlockItem(AllBlocks.STEEL_TILES.get(), getProperties(DECO)));
    public static final RegistryObject<Item> FISH_TANK = ITEMS.register("fish_tank", () -> new BlockItem(AllBlocks.FISH_TANK.get(), getProperties(DECO)));
    //ship
    //alien
    public static final RegistryObject<Item> MOON_ROCK = ITEMS.register("moon_rock", () -> new BlockItem(AllBlocks.MOON_ROCK.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> MOON_SOIL = ITEMS.register("moon_soil", () -> new BlockItem(AllBlocks.MOON_SOIL.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> MOON_DUST = ITEMS.register("moon_dust", () -> new BlockItem(AllBlocks.MOON_DUST.get(), getProperties(ALIEN)));
    public static final RegistryObject<Item> TITANIUM_ORE = ITEMS.register("titanium_ore", () -> new BlockItem(AllBlocks.TITANIUM_ORE.get(), getProperties(ALIEN)));

    //base
    public static final RegistryObject<Item> WRENCH = ITEMS.register("wrench", () -> new WrenchItem(getProperties()));
    public static final RegistryObject<Item> ACID_BUCKET = ITEMS.register("acid_bucket",() -> new BucketItem(AllFluids.ACID_FLUID, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(BASE)));
    public static final RegistryObject<Item> REDSTONE_BUCKET = ITEMS.register("redstone_bucket",() -> new BucketItem(AllFluids.REDSTONE_FLUID, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(BASE)));

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
    public static final RegistryObject<Item> PEARL_FRAGMENT = ITEMS.register("pearl_fragment", () -> new Item(getProperties()));
    public static final RegistryObject<Item> PURE_CRYSTAL = ITEMS.register("pure_crystal", () -> new Item(getProperties()));
    public static final RegistryObject<Item> SPACE_CRYSTAL = ITEMS.register("space_crystal", () -> new Item(getProperties()));
    public static final RegistryObject<Item> REMOTE_CONTROL = ITEMS.register("remote_control", () -> new Item(getProperties()));
    public static final RegistryObject<Item> BREATHING_HELMET = ITEMS.register("breathing_helmet", () -> new BreathingHelmet(getProperties()));
    //deco
    //ship
    //alien

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
