package me.yourname.example.commands;

import me.yourname.example.ExamplePlugin;
import me.yourname.example.utilities.chat.Color;
import me.yourname.example.utilities.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadCommand extends SubCommand {

    @Override
    public String getName() {
        return ExamplePlugin.getInstance().getCommands().RELOAD_COMMAND_ALIASES.get(0);
    }

    @Override
    public List<String> getAliases() {
        return ExamplePlugin.getInstance().getCommands().RELOAD_COMMAND_ALIASES;
    }

    @Override
    public String getDescription() {
        return ExamplePlugin.getInstance().getLocaleFile().getString("command-reload-description");
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(ExamplePlugin.getInstance().getCommands().RELOAD_COMMAND_PERMISSION)) {
            Color.sendMessage(commandSender, "no-permission");
            return;
        }

        ExamplePlugin.getInstance().reloadFiles();
        Color.sendMessage(commandSender, "command-reload-success");
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
