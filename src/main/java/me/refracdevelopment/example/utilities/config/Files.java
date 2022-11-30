package me.refracdevelopment.example.utilities.config;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.chat.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class Files {

    private static File configFile;
    private static FileConfiguration config;

    public static void loadFiles(ExamplePlugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        Config.loadConfig();

        Color.log("&c==========================================");
        Color.log("&aAll files have been loaded correctly!");
        Color.log("&c==========================================");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reloadFiles(ExamplePlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch (Exception exception) {
            Color.log("&cFailed to reload the config file!");
            exception.printStackTrace();
        }

        Config.loadConfig();

        Color.log("&c==========================================");
        Color.log("&aAll files have been reloaded correctly!");
        Color.log("&c==========================================");
    }
}