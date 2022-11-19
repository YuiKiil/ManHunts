package fr.girlstream.manhunts.game;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import fr.girlstream.manhunts.tools.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {
    private main instance = main.getInstance();
    private int time = 0;

    @Override
    public void run() {

        if(time == instance.getSettings().getTimeBeforeReleaseHunters()){ // Release hunter
            for(Player player : Bukkit.getOnlinePlayers()){
                if(instance.getTeamManager().getPlayerTeam(player) == TeamUnit.HUNTERS){
                    player.getActivePotionEffects().forEach(potionEffect ->  player.removePotionEffect(potionEffect.getType()));
                }
            }

            Title title = new Title("§4Attentions", "§cLes Hunters sont libérés ");
            title.send(Bukkit.getOnlinePlayers(), 1, 3, 1);
            GameState.setState(GameState.IN_GAME_PVP);
            instance.getSettings().getGameMap().setPVP(true);
        }

        instance.getGame().getTimers().add1ToTimer();

        time++;
    }
}
