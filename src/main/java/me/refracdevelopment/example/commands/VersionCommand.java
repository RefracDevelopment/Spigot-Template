package me.refracdevelopment.example.commands;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.chat.Color;
import me.refracdevelopment.example.utilities.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class VersionCommand extends SubCommand {

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        return "command-version-description";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("exampleplugin.command.version")) {
            Color.sendMessage(commandSender, "no-permission");
            return;
        }

        String baseColor = ExamplePlugin.getInstance().getConfigFile().getString("messages.base-command-color");
        Color.sendCustomMessage(commandSender, baseColor + "Running <g:#8A2387:#E94057:#F27121>ExamplePlugin" + baseColor + " v" + ExamplePlugin.getInstance().getDescription().getVersion());
        Color.sendCustomMessage(commandSender, baseColor + "Plugin created by: <g:#41E0F0:#FF8DCE>" + ExamplePlugin.getInstance().getDescription().getAuthors().get(0));
        Color.sendMessage(commandSender, "messages.base-command-help");
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}