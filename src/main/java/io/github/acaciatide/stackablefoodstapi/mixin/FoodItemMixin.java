package io.github.acaciatide.stackablefoodstapi.mixin;

import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.acaciatide.stackablefoodstapi.StackableFoodStAPI;

@Mixin(FoodItem.class)
public class FoodItemMixin extends Item {

    public FoodItemMixin(int id) {
        super(id);
    }

    @Inject(method = "<init>(IIZ)V", at = @At("RETURN"))
    private void onInit(int id, int healthRestored, boolean meat, CallbackInfo ci) {
        // デバッグ用: アイテムのインスタンス化タイミング確認
        System.out.println("[StackableFood] FoodItem created! ID: " + id);
        
        // とりあえずコンストラクタ時点での上書きを試行する
        if (StackableFoodStAPI.config != null && StackableFoodStAPI.config.maxFoodStackSize != null) {
            this.maxCount = StackableFoodStAPI.config.maxFoodStackSize;
        } else {
            System.out.println("[StackableFood] WARNING: Config is not loaded yet!");
        }
    }
}
