package me.refracdevelopment.example.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.refracdevelopment.example.ExamplePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadCommand extends SubCommand {

    private final ExamplePlugin plugin;

    public ReloadCommand(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return plugin.getCommands().RELOAD_COMMAND_ALIASES.get(0);
    }

    @Override
    public List<String> getAliases() {
        return plugin.getCommands().RELOAD_COMMAND_ALIASES;
    }

    @Override
    public String getDescription() {
        return "Allows you to reload the config files.";
    }

    @Override
    public String getSyntax() {
        return plugin.getCommands().EXAMPLE_COMMAND_ALIASES.get(0) + " " + plugin.getCommands().RELOAD_COMMAND_ALIASES.get(0);
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(plugin.getCommands().RELOAD_COMMAND_PERMISSION)) {
            plugin.getColor().sendMessage(commandSender, "no-permission");
            return;
        }

        plugin.reloadFiles();
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
