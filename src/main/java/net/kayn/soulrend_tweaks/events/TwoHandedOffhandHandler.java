package net.kayn.soulrend_tweaks.events;

import net.kayn.soulrend_tweaks.SoulrendTweaks;
import net.kayn.soulrend_tweaks.TwoHandedWeaponTags;
import net.kayn.soulrend_tweaks.SoulrendTweaksConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SoulrendTweaks.MOD_ID)
public class TwoHandedOffhandHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side.isClient()) return;
        if (SoulrendTweaksConfig.TWO_HANDED_OFFHAND_MODE.get() != SoulrendTweaksConfig.OffhandRestrictionMode.KICK) return;

        Player player = event.player;
        ItemStack offhand = player.getOffhandItem();
        if (offhand.isEmpty()) return;

        ItemStack mainhand = player.getMainHandItem();
        if (!mainhand.is(TwoHandedWeaponTags.TWO_HANDED_WEAPONS)) return;

        player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        if (!player.addItem(offhand)) {
            player.drop(offhand, false);
        }
    }
}