package com.xekr.ironstars.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.xekr.ironstars.config.IronStarsConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.util.Mth;

public class TestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("is").then(Commands.literal("test").executes(context -> {
            System.out.println((long)20/20.0F);
            System.out.println(Mth.ceil((long)1/20.0F));
            return Command.SINGLE_SUCCESS;
        }).then(Commands.argument("value", FloatArgumentType.floatArg(0)).executes(context -> {
            IronStarsConfig.ItemInMagnetGravy = FloatArgumentType.getFloat(context, "value");
            return Command.SINGLE_SUCCESS;
        })))); // is test [float] -> 设置IronStarsConfig.ItemInMagnetGravy的值
    }
}
