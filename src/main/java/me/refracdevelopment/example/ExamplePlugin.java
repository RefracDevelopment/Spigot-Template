package me.refracdevelopment.example;

import co.aikar.commands.BukkitCommandIssuer;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.ConditionFailedException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import me.refracdevelopment.example.utilities.Settings;
import me.refracdevelopment.example.utilities.VersionCheck;
import me.refracdevelopment.example.utilities.chat.Color;
import me.refracdevelopment.example.utilities.config.Files;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
public final class ExamplePlugin extends JavaPlugin {

    @Getter
    private static ExamplePlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        long startTiming = System.currentTimeMillis();

        Files.loadFiles(this);

        loadCommands();
        Color.log("&aLoaded commands.");
        loadListeners();
        Color.log("&aLoaded listeners.");

        int pluginid = 0;
        new Metrics(this, pluginid);

        Color.log("&aChecking for updates!");
        updateCheck(Bukkit.getConsoleSender(), true);

        Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
        Color.log("&e" + Settings.getName + " has been enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
        Color.log(" &f[*] &6Version&f: &b" + Settings.getVersion);
        Color.log(" &f[*] &6Name&f: &b" + Settings.getName);
        Color.log(" &f[*] &6Author&f: &b" + Settings.getDeveloper);
        Color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");

        if (VersionCheck.isOnePointSevenPlus()) {
            Color.log("&eThis version of Minecraft is extremely outdated and support for it has reached its end of life.");
            Color.log("&eYou will still be able to run " + Settings.getName + " on this Minecraft version, but we are unlikely to provide any further fixes or help with problems specific to legacy Minecraft versions.");
            Color.log("&ePlease consider updating to give your players a better experience and to avoid issues that have long been fixed.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        BukkitCommandManager manager = new BukkitCommandManager(this);
        manager.getCommandConditions().addCondition("noconsole", (context) -> {
            BukkitCommandIssuer issuer = context.getIssuer();
            if (!issuer.isPlayer()) {
                throw new ConditionFailedException("Only players can use this command.");
            }
            return;
        });
        manager.getCommandConditions().addCondition("noplayers", (context) -> {
            BukkitCommandIssuer issuer = context.getIssuer();
            if (issuer.isPlayer()) {
                throw new ConditionFailedException("Only console can use this command.");
            }
            return;
        });
    }

    private void loadListeners() {

    }

    public void updateCheck(CommandSender sender, boolean console) {
        try {
            String urlString = "https://updatecheck.refracdev.ml/";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            StringBuffer response = new StringBuffer();
            while ((input = reader.readLine()) != null) {
                response.append(input);
            }
            reader.close();
            JsonObject object = new JsonParser().parse(response.toString()).getAsJsonObject();

            if (object.has("plugins")) {
                JsonObject plugins = object.get("plugins").getAsJsonObject();
                JsonObject info = plugins.get(Settings.getName).getAsJsonObject();
                String version = info.get("version").getAsString();
                if (version.equals(getDescription().getVersion())) {
                    if (console) {
                        sender.sendMessage(Color.translate("&a" + Settings.getName + " is on the latest version."));
                    }
                } else {
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour " + Settings.getName + " version is out of date!"));
                    sender.sendMessage(Color.translate("&cWe recommend updating ASAP!"));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate("&cYour Version: &e" + Settings.getVersion));
                    sender.sendMessage(Color.translate("&aNewest Version: &e" + version));
                    sender.sendMessage(Color.translate(""));
                    sender.sendMessage(Color.translate(""));
                    return;
                }
                return;
            } else {
                sender.sendMessage(Color.translate("&cWrong response from update API, contact plugin developer!"));
                return;
            }
        } catch (
                Exception ex) {
            sender.sendMessage(Color.translate("&cFailed to get updater check. (" + ex.getMessage() + ")"));
            return;
        }
    }
}
