package me.refracdevelopment.example.manager.data.sql;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.refracdevelopment.example.manager.data.sql.entities.PlayerPoints;
import me.refracdevelopment.example.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLiteManager {

    private final Dao<PlayerPoints, String> playerPointsDao;

    public SQLiteManager(String path) throws SQLException, ClassNotFoundException {
        Color.log("&aConnecting to SQLite...");
        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + path);
        TableUtils.createTableIfNotExists(connectionSource, PlayerPoints.class);
        playerPointsDao = DaoManager.createDao(connectionSource, PlayerPoints.class);
        Color.log("&aConnected to SQLite!");
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

    public void updatePlayerPoints(UUID uuid, long points) throws SQLException {
        PlayerPoints playerPoints = playerPointsDao.queryForId(uuid.toString());
        if (playerPoints != null) {
            playerPoints.setPoints(points);
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