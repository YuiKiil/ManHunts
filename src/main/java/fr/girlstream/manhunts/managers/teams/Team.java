package fr.girlstream.manhunts.managers.teams;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team {

    private TeamUnit teamUnit;
    private final Set<UUID> players;

    public Team(TeamUnit teamUnit) {
        this.teamUnit = teamUnit;
        this.players = new HashSet<>();
    }

    public String getName(){
        return teamUnit.getName();
    }

    public String getPrefix(){
        return teamUnit.getPrefix();
    }

    public String getColor(){
        return teamUnit.getColor();
    }

    public String getColoredName(){
        return teamUnit.getColoredName();
    }

    public Set<UUID> getPlayers() {
        return players;
    }

    public int getSize(){
        return players.size();
    }

    public boolean isEmpty(){
        return players.isEmpty();
    }

}
