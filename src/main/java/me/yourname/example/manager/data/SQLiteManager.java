package me.yourname.example.manager.data;

import me.yourname.example.utilities.Tasks;
import me.yourname.example.utilities.chat.Color;
import org.bukkit.Bukkit;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteManager {

    private SQLiteDataSource dataSource;

    public SQLiteManager(String path) {
        Color.log("&eEnabling SQLite support!");

        Exception ex = connect(path);

        if (ex != null) {
            Color.log("&cThere was an error connecting to your database. Here's the suspect: &e" + ex.getLocalizedMessage());
            ex.printStackTrace();
            Bukkit.shutdown();
        } else {
            Color.log("&aManaged to successfully connect to: &e" + path + "&a!");
        }

        createT();
    }

    public void createT() {
        Tasks.runAsync(this::createTables);
    }

    private Exception connect(String path) {
        try {
            Class.forName("org.sqlite.JDBC");
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:" + path);
        } catch (Exception exception) {
            dataSource = null;
            return exception;
        }

        return null;
    }

    public void shutdown() {
        close();
    }

    public void createTables() {
        createTable("ExamplePlugin", "uuid VARCHAR(36) NOT NULL PRIMARY KEY, name VARCHAR(16), points BIGINT(50)");
    }

    public boolean isInitiated() {
        return dataSource != null;
    }

    public void close() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return A new database connecting, provided by the Hikari pool.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Create a new table in the database.
     *
     * @param name The name of the table.
     * @param info The table info between the round VALUES() brackets.
     */
    public void createTable(String name, String info) {
        new Thread(() -> {
            try (Connection resource = getConnection(); PreparedStatement statement = resource.prepareStatement("CREATE TABLE IF NOT EXISTS " + name + "(" + info + ");")) {
                statement.execute();
            } catch (SQLException exception) {
                Color.log("An error occurred while creating database table " + name + ".");
                exception.printStackTrace();
            }
        }).start();
    }

    /**
     * Execute an update to the database.
     *
     * @param query  The statement to the database.
     * @param values The values to be inserted into the statement.
     */
    public void execute(String query, Object... values) {
        new Thread(() -> {
            try (Connection resource = getConnection(); PreparedStatement statement = resource.prepareStatement(query)) {
                for (int i = 0; i < values.length; i++)
                    statement.setObject((i + 1), values[i]);

                statement.execute();
            } catch (SQLException exception) {
                Color.log("An error occurred while executing an update on the database.");
                Color.log("SQLite#execute : " + query);
                exception.printStackTrace();
            }
        }).start();
    }

    /**
     * Execute a query to the database.
     *
     * @param query    The statement to the database.
     * @param callback The data callback (Async).
     * @param values   The values to be inserted into the statement.
     */
    public void select(String query, SelectCall callback, Object... values) {
        new Thread(() -> {
            try (Connection resource = getConnection(); PreparedStatement statement = resource.prepareStatement(query)) {
                for (int i = 0; i < values.length; i++)
                    statement.setObject((i + 1), values[i]);

                callback.call(statement.executeQuery());
            } catch (SQLException exception) {
                Color.log("An error occurred while executing a query on the database.");
                Color.log("SQLite#select : " + query);
                exception.printStackTrace();
            }
        }).start();
    }

    public void updatePlayerPoints(String uuid, long points) {
        execute("UPDATE ExamplePlugin SET points=? WHERE uuid=?", points, uuid);
    }

    public void updatePlayerName(String uuid, String name) {
        execute("UPDATE ExamplePlugin SET name=? WHERE uuid=?", name, uuid);
    }

    public void delete() {
        execute("DELETE FROM ExamplePlugin");
    }

    public void deletePlayer(String uuid) {
        execute("DELETE FROM ExamplePlugin WHERE uuid=?", uuid);
    }
}
