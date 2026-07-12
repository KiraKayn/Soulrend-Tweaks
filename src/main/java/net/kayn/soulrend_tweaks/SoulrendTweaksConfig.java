package net.kayn.soulrend_tweaks;

import net.minecraftforge.common.ForgeConfigSpec;

public class SoulrendTweaksConfig {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec.DoubleValue GEM_LOSS_CHANCE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("gem_withdrawal");
        GEM_LOSS_CHANCE = builder
                .comment("Chance (0.0 - 1.0) for a gem to be destroyed when unsocketed with the Sigil of Withdrawal")
                .defineInRange("gemLossChance", 0.5, 0.0, 1.0);
        builder.pop();

        COMMON_SPEC = builder.build();
    }
}