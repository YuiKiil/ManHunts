package fr.girlstream.manhunts.managers;

import java.util.Arrays;

public enum GameState {
    LOBBY(false),
    IN_GAME_NO_PVP(false),
    IN_GAME_PVP(false),
    END(false);

    private boolean state;
    private static GameState currentState;

    GameState(boolean state) {
        this.state = state;
    }

    public static void setState(GameState state) {
        currentState = state;
    }

    public static boolean isState(GameState ... state) {
        return Arrays.asList(state).contains((currentState));
    }

    public static GameState getCurrentState() {
        return currentState;
    }
}
