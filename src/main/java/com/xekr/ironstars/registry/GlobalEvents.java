package com.xekr.ironstars.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.xekr.ironstars.command.TestCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GlobalEvents {

    public static void onCommandRegister(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        TestCommand.register(dispatcher);
    }
}
