package me.refracdevelopment.example.player.data;

import lombok.Getter;
import lombok.Setter;
import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.manager.data.DataType;
import me.refracdevelopment.example.manager.data.sql.entities.PlayerPoints;
import me.refracdevelopment.example.player.stats.Stat;
import me.refracdevelopment.example.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class ProfileData {

    private final String name;
    private final UUID uuid;

    private Stat stat = new Stat();

    public ProfileData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    // Check if the player exists already if not add them to the database then load and cache their data
    public void load() {
        if (Objects.requireNonNull(ExamplePlugin.getInstance().getDataType()) == DataType.MYSQL) {
            try {
                PlayerPoints playerPoints = ExamplePlugin.getInstance().getMySQLManager().getPlayerPoints(uuid);

                if (playerPoints == null) {
                    ExamplePlugin.getInstance().getMySQLManager().addPlayer(getPlayer());
                }

                stat.setAmount(playerPoints.getPoints());
                ExamplePlugin.getInstance().getMySQLManager().updatePlayerName(uuid, name);
            } catch (SQLException exception) {
                Color.log("&cMySQL Error: " + exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            try {
                PlayerPoints playerPoints = ExamplePlugin.getInstance().getSqLiteManager().getPlayerPoints(uuid);

                if (playerPoints == null) {
                    ExamplePlugin.getInstance().getSqLiteManager().addPlayer(getPlayer());
                }

                stat.setAmount(playerPoints.getPoints());
                ExamplePlugin.getInstance().getSqLiteManager().updatePlayerName(uuid, name);
            } catch (SQLException exception) {
                Color.log("&cSQLite Error: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
    }

    // Save the player to the database
    public void save() {
        if (Objects.requireNonNull(ExamplePlugin.getInstance().getDataType()) == DataType.MYSQL) {
            try {
                ExamplePlugin.getInstance().getMySQLManager().updatePlayerPoints(uuid, stat.getAmount());
            } catch (SQLException exception) {
                Color.log("&cMySQL Error: " + exception);
                exception.printStackTrace();
            }
        } else {
            try {
                ExamplePlugin.getInstance().getSqLiteManager().updatePlayerPoints(uuid, stat.getAmount());
            } catch (SQLException exception) {
                Color.log("&cSQLite Error: " + exception);
                exception.printStackTrace();
            }
        }
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

}