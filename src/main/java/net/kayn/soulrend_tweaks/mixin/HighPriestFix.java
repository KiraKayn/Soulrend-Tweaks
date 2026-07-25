package net.kayn.soulrend_tweaks.mixin;

import net.mcreator.dungeonsandcombat.procedures.HighPriestOnEntityTickUpdateProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HighPriestOnEntityTickUpdateProcedure.class, remap = false)
public class HighPriestFix {
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void highPriestFix(LevelAccessor world, double x, double y, double z, Entity entity, CallbackInfo ci) {
        if (entity instanceof Mob mob) {
            if (mob.getTarget() == null) {
                ci.cancel();
            }
        } else {
            ci.cancel();
        }
    }
}
