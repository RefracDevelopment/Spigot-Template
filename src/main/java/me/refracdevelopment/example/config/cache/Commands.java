package me.refracdevelopment.example.config.cache;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.Manager;

import java.util.List;

public class Commands extends Manager {

    public boolean EXAMPLE_COMMAND_ENABLED;
    public List<String> EXAMPLE_COMMAND_ALIASES;

    public boolean VERSION_COMMAND_ENABLED;
    public String VERSION_COMMAND_PERMISSION;
    public List<String> VERSION_COMMAND_ALIASES;

    public boolean RELOAD_COMMAND_ENABLED;
    public String RELOAD_COMMAND_PERMISSION;
    public List<String> RELOAD_COMMAND_ALIASES;

    public Commands(ExamplePlugin plugin) {
        super(plugin);
    }

    public void loadConfig() {
        EXAMPLE_COMMAND_ENABLED = plugin.getCommandsFile().getBoolean("commands.example.enabled");
        EXAMPLE_COMMAND_ALIASES = plugin.getCommandsFile().getStringList("commands.example.aliases");

        VERSION_COMMAND_ENABLED = plugin.getCommandsFile().getBoolean("commands.example.enabled");
        VERSION_COMMAND_PERMISSION = plugin.getCommandsFile().getString("commands.example.permission");
        VERSION_COMMAND_ALIASES = plugin.getCommandsFile().getStringList("commands.example.aliases");

        RELOAD_COMMAND_ENABLED = plugin.getCommandsFile().getBoolean("commands.reload.enabled");
        RELOAD_COMMAND_PERMISSION = plugin.getCommandsFile().getString("commands.reload.permission");
        RELOAD_COMMAND_ALIASES = plugin.getCommandsFile().getStringList("commands.reload.aliases");
    }
}