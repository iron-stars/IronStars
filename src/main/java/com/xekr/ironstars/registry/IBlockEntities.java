package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.blocks.entity.CopperPressurePlateBlockEntity;
import com.xekr.ironstars.blocks.entity.SteelPressurePlateBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("ConstantConditions")
public class IBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, IronStars.ID);

    public static final RegistryObject<BlockEntityType<CopperPressurePlateBlockEntity>> COPPER_PRESSURE_PLATE = BLOCK_ENTITIES.register("copper_pressure_plate", () -> BlockEntityType.Builder.of(CopperPressurePlateBlockEntity::new, IBlocks.COPPER_PRESSURE_PLATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<SteelPressurePlateBlockEntity>> STEEL_PRESSURE_PLATE = BLOCK_ENTITIES.register("steel_pressure_plate", () -> BlockEntityType.Builder.of(SteelPressurePlateBlockEntity::new, IBlocks.STEEL_PRESSURE_PLATE.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
