package me.refracdevelopment.example.manager.configuration.cache;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.chat.Color;

public class Config {

    public String DATA_TYPE;

    public void loadConfig() {
        DATA_TYPE = ExamplePlugin.getInstance().getConfigFile().getString("data-type");

        Color.log("&c==========================================");
        Color.log("&aAll files have been loaded correctly!");
        Color.log("&c==========================================");
    }
}