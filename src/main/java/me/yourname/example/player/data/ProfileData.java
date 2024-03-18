package me.yourname.example.player.data;

import lombok.Getter;
import lombok.Setter;
import me.yourname.example.ExamplePlugin;
import me.yourname.example.player.stats.Stat;
import me.yourname.example.utilities.chat.Color;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

@Getter
@Setter
public class ProfileData {

    private final String name;
    private final UUID uuid;

    private Stat points = new Stat();

    public ProfileData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    // Check if the player exists already if not add them to the database then load and cache their data
    public void load(Player player) {
        switch (ExamplePlugin.getInstance().getDataType()) {
            case MYSQL:
                ExamplePlugin.getInstance().getMySQLManager().select("SELECT * FROM ExamplePlugin WHERE uuid=?", resultSet -> {
                    try {
                        if (resultSet.next()) {
                            getPoints().setAmount(resultSet.getLong("points"));
                            ExamplePlugin.getInstance().getMySQLManager().updatePlayerName(player.getUniqueId().toString(), player.getName());
                        } else {
                            ExamplePlugin.getInstance().getMySQLManager().execute("INSERT INTO ExamplePlugin (uuid, name, points) VALUES (?,?,?)",
                                    player.getUniqueId().toString(), player.getName(), 0L);
                        }
                    } catch (SQLException exception) {
                        Color.log("MySQL Error: " + exception.getMessage());
                    }
                }, player.getUniqueId().toString());
                break;
            default:
                ExamplePlugin.getInstance().getSqLiteManager().select("SELECT * FROM ExamplePlugin WHERE uuid=?", resultSet -> {
                    try {
                        if (resultSet.next()) {
                            getPoints().setAmount(resultSet.getLong("points"));
                            ExamplePlugin.getInstance().getSqLiteManager().updatePlayerName(player.getUniqueId().toString(), player.getName());
                        } else {
                            ExamplePlugin.getInstance().getSqLiteManager().execute("INSERT INTO ExamplePlugin (uuid, name, points) VALUES (?,?,?)",
                                    player.getUniqueId().toString(), player.getName(), 0L);
                        }
                    } catch (SQLException exception) {
                        Color.log("SQLite Error: " + exception.getMessage());
                    }
                }, player.getUniqueId().toString());
                break;
        }
    }

    // Save the player to the database
    public void save(Player player) {
        switch (ExamplePlugin.getInstance().getDataType()) {
            case MYSQL:
                ExamplePlugin.getInstance().getMySQLManager().updatePlayerPoints(player.getUniqueId().toString(), getPoints().getAmount());
                break;
            default:
                ExamplePlugin.getInstance().getSqLiteManager().updatePlayerPoints(player.getUniqueId().toString(), getPoints().getAmount());
                break;
        }
    }
}