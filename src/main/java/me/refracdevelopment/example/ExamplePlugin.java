package me.refracdevelopment.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import me.refracdevelopment.example.commands.ReloadCommand;
import me.refracdevelopment.example.commands.VersionCommand;
import me.refracdevelopment.example.config.ConfigFile;
import me.refracdevelopment.example.config.cache.Commands;
import me.refracdevelopment.example.config.cache.Config;
import me.refracdevelopment.example.listeners.JoinListener;
import me.refracdevelopment.example.utilities.ReflectionUtils;
import me.refracdevelopment.example.utilities.chat.Color;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Getter
public final class ExamplePlugin extends JavaPlugin {

    private ConfigFile configFile;
    private ConfigFile commandsFile;

    private Config settings;
    private Commands commands;

    private Color color;

    @Override
    public void onEnable() {
        // Plugin startup logic
        long startTiming = System.currentTimeMillis();
        PluginManager pluginManager = this.getServer().getPluginManager();

        loadFiles();

        // Replace with your metrics plugin id
        new Metrics(this, -1);
        this.color = new Color(this);

        // Check if the server is on 1.7
        if (ReflectionUtils.MINOR_NUMBER <= 7) {
            color.log("&c" + getDescription().getName() + " 1.7 is in legacy mode, please update to 1.8+");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Make sure the server has PlaceholderAPI
        if (pluginManager.getPlugin("PlaceholderAPI") == null) {
            color.log("&cPlease install PlaceholderAPI onto your server to use this plugin.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        loadManagers();

        loadCommands();
        loadListeners();

        color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");
        color.log("&e" + this.getDescription().getName() + " has been enabled. (" + (System.currentTimeMillis() - startTiming) + "ms)");
        color.log(" &f[*] &6Version&f: &b" + this.getDescription().getVersion());
        color.log(" &f[*] &6Name&f: &b" + this.getDescription().getName());
        color.log(" &f[*] &6Author&f: &b" + this.getDescription().getAuthors().get(0));
        color.log("&8&m==&c&m=====&f&m======================&c&m=====&8&m==");

        updateCheck(Bukkit.getConsoleSender(), true);
    }

    @Override
    public void onDisable() {
        // unused
    }

    private void loadFiles() {
        // Files
        configFile = new ConfigFile(this, "config.yml");
        commandsFile = new ConfigFile(this, "commands.yml");

        // Cache
        settings = new Config(this);
        commands = new Commands(this);
    }

    public void reloadFiles() {
        // Files
        configFile.reload();
        commandsFile.reload();

        // Cache
        settings.loadConfig();
        commands.loadConfig();
    }

    private void loadManagers() {
        color.log("&aLoaded managers.");
    }

    private void loadCommands() {
        try {
            CommandManager.createCoreCommand(this, commands.EXAMPLE_COMMAND_ALIASES.get(0), "Example plugin",
                    "/" + commands.EXAMPLE_COMMAND_ALIASES.get(0), new CommandList() {
                        @Override
                        public void displayCommandList(CommandSender commandSender, List<SubCommand> list) {
                            color.sendCustomMessage(commandSender, "&e---------------------");
                            list.forEach(subCommand -> {
                                color.sendCustomMessage(commandSender, "&d" + subCommand.getSyntax() + " &7- " + subCommand.getDescription());
                            });
                            color.sendCustomMessage(commandSender, "&e---------------------");
                        }
                    }, commands.EXAMPLE_COMMAND_ALIASES,
                    VersionCommand.class,
                    ReloadCommand.class
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        color.log("&aLoaded commands.");
    }

    private void loadListeners() {
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        color.log("&aLoaded listeners.");
    }

    public void updateCheck(CommandSender sender, boolean console) {
        color.log("&aChecking for updates!");
        try {
            String urlString = "https://refracdev-updatecheck.refracdev.workers.dev/";
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
                JsonObject info = plugins.get(this.getDescription().getName()).getAsJsonObject();
                String version = info.get("version").getAsString();
                if (version.equals(this.getDescription().getVersion())) {
                    if (console) {
                        sender.sendMessage(color.translate("&a" + this.getDescription().getName() + " is on the latest version."));
                    }
                } else {
                    sender.sendMessage(color.translate(""));
                    sender.sendMessage(color.translate(""));
                    sender.sendMessage(color.translate("&cYour " + this.getDescription().getName() + " version is out of date!"));
                    sender.sendMessage(color.translate("&cWe recommend updating ASAP!"));
                    sender.sendMessage(color.translate(""));
                    sender.sendMessage(color.translate("&cYour Version: &e" + this.getDescription().getVersion()));
                    sender.sendMessage(color.translate("&aNewest Version: &e" + version));
                    sender.sendMessage(color.translate(""));
                    sender.sendMessage(color.translate(""));
                    return;
                }
                return;
            } else {
                sender.sendMessage(color.translate("&cWrong response from update API, contact plugin developer!"));
                return;
            }
        } catch (
                Exception ex) {
            sender.sendMessage(color.translate("&cFailed to get updater check. (" + ex.getMessage() + ")"));
            return;
        }
    }
}
