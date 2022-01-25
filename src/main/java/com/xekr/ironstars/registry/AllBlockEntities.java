package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.blocks.entity.CopperPressurePlateBlockEntity;
import com.xekr.ironstars.blocks.entity.TitaniumPressurePlateBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("ConstantConditions")
public class AllBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, IronStars.ID);

    public static final BlockEntityType<CopperPressurePlateBlockEntity> COPPER_PRESSURE_PLATE = register("copper_pressure_plate", CopperPressurePlateBlockEntity::new, AllBlocks.COPPER_PRESSURE_PLATE);
    public static final BlockEntityType<TitaniumPressurePlateBlockEntity> TITANIUM_PRESSURE_PLATE = register("titanium_pressure_plate", TitaniumPressurePlateBlockEntity::new, AllBlocks.TITANIUM_PRESSURE_PLATE);


    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.BlockEntitySupplier<T> pFactory, Block... pValidBlocks) {
        BlockEntityType<T> blockEntityType = BlockEntityType.Builder.of(pFactory, pValidBlocks).build(null);
        REGISTRY.register(name, () -> blockEntityType);
        return blockEntityType;
    }

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
