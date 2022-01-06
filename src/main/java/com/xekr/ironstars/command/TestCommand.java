package com.xekr.ironstars.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.xekr.ironstars.config.IronStarsConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class TestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("is").then(Commands.literal("test").then(Commands.argument("value", FloatArgumentType.floatArg(0)).executes(context -> {
            IronStarsConfig.ItemInMagnetGravy = FloatArgumentType.getFloat(context, "value");
            return Command.SINGLE_SUCCESS;
        })))); // is test [float] -> 设置IronStarsConfig.ItemInMagnetGravy的值
    }
}
