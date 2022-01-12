package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.fluids.AcidFluid;
import com.xekr.ironstars.fluids.RedstoneFluid;
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

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IronStars.ID);

    public static RegistryObject<FlowingFluid> ACID_FLUID = FLUIDS.register("acid_fluid", () -> new AcidFluid.Source(AllFluids.acidPROPERTIES));
    public static RegistryObject<FlowingFluid> ACID_FLUID_FLOWING = FLUIDS.register("acid_fluid_flowing", () -> new AcidFluid.Flowing(AllFluids.acidPROPERTIES));
    public static RegistryObject<FlowingFluid> REDSTONE_FLUID = FLUIDS.register("redstone_fluid", () -> new RedstoneFluid.Source(AllFluids.redPROPERTIES));
    public static RegistryObject<FlowingFluid> REDSTONE_FLUID_FLOWING = FLUIDS.register("redstone_fluid_flowing", () -> new RedstoneFluid.Flowing(AllFluids.redPROPERTIES));

    public static AcidFluid.Properties acidPROPERTIES = new AcidFluid.Properties(ACID_FLUID, ACID_FLUID_FLOWING, FluidAttributes.builder(ACID_TEXTURE, FLOWING_ACID_TEXTURE).color(0xAAa5d439).density(4000).viscosity(4000)).bucket(AllItems.ACID_BUCKET).block(AllBlocks.ACID).slopeFindDistance(3).explosionResistance(100F);
    public static RedstoneFluid.Properties redPROPERTIES = new RedstoneFluid.Properties(REDSTONE_FLUID, REDSTONE_FLUID_FLOWING, FluidAttributes.builder(REDSTONE_TEXTURE, FLOWING_REDSTONE_TEXTURE).color(0x7fDC143C).density(4000).viscosity(4000)).bucket(AllItems.REDSTONE_BUCKET).block(AllBlocks.REDSTONE).slopeFindDistance(3).explosionResistance(100F);

    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
