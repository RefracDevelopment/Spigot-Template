package me.refracdevelopment.example.utilities.chat;

import dev.rosewood.rosegarden.utils.HexUtils;
import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.manager.LocaleManager;
import org.bukkit.Bukkit;

public class Color {

    public static String translate(String source) {
        return HexUtils.colorify(source);
    }

    public static void log(String message) {
        final LocaleManager locale = ExamplePlugin.getInstance().getManager(LocaleManager.class);

        String prefix = locale.getLocaleMessage("prefix");

        locale.sendCustomMessage(Bukkit.getConsoleSender(), prefix +  message);
    }
}