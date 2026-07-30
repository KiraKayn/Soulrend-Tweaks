package net.kayn.soulrend_tweaks.mixin.dungeonandcombat;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.mcreator.dungeonsandcombat.procedures.MoltenDarkSteelWeaponProcedure;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MoltenDarkSteelWeaponProcedure.class, remap = false)
public class MoltenDarkSteelWeaponFix {
    @WrapOperation(method = "execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V"))
    private static void healFix(LivingEntity instance, float p_21154_, Operation<Void> original) {
        original.call(instance, instance.getHealth() + 3.0f);
    }
}
