package io.github.acaciatide.stackablefoodstapi.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomStewItem.class)
public class MushroomStewItemMixin {

    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
    private void onUseReturned(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir) {
        // スタックがまだ残っている（2個以上重なっていた）場合
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
