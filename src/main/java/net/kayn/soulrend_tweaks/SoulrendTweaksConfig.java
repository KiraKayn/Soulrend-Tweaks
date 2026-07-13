package net.kayn.soulrend_tweaks;

import net.minecraftforge.common.ForgeConfigSpec;

public class SoulrendTweaksConfig {

    public enum OffhandRestrictionMode {
        INERT,
        KICK
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec.DoubleValue GEM_LOSS_CHANCE;
    public static final ForgeConfigSpec.EnumValue<OffhandRestrictionMode> TWO_HANDED_OFFHAND_MODE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("soulrend_tweaks");
        GEM_LOSS_CHANCE = builder
                .comment("Chance (0.0 - 1.0) for a gem to be destroyed when unsocketed with the Sigil of Withdrawal")
                .defineInRange("gemLossChance", 0.5, 0.0, 1.0);
        builder.pop();

        builder.push("two_handed_offhand_restriction");
        TWO_HANDED_OFFHAND_MODE = builder
                .comment("Controls behavior for weapons in the soulrend_tweaks:two_handed_weapons tag when something is placed in the offhand.",
                        "INERT: the offhand item can be placed but grants no dual-wielding bonuses.",
                        "KICK: the offhand item is forcibly removed and cannot be placed at all.")
                .defineEnum("mode", OffhandRestrictionMode.INERT);
        builder.pop();

        COMMON_SPEC = builder.build();
    }
}