package io.github.acaciatide.stackablefoodbabric.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomStewItem.class)
public class MushroomStewItemMixin {

    @Unique
    private int stackableFood$lastStewCount = -1;

    @Inject(method = "use", at = @At("HEAD"))
    private void onUseHead(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir) {
        // 食べる前の個数を記憶しておく
        this.stackableFood$lastStewCount = stack.count;
    }

    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
    private void onUseReturned(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir) {
        // 食べる前後で個数が変わっていない場合（他Modによって消費がキャンセルされた場合など）
        if (this.stackableFood$lastStewCount != -1 && this.stackableFood$lastStewCount == stack.count) {
            // ボウルを配らず、そのままのシチューをインベントリに全返しする
            cir.setReturnValue(stack);
            this.stackableFood$lastStewCount = -1;
            return;
        }

        // リセット
        this.stackableFood$lastStewCount = -1;

        // スタックがまだ残っている（正しく1個消費されて2個以上残っていた）場合
        if (stack.count > 0) {
            // シチュー本体のスタックをそのまま返り値とし、スロットを上書きさせない
            cir.setReturnValue(stack);

            // インベントリの空きに空のボウルを突っ込む
            ItemStack bowl = new ItemStack(Item.BOWL);
            user.inventory.addStack(bowl);

            // もし入り切らなかったら（インベントリ満杯状態）、足元に落とす
            if (bowl.count > 0) {
                user.dropItem(bowl, false);
            }
        }
    }
}
