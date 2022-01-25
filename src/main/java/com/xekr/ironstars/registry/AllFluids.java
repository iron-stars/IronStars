package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AllFluids {
    private static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
    private static final ResourceLocation WATER_FLOW = new ResourceLocation("block/water_flow");
    private static final ResourceLocation LAVA_STILL = new ResourceLocation("block/lava_still");
    private static final ResourceLocation LAVA_FLOW = new ResourceLocation("block/lava_flow");

    private static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, IronStars.ID);

    public static final RegistryObject<FlowingFluid> ACID_FLUID = REGISTRY.register("acid_fluid", () -> new ForgeFlowingFluid.Source(AllFluids.ACID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> ACID_FLUID_FLOWING = REGISTRY.register("acid_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(AllFluids.ACID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> REDSTONE_FLUID = REGISTRY.register("redstone_fluid", () -> new ForgeFlowingFluid.Source(AllFluids.RED_PROPERTIES));
    public static final RegistryObject<FlowingFluid> REDSTONE_FLUID_FLOWING = REGISTRY.register("redstone_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(AllFluids.RED_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FUSION_FUEL_FLUID = REGISTRY.register("fusion_fuel_fluid", () -> new ForgeFlowingFluid.Source(AllFluids.FUSION_FUEL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FUSION_FUEL_FLUID_FLOWING = REGISTRY.register("fusion_fuel_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(AllFluids.FUSION_FUEL_PROPERTIES));

    private static final ForgeFlowingFluid.Properties ACID_PROPERTIES = new Properties(ACID_FLUID, ACID_FLUID_FLOWING, WATER_STILL, WATER_FLOW, 0xaaa5d439, AllItems.ACID_BUCKET, AllBlocks.ACID);
    private static final ForgeFlowingFluid.Properties RED_PROPERTIES = new Properties(REDSTONE_FLUID, REDSTONE_FLUID_FLOWING, LAVA_STILL, LAVA_FLOW, 0xbbdc143c, AllItems.REDSTONE_BUCKET, AllBlocks.REDSTONE);
    private static final ForgeFlowingFluid.Properties FUSION_FUEL_PROPERTIES = new Properties(FUSION_FUEL_FLUID, FUSION_FUEL_FLUID_FLOWING, LAVA_STILL, LAVA_FLOW,0xbb48d1cc, AllItems.FUSION_FUEL_BUCKET, AllBlocks.FUSION_FUEL);


    static class Properties extends ForgeFlowingFluid.Properties {
        public Properties(Supplier<FlowingFluid> still, Supplier<FlowingFluid> flowing, ResourceLocation still_id, ResourceLocation flowing_id, int color, Supplier<Item> bucket, LiquidBlock block) {
            super(still, flowing, FluidAttributes.builder(still_id, flowing_id).color(color).density(4000).viscosity(4000));
            this.bucket(bucket).block(() -> block).slopeFindDistance(3).explosionResistance(100f);
        }
    }

    public static void register(IEventBus bus) {
        REGISTRY.register(bus);
    }
}
