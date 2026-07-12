package net.kayn.soulrend_tweaks.mixin;

import dev.shadowsoffire.apotheosis.adventure.Adventure.Items;
import dev.shadowsoffire.apotheosis.adventure.socket.SocketHelper;
import dev.shadowsoffire.apotheosis.adventure.socket.SocketedGems;
import dev.shadowsoffire.apotheosis.adventure.socket.WithdrawalRecipe;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.GemItem;
import net.kayn.soulrend_tweaks.SoulrendTweaksConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedHashMap;
import java.util.Map;

@Mixin(value = WithdrawalRecipe.class, remap = false)
public class WithdrawalRecipeMixin {

    @Inject(method = "onCraft", at = @At("HEAD"), cancellable = true, remap = false)
    private void soulrendTweaks$onCraft(Container inv, Player player, ItemStack output, CallbackInfo ci) {
        if (player.level().isClientSide()) {
            ci.cancel();
            return;
        }

        ItemStack base = ItemStack.EMPTY;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack candidate = inv.getItem(i);
            if (!candidate.isEmpty() && candidate.getItem() != Items.SIGIL_OF_WITHDRAWAL.get()) {
                base = candidate;
                break;
            }
        }

        if (base.isEmpty()) {
            ci.cancel();
            return;
        }

        SocketedGems gems = SocketHelper.getGems(base);
        Map<String, MutableComponent> lostNames = new LinkedHashMap<>();
        Map<String, Integer> lostCounts = new LinkedHashMap<>();
        double lossChance = SoulrendTweaksConfig.GEM_LOSS_CHANCE.get();

        for (dev.shadowsoffire.apotheosis.adventure.socket.gem.GemInstance gem : gems) {
            ItemStack stack = gem.gemStack();
            if (stack.isEmpty()) continue;

            if (player.level().random.nextDouble() < lossChance) {
                String key = stack.getHoverName().getString();
                lostNames.putIfAbsent(key, stack.getHoverName().copy());
                lostCounts.merge(key, 1, Integer::sum);
                continue;
            }

            stack.removeTagKey(GemItem.UUID_ARRAY);
            if (!player.addItem(stack)) Block.popResource(player.level(), player.blockPosition(), stack);
        }

        SocketHelper.setGems(base, SocketedGems.EMPTY);

        if (!lostNames.isEmpty()) {
            player.level().playSound(null, player.blockPosition(), SoundEvents.ANVIL_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);

            MutableComponent message = Component.literal("Gems lost: ").withStyle(ChatFormatting.GRAY);
            int i = 0;
            int total = lostNames.size();
            for (Map.Entry<String, MutableComponent> entry : lostNames.entrySet()) {
                message.append(entry.getValue());
                int count = lostCounts.get(entry.getKey());
                if (count > 1) {
                    message.append(Component.literal(" x" + count).withStyle(ChatFormatting.GRAY));
                }
                if (++i < total) message.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
            }
            player.sendSystemMessage(message);
        }

        ci.cancel();
    }
}