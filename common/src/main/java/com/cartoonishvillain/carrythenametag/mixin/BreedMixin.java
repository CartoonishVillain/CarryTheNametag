package com.cartoonishvillain.carrythenametag.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.cartoonishvillain.carrythenametag.CommonCarry;

@Mixin(Animal.class)
public class BreedMixin {
    
    @Inject(at = @At("TAIL"), method = "finalizeSpawnChildFromBreeding")
    private void carrythenametagBreeding(ServerLevel serverLevel, Animal animal, AgeableMob ageableMob, CallbackInfo ci) {
        CommonCarry.doRename(((Animal) (Object) this), animal, ageableMob);
    }
}