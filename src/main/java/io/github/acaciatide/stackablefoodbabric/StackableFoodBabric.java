package io.github.acaciatide.stackablefoodbabric;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import io.github.acaciatide.stackablefoodbabric.config.FoodStackConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackableFoodBabric {
    public static final Logger LOGGER = LoggerFactory.getLogger("StackableFood");

    // デバッグ用: 初期化タイミングを確認する
    //static {
    //    // デバッグ用: 設定クラスのロードをログ出力する
    //    // LOGGER.info("Config class (GCAPI Entrypoint) is loaded!");
    //}

    @ConfigRoot(value = "config", visibleName = "Stackable Food Config")
    public static final FoodStackConfig config = new FoodStackConfig();
}
