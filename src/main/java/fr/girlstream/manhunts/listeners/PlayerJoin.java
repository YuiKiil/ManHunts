package fr.girlstream.manhunts.listeners;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.config.Settings;
import fr.girlstream.manhunts.managers.lang.Lang;
import fr.girlstream.manhunts.managers.lang.LangValue;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import fr.girlstream.manhunts.scordboard.ScordBoard;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {
    private main instance = main.getInstance();
    private Settings settings = instance.getSettings();
    private ScordBoard scordBoard = instance.getScordBoard();

    @EventHandler
    public void onJoin (PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        instance.getUserManager().onLogin(p);

        if(GameState.isState(GameState.LOBBY)){
            e.setJoinMessage(Lang.getPrefix() + "§f" + Lang.PLAYER_JOIN_LOBBY.get().replace(LangValue.PLAYER.toName(), p.getName()));

            p.getInventory().clear();
            p.setGameMode(GameMode.ADVENTURE);
            p.setLevel(0);
            p.setExp(0);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.getActivePotionEffects().forEach(potionEffect ->  p.removePotionEffect(potionEffect.getType()));
            p.teleport(settings.getLobby());
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1f, 1f);


            instance.getTeamManager().onLogout(p);
            instance.getTeamManager().join(p, TeamUnit.NONE);
            instance.getUserManager().onLogin(p);
        }

        FastBoard board = new FastBoard(p);

        board.updateTitle(Lang.SCOREBOARD_TITLE.get());

        scordBoard.addBoards(p.getUniqueId(), board);

    }

    //TODO Quand un joueur quitte en gamestate lobby, supprimer le user, sinon le garder en mettant connected sur false
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(null);
    }

}
