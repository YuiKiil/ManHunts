package fr.girlstream.manhunts.listeners;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.lang.Lang;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    private main instance = main.getInstance();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        TeamUnit teamUnit = instance.getTeamManager().getPlayerTeam(player);
        if(teamUnit == TeamUnit.HUNTERS){
            e.setFormat(Lang.TEAM_HUNTERS_PREFIX.get() + " " + Lang.TEAM_HUNTERS_COLOR.get() + player.getName() + " >> §f" + e.getMessage());
            return;
        }
        if(teamUnit == TeamUnit.RUNNERS){
            e.setFormat(Lang.TEAM_RUNNERS_PREFIX.get() + " " + Lang.TEAM_RUNNERS_COLOR.get() + player.getName() + " >> §f" + e.getMessage());
            return;
        }
        e.setFormat(player.getDisplayName() + " >> §f" + e.getMessage());
    }
}
