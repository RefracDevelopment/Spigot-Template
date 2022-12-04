package me.refracdevelopment.example.utilities.chat;

import dev.rosewood.rosegarden.utils.StringPlaceholders;
import me.refracdevelopment.example.utilities.config.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Placeholders {

    public static StringPlaceholders setPlaceholders(CommandSender sender) {
        StringPlaceholders placeholders = StringPlaceholders.builder().build();

        placeholders.addPlaceholder("prefix", Config.PREFIX);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholders.addPlaceholder("%player%", player.getName());
            placeholders.addPlaceholder("%displayname%", player.getDisplayName());
        }
        placeholders.addPlaceholder("%arrow%", "\u00BB");
        placeholders.addPlaceholder("%arrow2%", "\u27A5");
        placeholders.addPlaceholder("%arrow_2%", "\u27A5");
        placeholders.addPlaceholder("%star%", "\u2726");
        placeholders.addPlaceholder("%circle%", "\u2219");
        placeholders.addPlaceholder("|", "\u239F");

        return placeholders;
    }
}
