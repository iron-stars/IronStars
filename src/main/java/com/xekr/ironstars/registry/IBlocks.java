package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.blocks.CoilBlock;
import com.xekr.ironstars.blocks.ElectricWireBlock;
import com.xekr.ironstars.blocks.MagnetBlock;
import com.xekr.ironstars.blocks.MoonDustBlock;
import com.xekr.ironstars.blocks.MoonRockBlock;
import com.xekr.ironstars.blocks.MoonSoilBlock;
import com.xekr.ironstars.blocks.MotorBlock;
import com.xekr.ironstars.blocks.MotorLayBlock;
import com.xekr.ironstars.blocks.SiliconBlock;
import com.xekr.ironstars.blocks.SteelBlock;
import com.xekr.ironstars.blocks.SteelTilesBlock;
import com.xekr.ironstars.blocks.TitaniumAlloyBlock;
import com.xekr.ironstars.blocks.TitaniumBlock;
import com.xekr.ironstars.blocks.TitaniumOreBlock;
import com.xekr.ironstars.blocks.TurbineBlock;
import com.xekr.ironstars.blocks.WindmillBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class IBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IronStars.ID);

    public static final RegistryObject<Block> COIL = BLOCKS.register("coil", () -> new CoilBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> ELECTRIC_WIRE = BLOCKS.register("electric_wire", () -> new ElectricWireBlock(getProperties()));
    public static final RegistryObject<Block> MAGNET = BLOCKS.register("magnet_block", () -> new MagnetBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> MOON_DUST = BLOCKS.register("moon_dust", () -> new MoonDustBlock(getProperties(Material.CLAY).sound(SoundType.SAND).noOcclusion()));
    public static final RegistryObject<Block> MOON_ROCK = BLOCKS.register("moon_rock", () -> new MoonRockBlock(getProperties().sound(SoundType.STONE)));
    public static final RegistryObject<Block> MOON_SOIL = BLOCKS.register("moon_soil", () -> new MoonSoilBlock(getProperties(Material.CLAY).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> MOTOR = BLOCKS.register("motor", () -> new MotorBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> MOTOR_LAY = BLOCKS.register("motor_lay", () -> new MotorLayBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> SILICON = BLOCKS.register("silicon_block", () -> new SiliconBlock(getProperties()));
    public static final RegistryObject<Block> STEEL = BLOCKS.register("steel_block", () -> new SteelBlock(getProperties()));
    public static final RegistryObject<Block> STEEL_TILES = BLOCKS.register("steel_tiles", () -> new SteelTilesBlock(getProperties(Material.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> TITANIUM_ALLOY = BLOCKS.register("titanium_alloy_block", () -> new TitaniumAlloyBlock(getProperties()));
    public static final RegistryObject<Block> TITANIUM = BLOCKS.register("titanium_block", () -> new TitaniumBlock(getProperties()));
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new TitaniumOreBlock(getProperties(Material.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> TURBINE = BLOCKS.register("turbine", () -> new TurbineBlock(getProperties().noOcclusion()));
    public static final RegistryObject<Block> WINDMILL = BLOCKS.register("windmill", () -> new WindmillBlock(getProperties().noOcclusion()));


    private static Properties getProperties() {
        return getProperties(Material.METAL);
    }

    private static Properties getProperties(Material material) {
        return Properties.of(material).explosionResistance(0.5F).sound(SoundType.METAL);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
