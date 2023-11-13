package me.refracdevelopment.example.manager.configuration.cache;

import me.refracdevelopment.example.ExamplePlugin;

import java.util.List;

public class Commands {

    public String EXAMPLE_COMMAND_NAME;
    public List<String> EXAMPLE_COMMAND_ALIASES;

    public Commands() {
        loadConfig();
    }

    public void loadConfig() {
        EXAMPLE_COMMAND_NAME = ExamplePlugin.getInstance().getCommandsFile().getString("name");
        EXAMPLE_COMMAND_ALIASES = ExamplePlugin.getInstance().getCommandsFile().getStringList("aliases");
    }
}