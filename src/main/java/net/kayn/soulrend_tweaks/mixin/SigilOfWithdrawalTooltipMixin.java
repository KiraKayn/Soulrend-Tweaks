package net.kayn.soulrend_tweaks.mixin;

import dev.shadowsoffire.apotheosis.adventure.Adventure.Items;
import dev.shadowsoffire.apotheosis.util.TooltipItem;
import net.kayn.soulrend_tweaks.SoulrendTweaksConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = TooltipItem.class, remap = false)
public class SigilOfWithdrawalTooltipMixin {

    @Inject(method = "appendHoverText", at = @At("TAIL"), remap = false)
    private void soulrendTweaks$appendHoverText(ItemStack pStack, Level pLevel, List<Component> list, TooltipFlag pIsAdvanced, CallbackInfo ci) {
        if (pStack.getItem() != Items.SIGIL_OF_WITHDRAWAL.get()) return;

        int percent = (int) Math.round(SoulrendTweaksConfig.GEM_LOSS_CHANCE.get() * 100);

        list.add(Component.translatable("item.soulrend_tweaks.sigil_of_withdrawal.desc.chance", percent).withStyle(ChatFormatting.DARK_GRAY));
    }
}