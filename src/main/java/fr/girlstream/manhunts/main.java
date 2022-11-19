package fr.girlstream.manhunts;

import fr.girlstream.manhunts.game.Game;
import fr.girlstream.manhunts.managers.HuntFiles;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.Registration;
import fr.girlstream.manhunts.managers.config.Settings;
import fr.girlstream.manhunts.managers.teams.TeamManager;
import fr.girlstream.manhunts.managers.users.User;
import fr.girlstream.manhunts.managers.users.UserManager;
import fr.girlstream.manhunts.scordboard.ScordBoard;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class main extends JavaPlugin {
    private static main instance;
    private Settings settings;
    private TeamManager teamManager;
    private UserManager userManager;
    private User user;
    private Game game;
    private ScordBoard scordBoard;


    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("[ManHunts] Plugin Starting ...");

        HuntFiles config = HuntFiles.CONFIG;
        HuntFiles lang = HuntFiles.LANG;
        config.create(getLogger());
        lang.create(getLogger());

        try (final Reader reader = Files.newBufferedReader(config.getFile().toPath(), StandardCharsets.UTF_8)){

            Yaml yaml = new Yaml(new CustomClassLoaderConstructor(getClassLoader()));
            yaml.setBeanAccess(BeanAccess.FIELD);

            settings = yaml.loadAs(reader, Settings.class);
            getLogger().info("Configuration Loaded !");

        } catch(IOException e){
            e.printStackTrace();
        }

        teamManager = new TeamManager();
        userManager = new UserManager();
        game = new Game();
        scordBoard = new ScordBoard();


        GameState.setState(GameState.LOBBY);
        Registration.register();


        settings.getGameMap().setDifficulty(Difficulty.HARD);
        settings.getGameMap().setPVP(false);
        settings.getGameMap().setTime(6000L);
        settings.getGameMap().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        WorldBorder worldBorder = settings.getGameMap().getWorldBorder();
        worldBorder.setCenter(0,0);
        worldBorder.setSize(4000);
        worldBorder.setDamageAmount(2);
        worldBorder.setDamageBuffer(6);
        worldBorder.setWarningDistance(5);

        getLogger().info("[ManHunts] GameMap prepared !");

        scordBoard.EnableScore();
    }


    public static main getInstance() {
        return instance;
    }

    public Settings getSettings() {
        return settings;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public Game getGame() {
        return game;
    }

    public UserManager getUserManager(){
        return userManager;
    }

    public ScordBoard getScordBoard() {
        return scordBoard;
    }
}
