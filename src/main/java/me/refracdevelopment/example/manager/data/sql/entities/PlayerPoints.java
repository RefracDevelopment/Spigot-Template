package me.refracdevelopment.example.manager.data.sql.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ExamplePlugin")
public class PlayerPoints {

    @DatabaseField(id = true)
    private String uuid;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private long points;

    public PlayerPoints() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}