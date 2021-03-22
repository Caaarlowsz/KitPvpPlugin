package me.finneganmcguire.kit_pvp_minecraft.GlobalEvents;

public class GameState {

    public static String gameState = null;
    public static String gamestate_lobby = "lobby";
    public static String gamestate_graceperiod = "graceperiod";
    public static String gamestate_main = "main";
    public static String gamestate_deathmatch = "deathmatch";

    public static String getCurrentGameState(){
        return gameState;
    }

}
