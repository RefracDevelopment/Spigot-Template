package me.refracdevelopment.example.config.cache;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.Manager;

public class Config extends Manager {

    public String PREFIX;
    public String NO_PERMISSION;
    public String NO_CONSOLE;
    public String EXAMPLE_MESSAGE;

    public Config(ExamplePlugin plugin) {
        super(plugin);
    }

    public void loadConfig() {
        PREFIX = plugin.getConfigFile().getString("prefix");
        NO_PERMISSION = plugin.getConfigFile().getString("messages.no-permission");
        NO_CONSOLE = plugin.getConfigFile().getString("messages.no-console");
        EXAMPLE_MESSAGE = plugin.getConfigFile().getString("messages.example-message");

        plugin.getColor().log("&c==========================================");
        plugin.getColor().log("&aAll files have been loaded correctly!");
        plugin.getColor().log("&c==========================================");
    }
}