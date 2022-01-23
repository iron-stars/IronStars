package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.fluids.NormalFluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllFluids {
    public static final ResourceLocation ACID_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOWING_ACID_TEXTURE = new ResourceLocation("block/water_flow");
    public static final ResourceLocation REDSTONE_TEXTURE = new ResourceLocation("block/lava_still");
    public static final ResourceLocation FLOWING_REDSTONE_TEXTURE = new ResourceLocation("block/lava_flow");
    public static final ResourceLocation FUSION_FUEL_TEXTURE = new ResourceLocation("block/lava_still");
    public static final ResourceLocation FLOWING_FUSION_FUEL_TEXTURE = new ResourceLocation("block/lava_flow");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IronStars.ID);

    public static RegistryObject<FlowingFluid> ACID_FLUID = FLUIDS.register("acid_fluid", () -> new NormalFluid.Source(AllFluids.acidPROPERTIES));
    public static RegistryObject<FlowingFluid> ACID_FLUID_FLOWING = FLUIDS.register("acid_fluid_flowing", () -> new NormalFluid.Flowing(AllFluids.acidPROPERTIES));
    public static RegistryObject<FlowingFluid> REDSTONE_FLUID = FLUIDS.register("redstone_fluid", () -> new NormalFluid.Source(AllFluids.redPROPERTIES));
    public static RegistryObject<FlowingFluid> REDSTONE_FLUID_FLOWING = FLUIDS.register("redstone_fluid_flowing", () -> new NormalFluid.Flowing(AllFluids.redPROPERTIES));
    public static RegistryObject<FlowingFluid> FUSION_FUEL_FLUID = FLUIDS.register("fusion_fuel_fluid", () -> new NormalFluid.Source(AllFluids.ffPROPERTIES));
    public static RegistryObject<FlowingFluid> FUSION_FUEL_FLUID_FLOWING = FLUIDS.register("fusion_fuel_fluid_flowing", () -> new NormalFluid.Flowing(AllFluids.ffPROPERTIES));

    public static NormalFluid.Properties acidPROPERTIES = new NormalFluid.Properties(ACID_FLUID, ACID_FLUID_FLOWING, FluidAttributes.builder(ACID_TEXTURE, FLOWING_ACID_TEXTURE).color(0xAAa5d439).density(4000).viscosity(4000)).bucket(AllItems.ACID_BUCKET).block(AllBlocks.ACID).slopeFindDistance(3).explosionResistance(100F);
    public static NormalFluid.Properties redPROPERTIES = new NormalFluid.Properties(REDSTONE_FLUID, REDSTONE_FLUID_FLOWING, FluidAttributes.builder(REDSTONE_TEXTURE, FLOWING_REDSTONE_TEXTURE).color(0xBBdc143c).density(4000).viscosity(4000)).bucket(AllItems.REDSTONE_BUCKET).block(AllBlocks.REDSTONE).slopeFindDistance(3).explosionResistance(100F);
    public static NormalFluid.Properties ffPROPERTIES = new NormalFluid.Properties(FUSION_FUEL_FLUID, FUSION_FUEL_FLUID_FLOWING, FluidAttributes.builder(FUSION_FUEL_TEXTURE, FLOWING_FUSION_FUEL_TEXTURE).color(0xBB48d1cc).density(4000).viscosity(4000)).bucket(AllItems.FUSION_FUEL_BUCKET).block(AllBlocks.FUSION_FUEL).slopeFindDistance(3).explosionResistance(100F);

    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
