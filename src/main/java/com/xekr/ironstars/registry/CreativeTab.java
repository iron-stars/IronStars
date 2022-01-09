package com.xekr.ironstars.registry;

import com.xekr.ironstars.IronStars;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreativeTab {

    public static final CreativeModeTab BASE = new CreativeModeTab(IronStars.ID + ".base") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(AllItems.CHIP.get());
        }
    };

    public static final CreativeModeTab DECO = new CreativeModeTab(IronStars.ID + ".deco") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(AllItems.FISH_TANK.get());
        }
    };

//    public static final CreativeModeTab SHIP = new CreativeModeTab(IronStars.ID + ".ship") {
//        @Override
//        public @NotNull ItemStack makeIcon() {
//            return new ItemStack(IItems.CHIP.get());
//        }
//    };

    public static final CreativeModeTab ALIEN = new CreativeModeTab(IronStars.ID + ".alien") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(AllItems.MOON_SOIL.get());
        }
    };
}
