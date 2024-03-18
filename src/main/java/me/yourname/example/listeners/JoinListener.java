package me.yourname.example.listeners;

import me.yourname.example.ExamplePlugin;
import me.yourname.example.player.Profile;
import me.yourname.example.utilities.Tasks;
import me.yourname.example.utilities.chat.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPreLogin(PlayerLoginEvent event) {
        ExamplePlugin.getInstance().getProfileManager().handleProfileCreation(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = ExamplePlugin.getInstance().getProfileManager().getProfile(player.getUniqueId());

        Tasks.runAsync(() -> profile.getData().load(player));

        if (profile == null || profile.getData() == null) {
            player.kickPlayer(Color.translate(ExamplePlugin.getInstance().getLocaleFile().getString("kick-messages-error")));
            return;
        }

        // Anything else goes here once player data is loaded
    }

    @EventHandler
    public void onReload(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!event.getMessage().equalsIgnoreCase("/reload confirm"))
            return;

        Color.sendCustomMessage(player, "&cUse of /reload is not recommended as it can cause issues often cases. Please restart your server when possible.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = ExamplePlugin.getInstance().getProfileManager().getProfile(player.getUniqueId());

        if (profile == null)
            return;
        if (profile.getData() == null)
            return;

        Tasks.runAsync(() -> profile.getData().save(player));
        ExamplePlugin.getInstance().getProfileManager().getProfiles().remove(player.getUniqueId());
    }
}