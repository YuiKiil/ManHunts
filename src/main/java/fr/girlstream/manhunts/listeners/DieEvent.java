package fr.girlstream.manhunts.listeners;

import fr.girlstream.manhunts.game.Game;
import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.teams.Team;
import fr.girlstream.manhunts.managers.teams.TeamManager;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import fr.girlstream.manhunts.managers.users.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Optional;

public class DieEvent implements Listener {
    private main instance = main.getInstance();
    private TeamManager teamManager = instance.getTeamManager();

    @EventHandler
    public void onEntityDie(EntityDeathEvent e){
        Entity entity = e.getEntity();

        if(GameState.isState(GameState.IN_GAME_PVP, GameState.IN_GAME_NO_PVP) && entity instanceof EnderDragon){
            instance.getGame().setAliveDragon(false);
            instance.getGame().checkWin();
        }
    }
    @EventHandler
    public void onPlayerDie(PlayerDeathEvent e){
        // TODO set death message with lang.yaml
        Player player = e.getEntity().getPlayer();
        Location deathLoc = e.getEntity().getLocation();
        Optional<User> user = instance.getUserManager().getUser(player);
        if(!GameState.isState(GameState.IN_GAME_PVP, GameState.IN_GAME_NO_PVP)){
            return;
        }

        if(instance.getTeamManager().getPlayerTeam(player) == TeamUnit.RUNNERS){
            for(Player player1 : Bukkit.getOnlinePlayers()){
                player1.playSound(player1.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH, 1f , 1f );
            }
            player.setGameMode(GameMode.SPECTATOR);
            player.setBedSpawnLocation(deathLoc);
            player.teleport(deathLoc);
            instance.getGame().checkWin();

        }
        if(instance.getTeamManager().getPlayerTeam(player) == TeamUnit.HUNTERS){ // TODO respawn hunter condition
            if(instance.getSettings().isRespawnHunterCondition() && instance.getSettings().getHunterCondition() == "normal"){
                // TODO respawn hunters
            }
        }
    }

}
