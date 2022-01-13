package com.xekr.ironstars.registry;

import com.xekr.ironstars.blocks.*;
import com.xekr.ironstars.IronStars;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IronStars.ID);

    //base
    public static final RegistryObject<Block> ACCELERATOR = BLOCKS.register("accelerator", () -> new AcceleratorBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> CENTRIFUGE = BLOCKS.register("centrifuge", () -> new CentrifugeBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> CHEMICAL_ACCUMULATOR = BLOCKS.register("chemical_accumulator", () -> new ChemicalAccumulatorBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> COIL = BLOCKS.register("coil", () -> new CoilBlock(getProperties().noOcclusion()));
//    public static final RegistryObject<Block> ELECTRIC_WIRE = BLOCKS.register("electric_wire", () -> new ElectricWireBlock(getProperties())); // TODO:报错太多暂时删掉
    public static final RegistryObject<Block> INTERACTOR = BLOCKS.register("interactor", () -> new InteractorBlock(defaultProperties().noOcclusion()));
    public static final RegistryObject<Block> ITEM_RAIL = BLOCKS.register("item_rail", () -> new ItemRailBlock(defaultProperties().noOcclusion()));
    public static final RegistryObject<Block> MOTOR = BLOCKS.register("motor", () -> new MotorBlock(defaultProperties().noOcclusion(), false));
    public static final RegistryObject<Block> MOTOR_LAY = BLOCKS.register("motor_lay", () -> new MotorBlock(defaultProperties().noOcclusion(), true));
    public static final RegistryObject<Block> TURBINE = BLOCKS.register("turbine", () -> new TurbineBlock(defaultProperties().noOcclusion()));
    public static final RegistryObject<Block> WINDMILL = BLOCKS.register("windmill", () -> new WindmillBlock(defaultProperties().noOcclusion()));
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = BLOCKS.register("copper_pressure_plate", () -> new CopperPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> TITANIUM_PRESSURE_PLATE = BLOCKS.register("titanium_pressure_plate", () -> new TitaniumPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> STEEL_PRESSURE_PLATE = BLOCKS.register("steel_pressure_plate", () -> new SteelPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = BLOCKS.register("netherite_pressure_plate", () -> new NetheritePressurePlateBlock(defaultProperties().noCollission()));
    public static final RegistryObject<Block> TITANIUM_ALLOY_PRESSURE_PLATE = BLOCKS.register("titanium_alloy_pressure_plate", () -> new TitaniumAlloyPressurePlateBlock(defaultProperties().noCollission()));
    public static final RegistryObject<Block> TUNGSTEN_PRESSURE_PLATE = BLOCKS.register("tungsten_pressure_plate", () -> new TungstenPressurePlateBlock(defaultProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> MAGNET_BLOCK = BLOCKS.register("magnet_block", () -> new MagnetBlock(defaultProperties().explosionResistance(6.0F).noOcclusion()));
    public static final RegistryObject<Block> SILICON_BLOCK = BLOCKS.register("silicon_block", () -> new SiliconBlock(defaultProperties()));
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new SteelBlock(defaultProperties()));
    public static final RegistryObject<Block> TITANIUM_ALLOY_BLOCK = BLOCKS.register("titanium_alloy_block", () -> new TitaniumAlloyBlock(defaultProperties()));
    public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block", () -> new TitaniumBlock(defaultProperties()));
    public static final RegistryObject<LiquidBlock> ACID = BLOCKS.register("acid", () -> new LiquidBlock(AllFluids.ACID_FLUID, liquidProperties()));
    public static final RegistryObject<LiquidBlock> REDSTONE = BLOCKS.register("redstone", () -> new LiquidBlock(AllFluids.REDSTONE_FLUID, liquidProperties()));

    //deco
    public static final RegistryObject<Block> STEEL_TILES = BLOCKS.register("steel_tiles", () -> new SteelTilesBlock(defaultProperties(Material.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> FISH_TANK = BLOCKS.register("fish_tank", () -> new FishTankBlock(defaultProperties().noOcclusion()));
    //ship
    //alien
    public static final RegistryObject<Block> MOON_DUST = BLOCKS.register("moon_dust", () -> new MoonDustBlock(defaultProperties(Material.CLAY).sound(SoundType.SAND).noOcclusion()));
    public static final RegistryObject<Block> MOON_ROCK = BLOCKS.register("moon_rock", () -> new MoonRockBlock(defaultProperties().sound(SoundType.STONE)));
    public static final RegistryObject<Block> MOON_SOIL = BLOCKS.register("moon_soil", () -> new MoonSoilBlock(defaultProperties(Material.CLAY).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new TitaniumOreBlock(defaultProperties(Material.STONE).sound(SoundType.STONE)));

    private static Properties defaultProperties() {
        return defaultProperties(Material.METAL);
    }

    private static Properties defaultProperties(Material material) {
        return Properties.of(material).sound(SoundType.METAL).strength(5.0F).requiresCorrectToolForDrops();
    }

    private static Properties liquidProperties() {
        return Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops();
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
