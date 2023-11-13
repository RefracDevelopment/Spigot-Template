package me.refracdevelopment.example.listeners;

import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.player.Profile;
import me.refracdevelopment.example.utilities.Tasks;
import me.refracdevelopment.example.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.UUID;

public class JoinListener implements Listener {

    private final UUID getDevUUID = UUID.fromString("d9c670ed-d7d5-45fb-a144-8b8be86c4a2d");
    private final UUID getDevUUID2 = UUID.fromString("ab898e40-9088-45eb-9d69-e0b78e872627");

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        ExamplePlugin.getInstance().getProfileManager().handleProfileCreation(event.getUniqueId(), event.getName());
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Profile profile = ExamplePlugin.getInstance().getProfileManager().getProfile(event.getPlayer().getUniqueId());

        if (profile == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Color.translate(ExamplePlugin.getInstance().getLocaleFile().getString("kick-messages-error")));
            return;
        }

        Tasks.runAsync(wrappedTask -> {
            profile.getData().load();

            Tasks.run(wrappedTask1 -> {
                if (profile.getData() == null) {
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Color.translate(ExamplePlugin.getInstance().getLocaleFile().getString("kick-messages-error")));
                }
            });
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = ExamplePlugin.getInstance().getProfileManager().getProfile(player.getUniqueId());

        if (profile == null || profile.getData() == null) {
            player.kickPlayer(Color.translate(ExamplePlugin.getInstance().getLocaleFile().getString("kick-messages-error")));
            return;
        }

        if (player.getUniqueId().equals(getDevUUID)) {
            sendDevMessage(player);
        } else if (player.getUniqueId().equals(getDevUUID2)) {
            sendDevMessage(player);
        }
    }

    @EventHandler
    public void onReload(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!event.getMessage().equalsIgnoreCase("/reload confirm")) return;

        Color.sendCustomMessage(player, "&cUse of /reload is not recommended as it can cause issues often cases. Please restart your server when possible.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = ExamplePlugin.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile == null) return;
        if (profile.getData() == null) return;

        Tasks.runAsync(wrappedTask -> profile.getData().save());
        ExamplePlugin.getInstance().getProfileManager().getProfiles().remove(player.getUniqueId());
    }

    public void sendDevMessage(Player player) {
        player.sendMessage("");
        Color.sendCustomMessage(player, "&aWelcome " + ExamplePlugin.getInstance().getDescription().getName() + " Developer!");
        Color.sendCustomMessage(player, "&aThis server is currently running " + ExamplePlugin.getInstance().getDescription().getName() + " &bv" + ExamplePlugin.getInstance().getDescription().getVersion() + "&a.");
        Color.sendCustomMessage(player, "&aPlugin name&7: &f" + ExamplePlugin.getInstance().getDescription().getName());
        player.sendMessage("");
        Color.sendCustomMessage(player, "&aServer version&7: &f" + Bukkit.getVersion());
        player.sendMessage("");
    }
}