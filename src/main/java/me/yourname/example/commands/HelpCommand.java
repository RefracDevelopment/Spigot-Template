package me.yourname.example.commands;

import me.yourname.example.ExamplePlugin;
import me.yourname.example.utilities.chat.Color;
import me.yourname.example.utilities.chat.StringPlaceholders;
import me.yourname.example.utilities.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand extends SubCommand {

    @Override
    public String getName() {
        return ExamplePlugin.getInstance().getCommands().HELP_COMMAND_ALIASES.get(0);
    }

    @Override
    public List<String> getAliases() {
        return ExamplePlugin.getInstance().getCommands().HELP_COMMAND_ALIASES;
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
        if (!commandSender.hasPermission(ExamplePlugin.getInstance().getCommands().HELP_COMMAND_PERMISSION)) {
            Color.sendMessage(commandSender, "no-permission");
            return;
        }

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