package me.refracdevelopment.example.utilities.config;

import me.refracdevelopment.example.manager.ConfigurationManager;

public class Config {

    public static String PREFIX;

    public static void loadConfig() {
        PREFIX = ConfigurationManager.Setting.PREFIX.getString();
    }
}