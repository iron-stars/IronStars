package com.xekr.ironstars.registry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(IBlocks.ACCELERATOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.CENTRIFUGE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.CHEMICAL_ACCUMULATOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.FISH_TANK.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.INTERACTOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.ITEM_RAIL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.MOON_DUST.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.MAGNET_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.MOTOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.MOTOR_LAY.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.TURBINE.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(IBlocks.WINDMILL.get(), RenderType.cutoutMipped());
    }
}
