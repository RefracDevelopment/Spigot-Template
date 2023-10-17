package me.refracdevelopment.example.listeners;

import me.refracdevelopment.example.ExamplePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    public final UUID getDevUUID = UUID.fromString("d9c670ed-d7d5-45fb-a144-8b8be86c4a2d");
    public final UUID getDevUUID2 = UUID.fromString("ab898e40-9088-45eb-9d69-e0b78e872627");
    
    private final ExamplePlugin plugin;
    
    public JoinListener(ExamplePlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getUniqueId().equals(getDevUUID)) {
            sendDevMessage(player);
        } else if (player.getUniqueId().equals(getDevUUID2)) {
            sendDevMessage(player);
        }
    }

    public void sendDevMessage(Player player) {
        player.sendMessage("");
        plugin.getColor().sendCustomMessage(player, "&aWelcome " + plugin.getDescription().getName() + " Developer!");
        plugin.getColor().sendCustomMessage(player, "&aThis server is currently running " + plugin.getDescription().getName() + " &bv" + plugin.getDescription().getVersion() + "&a.");
        plugin.getColor().sendCustomMessage(player, "&aPlugin name&7: &f" + plugin.getDescription().getName());
        player.sendMessage("");
        plugin.getColor().sendCustomMessage(player, "&aServer version&7: &f" + Bukkit.getVersion());
        player.sendMessage("");
    }
}