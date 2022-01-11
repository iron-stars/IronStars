package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import com.xekr.ironstars.fluids.AcidFluid;
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

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IronStars.ID);

    public static RegistryObject<FlowingFluid> ACID_FLUID = FLUIDS.register("acid_fluid", () -> new AcidFluid.Source(AllFluids.PROPERTIES));
    public static RegistryObject<FlowingFluid> ACID_FLUID_FLOWING = FLUIDS.register("acid_fluid_flowing", () -> new AcidFluid.Flowing(AllFluids.PROPERTIES));
    public static AcidFluid.Properties PROPERTIES = new AcidFluid.Properties(ACID_FLUID, ACID_FLUID_FLOWING, FluidAttributes.builder(ACID_TEXTURE, FLOWING_ACID_TEXTURE).color(0xAAa5d439).density(4000).viscosity(4000)).bucket(AllItems.ACID_BUCKET).block(AllBlocks.ACID).slopeFindDistance(3).explosionResistance(100F);

    public static void register(IEventBus bus) {
        FLUIDS.register(bus);
    }
}
