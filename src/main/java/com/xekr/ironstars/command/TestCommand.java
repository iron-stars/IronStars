package com.xekr.ironstars.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.xekr.ironstars.config.IronStarsConfig;
import com.xekr.ironstars.registry.AllDimensions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class TestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("is").then(Commands.literal("test").executes(context -> {
            context.getSource().sendSuccess(new TextComponent(String.valueOf(AllDimensions.seed)), false);
            return Command.SINGLE_SUCCESS;
        }))); // is test [float] -> 设置IronStarsConfig.ItemInMagnetGravy的值
    }

    private static void giveEffect(CommandContext<CommandSourceStack> context, boolean ambient, boolean visible, boolean showIcon) throws CommandSyntaxException {
        context.getSource().getPlayerOrException().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0, ambient, visible, showIcon));
    }
}
