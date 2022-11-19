package fr.girlstream.manhunts.managers.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Settings {
    // General
    private boolean spec;
    private boolean itemTeam;
    private boolean createNewMap;
    private String gameMap;
    private boolean uhc;

    // Game
    private boolean respawnAtDeath;
    private LocationConfig spawnAtStart;
    private boolean createGlassPlatform;
    private int timeBeforeReleaseHunters;

    // Hunter Settings
    private boolean respawnHunter;
    private boolean respawnHunterCondition;
    private String hunterCondition;

    private boolean compass;
    private String compassMode;
    private int timesInterval;

    // Runner Settings
    private boolean respawnRunner;
    private boolean respawnRunnerCondition;
    private String runnerCondition;

    // Lobby
    private LocationConfig Lobby;


    public boolean isSpec() {
        return spec;
    }

    public void setSpec(boolean spec) {
        this.spec = spec;
    }

    public boolean isItemTeam() {
        return itemTeam;
    }

    public void setItemTeam(boolean itemTeam) {
        this.itemTeam = itemTeam;
    }

    public boolean isCreateNewMap() {
        return createNewMap;
    }

    public void setCreateNewMap(boolean createNewMap) {
        this.createNewMap = createNewMap;
    }

    public World getGameMap() {
        return Bukkit.getWorld(gameMap);
    }

    public void setGameMap(String gameMap) {
        this.gameMap = gameMap;
    }

    public boolean isUhc() {
        return uhc;
    }

    public void setUhc(boolean uhc) {
        this.uhc = uhc;
    }

    public boolean isRespawnAtDeath() {
        return respawnAtDeath;
    }

    public void setRespawnAtDeath(boolean respawnAtDeath) {
        this.respawnAtDeath = respawnAtDeath;
    }

    public Location getSpawnAtStart() {
        return spawnAtStart.toBukkitLocation(gameMap);
    }

    public void setSpawnAtStart(LocationConfig spawnAtStart) {
        this.spawnAtStart = spawnAtStart;
    }

    public boolean isCreateGlassPlatform() {
        return createGlassPlatform;
    }

    public void setCreateGlassPlatform(boolean createGlassPlatform) {
        this.createGlassPlatform = createGlassPlatform;
    }

    public int getTimeBeforeReleaseHunters() {
        return timeBeforeReleaseHunters;
    }

    public void setTimeBeforeReleaseHunters(int timeBeforeReleaseHunters) {
        this.timeBeforeReleaseHunters = timeBeforeReleaseHunters;
    }

    public boolean isRespawnHunter() {
        return respawnHunter;
    }

    public void setRespawnHunter(boolean respawnHunter) {
        this.respawnHunter = respawnHunter;
    }

    public boolean isRespawnHunterCondition() {
        return respawnHunterCondition;
    }

    public void setRespawnHunterCondition(boolean respawnHunterCondition) {
        this.respawnHunterCondition = respawnHunterCondition;
    }

    public String getHunterCondition() {
        return hunterCondition;
    }

    public void setHunterCondition(String hunterCondition) {
        this.hunterCondition = hunterCondition;
    }

    public boolean isCompass() {
        return compass;
    }

    public void setCompass(boolean compass) {
        this.compass = compass;
    }

    public String getCompassMode() {
        return compassMode;
    }

    public void setCompassMode(String compassMode) {
        this.compassMode = compassMode;
    }

    public int getTimesInterval() {
        return timesInterval;
    }

    public void setTimesInterval(int timesInterval) {
        this.timesInterval = timesInterval;
    }

    public boolean isRespawnRunner() {
        return respawnRunner;
    }

    public void setRespawnRunner(boolean respawnRunner) {
        this.respawnRunner = respawnRunner;
    }

    public boolean isRespawnRunnerCondition() {
        return respawnRunnerCondition;
    }

    public void setRespawnRunnerCondition(boolean respawnRunnerCondition) {
        this.respawnRunnerCondition = respawnRunnerCondition;
    }

    public String getRunnerCondition() {
        return runnerCondition;
    }

    public void setRunnerCondition(String runnerCondition) {
        this.runnerCondition = runnerCondition;
    }

    public int getKillCondition() {
        return killCondition;
    }

    public void setKillCondition(int killCondition) {
        this.killCondition = killCondition;
    }

    public Location getLobby() {
        return Lobby.toBukkitLocation();
    }

    public void setLobby(LocationConfig lobby) {
        Lobby = lobby;
    }

    private int killCondition;



}
