package com.xekr.ironstars.registry;

import com.xekr.ironstars.blocks.*;
import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.blocks.SteelPressurePlateBlock;
import com.xekr.ironstars.blocks.TitaniumPressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class IBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IronStars.ID);

    //base
    public static final RegistryObject<Block> ACCELERATOR = BLOCKS.register("accelerator", () -> new AcceleratorBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> CENTRIFUGE = BLOCKS.register("centrifuge", () -> new CentrifugeBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> CHEMICAL_ACCUMULATOR = BLOCKS.register("chemical_accumulator", () -> new ChemicalAccumulatorBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> COIL = BLOCKS.register("coil", () -> new CoilBlock(getProperties().noOcclusion()));
//    public static final RegistryObject<Block> ELECTRIC_WIRE = BLOCKS.register("electric_wire", () -> new ElectricWireBlock(getProperties())); // TODO:报错太多暂时删掉
    public static final RegistryObject<Block> INTERACTOR = BLOCKS.register("interactor", () -> new InteractorBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> ITEM_RAIL = BLOCKS.register("item_rail", () -> new ItemRailBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> MAGNET_BLOCK = BLOCKS.register("magnet_block", () -> new MagnetBlock(getProperties().explosionResistance(6.0F).noOcclusion()));
    public static final RegistryObject<Block> MOTOR = BLOCKS.register("motor", () -> new MotorBlock(getProperties().noOcclusion(), false));
    public static final RegistryObject<Block> MOTOR_LAY = BLOCKS.register("motor_lay", () -> new MotorBlock(getProperties().noOcclusion(), true));
    public static final RegistryObject<Block> SILICON_BLOCK = BLOCKS.register("silicon_block", () -> new SiliconBlock(getProperties()));
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new SteelBlock(getProperties()));
    public static final RegistryObject<Block> TITANIUM_ALLOY_BLOCK = BLOCKS.register("titanium_alloy_block", () -> new TitaniumAlloyBlock(getProperties()));
    public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("titanium_block", () -> new TitaniumBlock(getProperties()));
    public static final RegistryObject<Block> TURBINE = BLOCKS.register("turbine", () -> new TurbineBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> WINDMILL = BLOCKS.register("windmill", () -> new WindmillBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = BLOCKS.register("copper_pressure_plate", () -> new CopperPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, getProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> TITANIUM_PRESSURE_PLATE = BLOCKS.register("titanium_pressure_plate", () -> new CopperPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, getProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> STEEL_PRESSURE_PLATE = BLOCKS.register("steel_pressure_plate", () -> new CopperPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, getProperties().strength(0.5F).noCollission()));
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = BLOCKS.register("netherite_pressure_plate", () -> new NetheritePressurePlateBlock(NetheritePressurePlateBlock.Sensitivity.FIRERESISTANT, getProperties().noCollission()));
    //deco
    public static final RegistryObject<Block> STEEL_TILES = BLOCKS.register("steel_tiles", () -> new SteelTilesBlock(getProperties(Material.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> FISH_TANK = BLOCKS.register("fish_tank", () -> new FishTankBlock(getProperties().noOcclusion()));
    //ship
    //alien
    public static final RegistryObject<Block> MOON_DUST = BLOCKS.register("moon_dust", () -> new MoonDustBlock(getProperties(Material.CLAY).sound(SoundType.SAND).noOcclusion()));
    public static final RegistryObject<Block> MOON_ROCK = BLOCKS.register("moon_rock", () -> new MoonRockBlock(getProperties().sound(SoundType.STONE)));
    public static final RegistryObject<Block> MOON_SOIL = BLOCKS.register("moon_soil", () -> new MoonSoilBlock(getProperties(Material.CLAY).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new TitaniumOreBlock(getProperties(Material.STONE).sound(SoundType.STONE)));

    private static Properties getProperties() {
        return getProperties(Material.METAL);
    }

    private static Properties getProperties(Material material) {
        return Properties.of(material).sound(SoundType.METAL).strength(5.0F).requiresCorrectToolForDrops();
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
