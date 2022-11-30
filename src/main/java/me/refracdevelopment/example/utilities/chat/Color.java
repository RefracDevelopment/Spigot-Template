package me.refracdevelopment.example.utilities.chat;

import dev.rosewood.rosegarden.utils.HexUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import me.refracdevelopment.example.utilities.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Color {

    public static String translate(CommandSender sender, String source) {
        source = Placeholders.setPlaceholders(sender, source);

        if (sender instanceof Player) {
            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                Player player = (Player) sender;
                return PlaceholderAPI.setPlaceholders(player, translate(source));
            }
        }

        return translate(source);
    }

    public static String translate(String source) {
        return HexUtils.colorify(source);
    }

    public static List<String> translate(List<String> source) {
        return source.stream().map(Color::translate).collect(Collectors.toList());
    }

    public static void sendMessage(CommandSender sender, String source, boolean color, boolean placeholders) {
        if (source.equalsIgnoreCase("%empty%") || source.contains("%empty%")) return;

        if (color) source = translate(sender, source);

        sender.sendMessage(source);
    }

    public static void log(String message) {
        sendMessage(Bukkit.getConsoleSender(), Config.PREFIX + " " + message, true, true);
    }
}