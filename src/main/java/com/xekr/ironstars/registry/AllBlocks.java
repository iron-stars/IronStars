package com.xekr.ironstars.registry;

import com.xekr.ironstars.blocks.*;
import com.xekr.ironstars.IronStars;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.world.level.block.Blocks.TRIPWIRE_HOOK;


public class AllBlocks {

    private static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, IronStars.ID);

    //base
    public static final Block ACCELERATOR = register("accelerator", new Block(defaultProperties().noOcclusion()));
    public static final Block CENTRIFUGE = register("centrifuge", new Block(defaultProperties().noOcclusion()));
    public static final Block CHEMICAL_ACCUMULATOR = register("chemical_accumulator", new Block(defaultProperties().noOcclusion()));
    public static final Block COIL = register("coil", new CoilBlock(defaultProperties().noOcclusion()));
    public static final Block COPPER_TRIPWIRE = register("copper_tripwire", new CopperTripWireBlock((TripWireHookBlock)TRIPWIRE_HOOK, BlockBehaviour.Properties.of(Material.DECORATION).noCollission()));
//    public static final Block ELECTRIC_WIRE = register("electric_wire", new ElectricWireBlock(getProperties())); // TODO:报错太多暂时删掉
    public static final Block INTERACTOR = register("interactor", new Block(defaultProperties().noOcclusion()));
    public static final Block ITEM_RAIL = register("item_rail", new Block(defaultProperties().noOcclusion()));
    public static final Block MOTOR = register("motor", new MotorBlock(defaultProperties().noOcclusion(), false));
    public static final Block MOTOR_LAY = register("motor_lay", new MotorBlock(defaultProperties().noOcclusion(), true));
    public static final Block TURBINE = register("turbine", new Block(defaultProperties().noOcclusion()));
    public static final Block WINDMILL = register("windmill", new Block(defaultProperties().noOcclusion()));
    public static final Block COPPER_PRESSURE_PLATE = register("copper_pressure_plate", new CopperPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final Block TITANIUM_PRESSURE_PLATE = register("titanium_pressure_plate", new TitaniumPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final Block STEEL_PRESSURE_PLATE = register("steel_pressure_plate", new SteelPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final Block NETHERITE_PRESSURE_PLATE = register("netherite_pressure_plate", new NetheritePressurePlateBlock(defaultProperties().noCollission()));
    public static final Block TITANIUM_ALLOY_PRESSURE_PLATE = register("titanium_alloy_pressure_plate", new TitaniumAlloyPressurePlateBlock(defaultProperties().noCollission()));
    public static final Block TUNGSTEN_PRESSURE_PLATE = register("tungsten_pressure_plate", new TungstenPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final Block MAGNET_BLOCK = register("magnet_block", new MagnetBlock(defaultProperties().explosionResistance(6.0F).noOcclusion()));
    public static final Block SILICON_BLOCK = register("silicon_block", new Block(defaultProperties()));
    public static final Block STEEL_BLOCK = register("steel_block", new Block(defaultProperties()));
    public static final Block TITANIUM_ALLOY_BLOCK = register("titanium_alloy_block", new Block(defaultProperties()));
    public static final Block TITANIUM_BLOCK = register("titanium_block", new Block(defaultProperties()));

    public static final LiquidBlock ACID = register("acid", new LiquidBlock(AllFluids.ACID_FLUID, liquidProperties()));
    public static final LiquidBlock REDSTONE = register("redstone", new LiquidBlock(AllFluids.REDSTONE_FLUID, liquidProperties()));
    public static final LiquidBlock FUSION_FUEL = register("fusion_fuel", new LiquidBlock(AllFluids.FUSION_FUEL_FLUID, liquidProperties()));

    //deco
    public static final Block STEEL_TILES = register("steel_tiles", new Block(defaultProperties(Material.STONE).sound(SoundType.STONE)));
    public static final Block FISH_TANK = register("fish_tank", new FishTankBlock(defaultProperties().noOcclusion()));
    //ship
    //alien
    public static final Block MOON_DUST = register("moon_dust", new MoonDustBlock(defaultProperties(Material.CLAY).sound(SoundType.SAND).noOcclusion()));
    public static final Block MOON_ROCK = register("moon_rock", new Block(defaultProperties().sound(SoundType.STONE)));
    public static final Block MOON_SOIL = register("moon_soil", new Block(defaultProperties(Material.CLAY).sound(SoundType.ROOTS)));
    public static final Block TITANIUM_ORE = register("titanium_ore", new Block(defaultProperties(Material.STONE).sound(SoundType.STONE)));

    private static Properties defaultProperties() {
        return defaultProperties(Material.METAL);
    }

    private static Properties defaultProperties(Material material) {
        return Properties.of(material).sound(SoundType.METAL).strength(5.0F).requiresCorrectToolForDrops();
    }

    private static Properties liquidProperties() {
        return Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops();
    }

    private static <T extends Block> T register(String name, T block) {
        REGISTRY.register(name, () -> block);
        return block;
    }
    
    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
