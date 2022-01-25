package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.item.BreathingHelmet;
import com.xekr.ironstars.item.WrenchItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xekr.ironstars.registry.AllCreativeTab.*;

@SuppressWarnings("unused")
public class AllItems {
    private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, IronStars.ID);

    //base
    public static final RegistryObject<Item> ACCELERATOR = REGISTRY.register("accelerator", () -> new BlockItem(AllBlocks.ACCELERATOR, getProperties()));
    public static final RegistryObject<Item> CENTRIFUGE = REGISTRY.register("centrifuge", () -> new BlockItem(AllBlocks.CENTRIFUGE, getProperties()));
    public static final RegistryObject<Item> CHEMICAL_ACCUMULATOR = REGISTRY.register("chemical_accumulator", () -> new BlockItem(AllBlocks.CHEMICAL_ACCUMULATOR, getProperties()));
    public static final RegistryObject<Item> COIL = REGISTRY.register("coil", () -> new BlockItem(AllBlocks.COIL, getProperties()));
    public static final RegistryObject<Item> COPPER_WIRE = REGISTRY.register("copper_wire", () -> new ItemNameBlockItem(AllBlocks.COPPER_TRIPWIRE, getProperties()));
//    public static final RegistryObject<Item> ELECTRIC_WIRE = REGISTRY.register("electric_wire", () -> new BlockItem(IBlocks.ELECTRIC_WIRE, getProperties()));
    public static final RegistryObject<Item> INTERACTOR = REGISTRY.register("interactor", () -> new BlockItem(AllBlocks.INTERACTOR, getProperties()));
    public static final RegistryObject<Item> ITEM_RAIL = REGISTRY.register("item_rail", () -> new BlockItem(AllBlocks.ITEM_RAIL, getProperties()));
    public static final RegistryObject<Item> MOTOR = REGISTRY.register("motor", () -> new BlockItem(AllBlocks.MOTOR, getProperties()));
    public static final RegistryObject<Item> MOTOR_LAY = REGISTRY.register("motor_lay", () -> new BlockItem(AllBlocks.MOTOR_LAY, getProperties()));
    public static final RegistryObject<Item> TURBINE = REGISTRY.register("turbine", () -> new BlockItem(AllBlocks.TURBINE, getProperties()));
    public static final RegistryObject<Item> WINDMILL = REGISTRY.register("windmill", () -> new BlockItem(AllBlocks.WINDMILL, getProperties()));
    public static final RegistryObject<Item> COPPER_PRESSURE_PLATE = REGISTRY.register("copper_pressure_plate", () -> new BlockItem(AllBlocks.COPPER_PRESSURE_PLATE, getProperties()));
    public static final RegistryObject<Item> TITANIUM_PRESSURE_PLATE = REGISTRY.register("titanium_pressure_plate", () -> new BlockItem(AllBlocks.TITANIUM_PRESSURE_PLATE, getProperties()));
    public static final RegistryObject<Item> STEEL_PRESSURE_PLATE = REGISTRY.register("steel_pressure_plate", () -> new BlockItem(AllBlocks.STEEL_PRESSURE_PLATE, getProperties()));
    public static final RegistryObject<Item> NETHERITE_PRESSURE_PLATE = REGISTRY.register("netherite_pressure_plate", () -> new BlockItem(AllBlocks.NETHERITE_PRESSURE_PLATE, getProperties().fireResistant()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_PRESSURE_PLATE = REGISTRY.register("titanium_alloy_pressure_plate", () -> new BlockItem(AllBlocks.TITANIUM_ALLOY_PRESSURE_PLATE, getProperties().fireResistant()));
    public static final RegistryObject<Item> TUNGSTEN_PRESSURE_PLATE = REGISTRY.register("tungsten_pressure_plate", () -> new BlockItem(AllBlocks.TUNGSTEN_PRESSURE_PLATE, getProperties().fireResistant()));
    public static final RegistryObject<Item> MAGNET_BLOCK = REGISTRY.register("magnet_block", () -> new BlockItem(AllBlocks.MAGNET_BLOCK, getProperties()));
    public static final RegistryObject<Item> SILICON_BLOCK = REGISTRY.register("silicon_block", () -> new BlockItem(AllBlocks.SILICON_BLOCK, getProperties()));
    public static final RegistryObject<Item> STEEL_BLOCK = REGISTRY.register("steel_block", () -> new BlockItem(AllBlocks.STEEL_BLOCK, getProperties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_BLOCK = REGISTRY.register("titanium_alloy_block", () -> new BlockItem(AllBlocks.TITANIUM_ALLOY_BLOCK, getProperties()));
    public static final RegistryObject<Item> TITANIUM_BLOCK = REGISTRY.register("titanium_block", () -> new BlockItem(AllBlocks.TITANIUM_BLOCK, getProperties()));

    //deco
    public static final RegistryObject<Item> STEEL_TILES = REGISTRY.register("steel_tiles", () -> new BlockItem(AllBlocks.STEEL_TILES, getProperties(DECO)));
    public static final RegistryObject<Item> FISH_TANK = REGISTRY.register("fish_tank", () -> new BlockItem(AllBlocks.FISH_TANK, getProperties(DECO)));
    //ship
    //alien
    public static final RegistryObject<Item> MOON_ROCK = REGISTRY.register("moon_rock", () -> new BlockItem(AllBlocks.MOON_ROCK, getProperties(ALIEN)));
    public static final RegistryObject<Item> MOON_SOIL = REGISTRY.register("moon_soil", () -> new BlockItem(AllBlocks.MOON_SOIL, getProperties(ALIEN)));
    public static final RegistryObject<Item> MOON_DUST = REGISTRY.register("moon_dust", () -> new BlockItem(AllBlocks.MOON_DUST, getProperties(ALIEN)));
    public static final RegistryObject<Item> TITANIUM_ORE = REGISTRY.register("titanium_ore", () -> new BlockItem(AllBlocks.TITANIUM_ORE, getProperties(ALIEN)));

    //base
    public static final RegistryObject<Item> WRENCH = REGISTRY.register("wrench", () -> new WrenchItem(getProperties()));
    public static final RegistryObject<Item> ACID_BUCKET = REGISTRY.register("acid_bucket",() -> new BucketItem(AllFluids.ACID_FLUID, getProperties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> REDSTONE_BUCKET = REGISTRY.register("redstone_bucket",() -> new BucketItem(AllFluids.REDSTONE_FLUID, getProperties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> FUSION_FUEL_BUCKET = REGISTRY.register("fusion_fuel_bucket",() -> new BucketItem(AllFluids.FUSION_FUEL_FLUID, getProperties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> ACID_BOTTLE = REGISTRY.register("acid_bottle",() -> new Item(getProperties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

    public static final RegistryObject<Item> CHIP = REGISTRY.register("chip", () -> new Item(getProperties()));
    public static final RegistryObject<Item> CIRCUIT_BOARD = REGISTRY.register("circuit_board", () -> new Item(getProperties()));

    public static final RegistryObject<Item> HARDENED_RESIN = REGISTRY.register("hardened_resin", () -> new Item(getProperties()));
    public static final RegistryObject<Item> MAGNET = REGISTRY.register("magnet", () -> new Item(getProperties()));
    public static final RegistryObject<Item> RESIN = REGISTRY.register("resin", () -> new Item(getProperties()));
    public static final RegistryObject<Item> SILICON = REGISTRY.register("silicon", () -> new Item(getProperties()));
    public static final RegistryObject<Item> STEEL_INGOT = REGISTRY.register("steel_ingot", () -> new Item(getProperties()));
    public static final RegistryObject<Item> STICKY_RESIN = REGISTRY.register("sticky_resin", () -> new Item(getProperties()));
    public static final RegistryObject<Item> TITANIUM_ALLOY_INGOT = REGISTRY.register("titanium_alloy_ingot", () -> new Item(getProperties()));
    public static final RegistryObject<Item> TITANIUM_INGOT = REGISTRY.register("titanium_ingot", () -> new Item(getProperties()));
    public static final RegistryObject<Item> PEARL_FRAGMENT = REGISTRY.register("pearl_fragment", () -> new Item(getProperties()));
    public static final RegistryObject<Item> PURE_CRYSTAL = REGISTRY.register("pure_crystal", () -> new Item(getProperties()));
    public static final RegistryObject<Item> SPACE_CRYSTAL = REGISTRY.register("space_crystal", () -> new Item(getProperties()));
    public static final RegistryObject<Item> REMOTE_CONTROL = REGISTRY.register("remote_control", () -> new Item(getProperties()));
    public static final RegistryObject<Item> BREATHING_HELMET = REGISTRY.register("breathing_helmet", () -> new BreathingHelmet(getProperties()));
    //deco
    //ship
    //alien

    private static Item.Properties getProperties() {
        return getProperties(BASE);
    }

    private static Item.Properties getProperties(CreativeModeTab tab) {
        return new Item.Properties().tab(tab);
    }

    // forge方块注册有延迟, 物品只能以supplier形式存在, 否则BlockItem会NPE
    private static <T extends Item> T register(String name, T item) {
        REGISTRY.register(name, () -> item);
        return item;
    }

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
