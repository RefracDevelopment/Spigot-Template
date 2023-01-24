package me.refracdevelopment.example.utilities.chat;

import dev.rosewood.rosegarden.utils.StringPlaceholders;
import me.refracdevelopment.example.utilities.config.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Placeholders {

    public static String setPlaceholders(CommandSender sender, String placeholder) {
        placeholder = placeholder.replace("prefix", Config.PREFIX);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholder = placeholder.replace("%player%", player.getName());
            placeholder = placeholder.replace("%displayname%", player.getDisplayName());
        }
        placeholder = placeholder.replace("%arrow%", "\u00BB");
        placeholder = placeholder.replace("%arrow_2%", "\u27A5");
        placeholder = placeholder.replace("%star%", "\u2726");
        placeholder = placeholder.replace("%circle%", "\u2219");
        placeholder = placeholder.replace("|", "\u239F");

        return placeholder;
    }

    public static StringPlaceholders setPlaceholders(CommandSender sender) {
        StringPlaceholders placeholders = StringPlaceholders.builder().build();

        placeholders.addPlaceholder("prefix", Config.PREFIX);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholders.addPlaceholder("player", player.getName());
            placeholders.addPlaceholder("displayname", player.getDisplayName());
        }
        placeholders.addPlaceholder("arrow", "\u00BB");
        placeholders.addPlaceholder("arrow2", "\u27A5");
        placeholders.addPlaceholder("arrow_2", "\u27A5");
        placeholders.addPlaceholder("star", "\u2726");
        placeholders.addPlaceholder("circle", "\u2219");
        placeholders.addPlaceholder("|", "\u239F");

        return placeholders;
    }
}
