package fr.girlstream.manhunts.managers.teams;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.users.User;
import fr.girlstream.manhunts.scordboard.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.stream.Collectors;

public class TeamManager {
    private main instance = main.getInstance();

    private final TeamHandler teamHandler;
    private final Team hunters;
    private final Team runners;

    public TeamManager() {
        this.teamHandler = new TeamHandler();
        this.hunters = new Team(TeamUnit.HUNTERS);
        this.runners = new Team(TeamUnit.RUNNERS);

        for(TeamUnit team : TeamUnit.values()){
            if(teamHandler.getTeam(team) != null)
                continue;

            TeamHandler.VTeam vt = teamHandler.createNewTeam(team.getName(), team.getColoredName());
            vt.setRealName(team.getName());
            vt.setPrefix(team.getPrefix());

            teamHandler.addTeam(vt);
        }
    }

    public void onLogout(Player player){
        teamHandler.removeReceiver(player);
    }

    public void join(Player player, TeamUnit teamUnit){
        leave(player);

        Team team = getTeam(teamUnit);
        if(team != null){
            team.getPlayers().add(player.getUniqueId());

        }

        teamHandler.addReceiver(player);
        teamHandler.addPlayerToTeam(player, teamHandler.getTeam(teamUnit));
    }

    public void leave(UUID uuid){
        hunters.getPlayers().remove(uuid);
        runners.getPlayers().remove(uuid);
    }

    public void leave(Player player){
        leave(player.getUniqueId());
    }

    public Team getTeam(TeamUnit teamUnit){
        if(teamUnit == TeamUnit.HUNTERS)
            return hunters;
        if(teamUnit == TeamUnit.RUNNERS)
            return runners;

        return null;
    }

    public TeamUnit getPlayerTeam(UUID uuid){
        if(hunters.getPlayers().contains(uuid))
            return TeamUnit.HUNTERS;
        if(runners.getPlayers().contains(uuid))
            return TeamUnit.RUNNERS;

        return TeamUnit.NONE;
    }

    public TeamUnit getPlayerTeam(Player player){
        return getPlayerTeam(player.getUniqueId());
    }

    public boolean areAllInTeam(){
        return hunters.getSize() + runners.getSize() == Bukkit.getOnlinePlayers().size();
    }

    public Team getHunters() {
        return hunters;
    }

    public Team getRunners() {
        return runners;
    }

    //TODO implementer les joueurs non connecter avec connected 
    public boolean isTeamAlive(TeamUnit teamUnit){
        int nbteamalive = 0;

        for(Player player : Bukkit.getOnlinePlayers()){
            if(getPlayerTeam(player) == teamUnit){
                User user = instance.getUserManager().getUser(player).get();
                if(user.isAlive()){
                    nbteamalive++;
                }
            } else {
                continue;
            }
        }

        if(nbteamalive <= 0){
            return false;
        }
        return true;
    }
}

