package com.xekr.ironstars.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.xekr.ironstars.command.TestCommand;
import com.xekr.ironstars.world.MoonWorldGenerator;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public final class AllEvents {
    @Mod.EventBusSubscriber
    static class ForgeBusGlobal { // forge管线全局事件
        @SubscribeEvent
        public static void onCommandRegister(RegisterCommandsEvent event) {
            CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
            TestCommand.register(dispatcher);
        }
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    static class ModBusGlobal { // mod管线全局事件
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            generator.addProvider(new MoonWorldGenerator(generator));
        }
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ModBusClient { // mod管线客户端事件
        @SubscribeEvent
        public static void onRenderTypeSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.ACCELERATOR, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.CENTRIFUGE, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.CHEMICAL_ACCUMULATOR, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.FISH_TANK, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.INTERACTOR, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.ITEM_RAIL, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MOON_DUST, RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MAGNET_BLOCK, RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MOTOR, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MOTOR_LAY, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.TURBINE, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.WINDMILL, RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.COPPER_TRIPWIRE, RenderType.translucentMovingBlock());
//      Fluids
            ItemBlockRenderTypes.setRenderLayer(AllFluids.ACID_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.ACID_FLUID_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.REDSTONE_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.REDSTONE_FLUID_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.FUSION_FUEL_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.FUSION_FUEL_FLUID_FLOWING.get(), RenderType.translucent());
        }
    }

}
