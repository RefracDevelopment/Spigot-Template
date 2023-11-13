package me.refracdevelopment.example.manager.data.sql;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.refracdevelopment.example.ExamplePlugin;
import me.refracdevelopment.example.manager.data.sql.entities.PlayerPoints;
import me.refracdevelopment.example.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySQLManager {

    private final Dao<PlayerPoints, String> playerPointsDao;

    public MySQLManager() throws ClassNotFoundException, SQLException {
        Color.log("&aConnecting to MySQL...");
        String host = ExamplePlugin.getInstance().getConfigFile().getString("mysql.host");
        String username = ExamplePlugin.getInstance().getConfigFile().getString("mysql.username");
        String password = ExamplePlugin.getInstance().getConfigFile().getString("mysql.password");
        String database = ExamplePlugin.getInstance().getConfigFile().getString("mysql.database");
        String port = ExamplePlugin.getInstance().getConfigFile().getString("mysql.port");
        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mariadb://" +
                host + ':' + port + '/' + database, username, password);
        TableUtils.createTableIfNotExists(connectionSource, PlayerPoints.class);
        playerPointsDao = DaoManager.createDao(connectionSource, PlayerPoints.class);
        Color.log("&aConnected to MySQL!");
    }

    public PlayerPoints addPlayer(Player player) throws SQLException {
        PlayerPoints playerPoints = new PlayerPoints();
        playerPoints.setUuid(player.getUniqueId().toString());
        playerPoints.setName(player.getName());
        playerPointsDao.create(playerPoints);
        return playerPoints;
    }

    public void deletePlayer(UUID uuid) throws SQLException {
        playerPointsDao.deleteById(uuid.toString());
    }

    public void delete() throws SQLException {
        List<PlayerPoints> playerPointsList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerPointsList.add(getPlayerPoints(player.getUniqueId()));
        }
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            playerPointsList.add(getPlayerPoints(player.getUniqueId()));
        }
        playerPointsDao.delete(playerPointsList);
    }

    public boolean playerExists(UUID uuid) throws SQLException {
        return playerPointsDao.idExists(uuid.toString());
    }

    public void updatePlayerPoints(UUID uuid, long gems) throws SQLException {
        PlayerPoints playerPoints = playerPointsDao.queryForId(uuid.toString());
        if (playerPoints != null) {
            playerPoints.setPoints(gems);
            playerPointsDao.update(playerPoints);
        }
    }

    public void updatePlayerName(UUID uuid, String name) throws SQLException {
        PlayerPoints playerPoints = playerPointsDao.queryForId(uuid.toString());
        if (playerPoints != null) {
            playerPoints.setName(name);
            playerPointsDao.update(playerPoints);
        }
    }

    public PlayerPoints getPlayerPoints(UUID uuid) throws SQLException {
        return playerPointsDao.queryForId(uuid.toString());
    }

    public List<PlayerPoints> getAllPlayers() throws SQLException {
        List<PlayerPoints> playerPointsList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerPointsList.add(getPlayerPoints(player.getUniqueId()));
        }
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            playerPointsList.add(getPlayerPoints(player.getUniqueId()));
        }
        return playerPointsList;
    }

}