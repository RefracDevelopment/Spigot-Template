package me.refracdevelopment.example.utilities.config;

public class Config {

    public static String PREFIX;

    public static void loadConfig() {
        PREFIX = Files.getConfig().getString("messages.prefix");
    }
}