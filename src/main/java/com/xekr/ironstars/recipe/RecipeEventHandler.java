package com.xekr.ironstars.recipe;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RecipeEventHandler {
@SubscribeEvent
public static void onFallingAnvilRemoval(EntityLeaveWorldEvent event) {//FIXME
//	System.out.printf("client=%s%n", event.getWorld().isClientSide());
	if (event.getWorld().isClientSide()) {
		return;
	}
	Entity entity = event.getEntity();
//	System.out.printf("entity=%s, blockPos=%s%n", entity, entity.blockPosition());
	if (entity instanceof FallingBlockEntity fbEntity) {
		if (fbEntity.getBlockState().getBlock() instanceof AnvilBlock) {
			System.out.println(entity.blockPosition());
			AnvilFlatteningCraftingManager.craft(event.getWorld(), entity.blockPosition());
		}
	}
}
}
