package net.kayn.soulrend_tweaks.mixin;

import daripher.skilltree.skill.bonus.predicate.living.DualWieldingEntityPredicate;
import net.kayn.soulrend_tweaks.TwoHandedWeaponTags;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DualWieldingEntityPredicate.class, remap = false)
public class DualWieldingEntityPredicateMixin {

    @Inject(method = "test*", at = @At("HEAD"), cancellable = true, remap = false)
    private void soulrendTweaks$test(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (living.getMainHandItem().is(TwoHandedWeaponTags.TWO_HANDED_WEAPONS)) {
            cir.setReturnValue(false);
        }
    }
}