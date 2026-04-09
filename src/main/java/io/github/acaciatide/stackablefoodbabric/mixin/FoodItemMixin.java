package io.github.acaciatide.stackablefoodbabric.mixin;

import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.acaciatide.stackablefoodbabric.StackableFoodBabric;

@Mixin(FoodItem.class)
public class FoodItemMixin extends Item {

    public FoodItemMixin(int id) {
        super(id);
    }

    @Inject(method = "<init>(IIZ)V", at = @At("RETURN"))
    private void onInit(int id, int healthRestored, boolean meat, CallbackInfo ci) {
        if (StackableFoodBabric.config != null && StackableFoodBabric.config.maxFoodStackSize != null) {
            this.maxCount = StackableFoodBabric.config.maxFoodStackSize;
        } else {
            // System.out.println("[StackableFood] WARNING: Config is not loaded yet!");
        }
    }
}
