package com.xekr.ironstars.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class TomlConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.DoubleValue RESISTANCE;
    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("ironstars-settings").push("electric");
        RESISTANCE = COMMON_BUILDER.comment("Resistance value").defineInRange("resistance",0.0D,0.0D,100.0D);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
