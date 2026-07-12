package net.kayn.soulrend_tweaks;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SoulrendTweaks.MOD_ID)
public class SoulrendTweaks {

    public static final String MOD_ID = "soulrend_tweaks";

    public SoulrendTweaks() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SoulrendTweaksConfig.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}