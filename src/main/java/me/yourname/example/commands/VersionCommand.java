package me.yourname.example.commands;

import me.yourname.example.ExamplePlugin;
import me.yourname.example.utilities.chat.Color;
import me.yourname.example.utilities.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VersionCommand extends SubCommand {

    @Override
    public String getName() {
        return ExamplePlugin.getInstance().getCommands().VERSION_COMMAND_ALIASES.get(0);
    }

    @Override
    public List<String> getAliases() {
        return ExamplePlugin.getInstance().getCommands().VERSION_COMMAND_ALIASES;
    }

    @Override
    public String getDescription() {
        return ExamplePlugin.getInstance().getLocaleFile().getString("command-version-description");
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(ExamplePlugin.getInstance().getCommands().VERSION_COMMAND_PERMISSION)) {
            Color.sendMessage(commandSender, "no-permission");
            return;
        }

        String baseColor = ExamplePlugin.getInstance().getLocaleFile().getString("base-command-color");
        Color.sendCustomMessage(commandSender, baseColor + "Running <g:#8A2387:#E94057:#F27121>" + ExamplePlugin.getInstance().getDescription().getName() + baseColor + " v" + ExamplePlugin.getInstance().getDescription().getVersion());
        Color.sendCustomMessage(commandSender, baseColor + "Plugin created by: <g:#41E0F0:#FF8DCE>" + ExamplePlugin.getInstance().getDescription().getAuthors().get(0));
        Color.sendMessage(commandSender, "base-command-help");
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}