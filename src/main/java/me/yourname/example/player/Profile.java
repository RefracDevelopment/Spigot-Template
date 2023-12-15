package me.yourname.example.player;

import lombok.Getter;
import lombok.Setter;
import me.yourname.example.player.data.ProfileData;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private ProfileData data;
    private UUID UUID;
    private String playerName;

    public Profile(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
        this.data = new ProfileData(getUUID(), getPlayerName());
    }
}