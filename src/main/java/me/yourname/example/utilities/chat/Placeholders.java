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
        placeholder = placeholder.replace("%arrow%", "\u00BB");
        placeholder = placeholder.replace("%arrowright%", "\u00BB");
        placeholder = placeholder.replace("%arrowleft%", "\u00AB");
        placeholder = placeholder.replace("%star%", "\u2726");
        placeholder = placeholder.replace("%circle%", "\u2219");
        placeholder = placeholder.replace("|", "\u239F");

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
        placeholders.add("arrow", "\u00BB");
        placeholders.add("arrowright", "\u00BB");
        placeholders.add("arrowleft", "\u00AB");
        placeholders.add("star", "\u2726");
        placeholders.add("circle", "\u2219");
        placeholders.add("|", "\u239F");

        return placeholders.build();
    }
}