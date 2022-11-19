package fr.girlstream.manhunts.managers.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationConfig {
    private String world;
    private double x, y, z;
    private float yaw, pitch;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setWorld(World world){
        setWorld(world.getName());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public Location toBukkitLocation(){
        return toBukkitLocation(world);
    }


    public Location toBukkitLocation(String gameWorld){
        return new Location(Bukkit.getWorld(gameWorld), x, y, z);
    }
}
