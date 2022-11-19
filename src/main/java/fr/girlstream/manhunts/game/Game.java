package fr.girlstream.manhunts.game;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.lang.Lang;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import fr.girlstream.manhunts.managers.users.User;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Game {

    private final main instance = main.getInstance();
    private StartRunnable startRunnable;
    private GameRunnable gameRunnable;
    private boolean aliveDragon;
    private Timers timers;


    public Game() {
        this.startRunnable = new StartRunnable(this);
        this.gameRunnable = new GameRunnable();
        this.aliveDragon = true;
    }

    public void startCountdown(){
        startRunnable.runTaskTimer(instance, 0, 20L);
        startRunnable.setStarting(true);
    }

    public void stopCountdown(){
        if(startRunnable.getCountdown() > 0){
            startRunnable.cancel();
            startRunnable = new StartRunnable(this);
        }
    }

    public void startGame(){
        timers = new Timers();
        for(Player player : Bukkit.getOnlinePlayers()){
            User user = new User(player);
            player.teleport(instance.getSettings().getSpawnAtStart());
            PotionEffect slowPotionEffect = new PotionEffect(PotionEffectType.SLOW, 9999, 255);
            PotionEffect blindPotionEffect = new PotionEffect(PotionEffectType.BLINDNESS, 9999, 255);
            PotionEffect jumpPotionEffect = new PotionEffect(PotionEffectType.JUMP, 9999, 255);
            PotionEffect resistancePotionEffect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 255);
            if(instance.getTeamManager().getPlayerTeam(player) == TeamUnit.HUNTERS){
                player.addPotionEffect(slowPotionEffect);
                player.addPotionEffect(blindPotionEffect);
                player.addPotionEffect(jumpPotionEffect);
                player.addPotionEffect(resistancePotionEffect);
                player.sendMessage(Lang.getPrefix() + "§fTu restera bloquer encore §c§l" + instance.getSettings().getTimeBeforeReleaseHunters() + " secondes !"); //TODO Message in Lang.yml
            } else if(instance.getTeamManager().getPlayerTeam(player) == TeamUnit.RUNNERS){
                continue;
            }

        }
        gameRunnable.runTaskTimer(instance, 0, 20L);
        GameState.setState(GameState.IN_GAME_NO_PVP);
        timers = new Timers();
    }

    public void checkWin(){
        if(!isAliveDragon() || !instance.getTeamManager().isTeamAlive(TeamUnit.HUNTERS)){
            win(TeamUnit.RUNNERS);
        }
        if(!instance.getTeamManager().isTeamAlive(TeamUnit.RUNNERS)){
            win(TeamUnit.HUNTERS);
        }

    }

    public void win(TeamUnit teamUnit){
// TODO win message in Lang.yml + title
    }

    public StartRunnable getStartRunnable() {
        return startRunnable;
    }

    public GameRunnable getGameRunnable() {
        return gameRunnable;
    }

    public boolean isAliveDragon() {
        return aliveDragon;
    }

    public void setAliveDragon(boolean aliveDragon) {
        this.aliveDragon = aliveDragon;
    }

    public Map<Player, Boolean> getUsers() {
        return users;
    }

    public Timers getTimers() {
        return timers;
    }
}
