package fr.girlstream.manhunts.scordboard;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.lang.Lang;
import fr.girlstream.manhunts.managers.lang.LangValue;
import fr.girlstream.manhunts.managers.teams.TeamManager;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScordBoard implements Listener {

    //TODO updateLobbyBord (player counter in each team + waiting time)
    //TODO updateInGameBord (Scordbord per team with teamkill, personnal kill, enemy remaining and time spend)
    //TODO updateEndBord (ScordBord with teamkill for each team and winner team + winning condition ex: "runner beat dragon" / "hunter kill runners")

    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private main instance = main.getInstance();

    public void addBoards(UUID uuid, FastBoard boards){
        this.boards.put(uuid, boards);
    }

    public Map<UUID, FastBoard> getBoards() {
        return boards;
    }

    public void EnableScore(){
        instance.getServer().getScheduler().runTaskTimer(instance, () -> {
            if(GameState.isState(GameState.LOBBY)){
                for (FastBoard board : this.boards.values()) {
                    updateLobbyBoard(board);
                }
            }
            if(GameState.isState(GameState.IN_GAME_PVP, GameState.IN_GAME_NO_PVP)){
                for (FastBoard board : this.boards.values()) {
                    updateInGameBoard(board);
                }
            }
        }, 0, 20);
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateLobbyBoard(FastBoard board){
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Player player = board.getPlayer();
        TeamUnit teamUnit = instance.getTeamManager().getPlayerTeam(player);
        board.updateLines(Lang.SCOREBOARD_LOBBY.get().replace(LangValue.TEAM.toName(), teamUnit.getColoredName()).replace(LangValue.ONLINE_PLAYER.toName(), toString().valueOf(players.size())).replace(LangValue.MAX_PLAYER.toName(), toString().valueOf(Bukkit.getMaxPlayers())).split("\n"));

    }

    private void updateInGameBoard(FastBoard board){
        Player player = board.getPlayer();
        TeamUnit teamUnit = instance.getTeamManager().getPlayerTeam(player);
        TeamManager teamManager = instance.getTeamManager();

        if(GameState.isState(GameState.IN_GAME_PVP)){
            board.updateLines(Lang.SCOREBOARD_INGAME.get()
                    .replace(LangValue.TEAM.toName(), teamUnit.getColoredName()).replace(LangValue.PLAYER_KILL.toName(), "not code")
                    .replace(LangValue.TIMER.toName(), String.valueOf(instance.getGame().getTimers().getFormatTimer()))
                    .replace(LangValue.PVP.toName(), Lang.PVP_STATE_ACTIVE.get())
                    .replace(LangValue.NB_RUNNER_ALIVE.toName(), String.valueOf(teamManager.getNbTeamAlive(TeamUnit.RUNNERS)))
                    .replace(LangValue.NB_HUNTER_ALIVE.toName(), String.valueOf(teamManager.getNbTeamAlive(TeamUnit.HUNTERS)))
                    .split("\n"));
        } else if(GameState.isState(GameState.IN_GAME_NO_PVP)){
            board.updateLines(Lang.SCOREBOARD_INGAME.get()
                    .replace(LangValue.TEAM.toName(), teamUnit.getColoredName()).replace(LangValue.PLAYER_KILL.toName(), "not code")
                    .replace(LangValue.TIMER.toName(), String.valueOf(instance.getGame().getTimers().getFormatTimer()))
                    .replace(LangValue.PVP.toName(), Lang.PVP_STATE_NO_ACTIVE.get())
                    .replace(LangValue.NB_RUNNER_ALIVE.toName(), String.valueOf(teamManager.getNbTeamAlive(TeamUnit.RUNNERS)))
                    .replace(LangValue.NB_HUNTER_ALIVE.toName(), String.valueOf(teamManager.getNbTeamAlive(TeamUnit.HUNTERS)))
                    .split("\n"));

        }
    }

}
