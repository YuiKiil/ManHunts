package fr.girlstream.manhunts.managers.users;

import org.bukkit.entity.Player;

import java.util.UUID;

public class User {

    private final UUID uuid;
    private final String name;
    private boolean alive;
    private int kills;


    public User(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.alive = true;
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

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setTrueAlive() { this.alive = true; }

    public int getKills() {
        return kills;
    }

    public void addKills() {
        this.kills++;
    }
}
