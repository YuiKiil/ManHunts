package fr.girlstream.manhunts.managers.lang;

public enum LangValue {
    PLAYER("player"),
    TIME("time"),
    TEAM("team"),
    ONLINE_PLAYER("online_player"),
    MAX_PLAYER("max_player"),
    PLAYER_KILL("player_kill"),
    TIMER("timer"),
    PVP("pvp_state"),
    NB_RUNNER_ALIVE("nb_runner_alive"),
    NB_HUNTER_ALIVE("nb_hunter_alive");

    private String name;

    LangValue(String name) {
        this.name = name;
    }

    public CharSequence toName(){
        return "{" + name + "}";
    }
}
