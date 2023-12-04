package fr.girlstream.manhunts.managers.users;

import org.bukkit.entity.Player;

import java.util.UUID;

public class User {

    private final UUID uuid;
    private final String name;
    private boolean alive;
    private int kills;
    private boolean connected;
    private boolean inGame;


    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.alive = true;
        this.kills = 0;
        this.connected = true;
        this.inGame = false;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getKills() {
        return kills;
    }

    public void addKills() {
        this.kills++;
    }
}
