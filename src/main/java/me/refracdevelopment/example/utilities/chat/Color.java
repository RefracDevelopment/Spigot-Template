package me.refracdevelopment.example.utilities.chat;

import me.clip.placeholderapi.PlaceholderAPI;
import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.utilities.Manager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Color extends Manager {

    private final Placeholders placeholders;
    private final HexUtils hexUtils;

    public Color(ExamplePlugin plugin) {
        super(plugin);
        this.placeholders = new Placeholders(plugin);
        this.hexUtils = new HexUtils();
    }

    public String translate(CommandSender sender, String source) {
        source = placeholders.setPlaceholders(sender, source);

        if (sender instanceof Player) {
            return PlaceholderAPI.setPlaceholders((Player) sender, translate(source));
        } else return translate(source);
    }

    public String translate(String source) {
        return hexUtils.colorify(source);
    }

    public void sendMessage(CommandSender sender, String message) {
        if (message.contains("%empty%") || message.equalsIgnoreCase("%empty%") || message.isEmpty()) return;

        sender.sendMessage(translate(sender, plugin.getConfigFile().getString("messages." + message).replace("%cmd%", plugin.getCommands().EXAMPLE_COMMAND_ALIASES.get(0))));
    }

    public void sendCustomMessage(CommandSender sender, String message) {
        if (message.contains("%empty%") || message.equalsIgnoreCase("%empty%") || message.isEmpty()) return;

        sender.sendMessage(translate(sender, message));
    }

    public void log(String message) {
        sendCustomMessage(Bukkit.getConsoleSender(), plugin.getSettings().PREFIX + message);
    }
}