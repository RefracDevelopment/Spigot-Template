package me.refracdevelopment.example.commands;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.chat.Color;
import me.refracdevelopment.example.utilities.chat.StringPlaceholders;
import me.refracdevelopment.example.utilities.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class HelpCommand extends SubCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        return ExamplePlugin.getInstance().getLocaleFile().getString("command-help-description");
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        Color.sendMessage(commandSender, "command-help-title");
        ExamplePlugin.getInstance().getSubCommands().forEach(command -> {
            StringPlaceholders placeholders;

            if (!command.getSyntax().isEmpty()) {
                placeholders = StringPlaceholders.builder()
                        .add("cmd", ExamplePlugin.getInstance().getCommands().EXAMPLE_COMMAND_NAME)
                        .add("subcmd", command.getName())
                        .add("args", command.getSyntax())
                        .add("desc", command.getDescription())
                        .build();
                Color.sendMessage(commandSender, "command-help-list-description", placeholders);
            } else {
                placeholders = StringPlaceholders.builder()
                        .add("cmd", ExamplePlugin.getInstance().getCommands().EXAMPLE_COMMAND_NAME)
                        .add("subcmd", command.getName())
                        .add("desc", command.getDescription())
                        .build();
                Color.sendMessage(commandSender, "command-help-list-description-no-args", placeholders);
            }
        });
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}