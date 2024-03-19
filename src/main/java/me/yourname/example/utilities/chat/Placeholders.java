package me.yourname.example.utilities.chat;

import lombok.experimental.UtilityClass;
import me.yourname.example.ExamplePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@UtilityClass
public class Placeholders {

    public String setPlaceholders(CommandSender sender, String placeholder) {
        placeholder = placeholder.replace("%prefix%", ExamplePlugin.getInstance().getLocaleFile().getString("prefix"));

        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholder = placeholder.replace("%player%", player.getName());
            placeholder = placeholder.replace("%displayname%", player.getDisplayName());
        }

        placeholder = placeholder.replace("%arrow%", "»");
        placeholder = placeholder.replace("%arrowright%", "»");
        placeholder = placeholder.replace("%arrowleft%", "«");
        placeholder = placeholder.replace("%star%", "✦");
        placeholder = placeholder.replace("%circle%", "∙");
        placeholder = placeholder.replace("|", "⎟");

        return placeholder;
    }

    public StringPlaceholders setPlaceholders(CommandSender sender) {
        StringPlaceholders.Builder placeholders = StringPlaceholders.builder();

        placeholders.add("prefix", ExamplePlugin.getInstance().getLocaleFile().getString("prefix"));

        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholders.add("player", player.getName());
            placeholders.add("displayname", player.getDisplayName());
        }

        placeholders.add("arrow", "»");
        placeholders.add("arrowright", "»");
        placeholders.add("arrowleft", "«");
        placeholders.add("star", "✦");
        placeholders.add("circle", "∙");
        placeholders.add("|", "⎟");

        return placeholders.build();
    }
}