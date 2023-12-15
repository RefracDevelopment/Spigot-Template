package me.yourname.example.manager.configuration.cache;

import me.yourname.example.ExamplePlugin;

import java.util.List;

public class Commands {

    public String EXAMPLE_COMMAND_NAME;
    public List<String> EXAMPLE_COMMAND_ALIASES;

    public boolean HELP_COMMAND_ENABLED;
    public String HELP_COMMAND_PERMISSION;
    public List<String> HELP_COMMAND_ALIASES;

    public boolean RELOAD_COMMAND_ENABLED;
    public String RELOAD_COMMAND_PERMISSION;
    public List<String> RELOAD_COMMAND_ALIASES;

    public boolean VERSION_COMMAND_ENABLED;
    public String VERSION_COMMAND_PERMISSION;
    public List<String> VERSION_COMMAND_ALIASES;

    public Commands() {
        loadConfig();
    }

    public void loadConfig() {
        EXAMPLE_COMMAND_NAME = ExamplePlugin.getInstance().getCommandsFile().getString("name");
        EXAMPLE_COMMAND_ALIASES = ExamplePlugin.getInstance().getCommandsFile().getStringList("aliases");

        HELP_COMMAND_ENABLED = ExamplePlugin.getInstance().getCommandsFile().getBoolean("subcommands.help.enabled");
        HELP_COMMAND_PERMISSION = ExamplePlugin.getInstance().getCommandsFile().getString("subcommands.help.permission");
        HELP_COMMAND_ALIASES = ExamplePlugin.getInstance().getCommandsFile().getStringList("subcommands.help.aliases");

        RELOAD_COMMAND_ENABLED = ExamplePlugin.getInstance().getCommandsFile().getBoolean("subcommands.reload.enabled");
        RELOAD_COMMAND_PERMISSION = ExamplePlugin.getInstance().getCommandsFile().getString("subcommands.reload.permission");
        RELOAD_COMMAND_ALIASES = ExamplePlugin.getInstance().getCommandsFile().getStringList("subcommands.reload.aliases");

        VERSION_COMMAND_ENABLED = ExamplePlugin.getInstance().getCommandsFile().getBoolean("subcommands.version.enabled");
        VERSION_COMMAND_PERMISSION = ExamplePlugin.getInstance().getCommandsFile().getString("subcommands.version.permission");
        VERSION_COMMAND_ALIASES = ExamplePlugin.getInstance().getCommandsFile().getStringList("subcommands.version.aliases");
    }
}