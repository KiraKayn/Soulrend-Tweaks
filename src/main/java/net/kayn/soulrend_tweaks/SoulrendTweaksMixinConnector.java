package net.kayn.soulrend_tweaks;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class SoulrendTweaksMixinConnector implements IMixinConnector {
    @Override
    public void connect() {
        Mixins.addConfiguration("soulrend_tweaks.mixins.json");

        boolean isDNCLoaded = getClass().getClassLoader().getResource(
                "net/mcreator/dungeonsandcombat/DungeonsAndCombatMod.class") != null;

        if (isDNCLoaded) {
            Mixins.addConfiguration("soulrend_tweaks.mixins.dungeonsandcombat.json");
        }
    }
}
