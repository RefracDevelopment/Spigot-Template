package me.refracdevelopment.example.commands;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.chat.Color;
import me.refracdevelopment.example.utilities.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        return "Allows you to reload the configuration files.";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("exampleplugin.command.reload")) {
            Color.sendMessage(commandSender, "no-permission");
            return;
        }

        ExamplePlugin.getInstance().reloadFiles();
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
