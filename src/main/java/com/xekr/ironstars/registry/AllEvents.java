package com.xekr.ironstars.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.xekr.ironstars.command.TestCommand;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
//        @SubscribeEvent
//        public static void gatherData(GatherDataEvent event) {
//            DataGenerator generator = event.getGenerator();
//            ExistingFileHelper helper = event.getExistingFileHelper();
//            generator.addProvider(new WorldGenerator(generator));
//
//        }
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ModBusClient { // mod管线客户端事件
        @SubscribeEvent
        public static void onRenderTypeSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.ACCELERATOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.CENTRIFUGE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.CHEMICAL_ACCUMULATOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.FISH_TANK.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.INTERACTOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.ITEM_RAIL.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MOON_DUST.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MAGNET_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MOTOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.MOTOR_LAY.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.TURBINE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.WINDMILL.get(), RenderType.cutoutMipped());
//      Fluids
            ItemBlockRenderTypes.setRenderLayer(AllFluids.ACID_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.ACID_FLUID_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.REDSTONE_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AllFluids.REDSTONE_FLUID_FLOWING.get(), RenderType.translucent());
        }
    }

}
