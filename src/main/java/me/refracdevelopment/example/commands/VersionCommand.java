package me.refracdevelopment.example.commands;

import me.kodysimpson.simpapi.command.SubCommand;
import me.refracdevelopment.example.ExamplePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VersionCommand extends SubCommand {

    private final ExamplePlugin plugin;

    public VersionCommand(ExamplePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return plugin.getCommands().VERSION_COMMAND_ALIASES.get(0);
    }

    @Override
    public List<String> getAliases() {
        return plugin.getCommands().VERSION_COMMAND_ALIASES;
    }

    @Override
    public String getDescription() {
        return "command-version-description";
    }

    @Override
    public String getSyntax() {
        return "/" + plugin.getCommands().EXAMPLE_COMMAND_ALIASES.get(0) + " " + plugin.getCommands().VERSION_COMMAND_ALIASES.get(0);
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(plugin.getCommands().VERSION_COMMAND_PERMISSION)) {
            plugin.getColor().sendMessage(commandSender, "no-permission");
            return;
        }

        String baseColor = plugin.getConfigFile().getString("messages.base-command-color");
        plugin.getColor().sendCustomMessage(commandSender, baseColor + "Running <g:#8A2387:#E94057:#F27121>ExamplePlugin" + baseColor + " v" + plugin.getDescription().getVersion());
        plugin.getColor().sendCustomMessage(commandSender, baseColor + "Plugin created by: <g:#41E0F0:#FF8DCE>" + plugin.getDescription().getAuthors().get(0));
        plugin.getColor().sendMessage(commandSender, "messages.base-command-help");
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}