package com.sgpvp.GameData;

import com.sgpvp.GameLogic.KillLeaderboard;
import com.sgpvp.Kits.Kit;
import com.sgpvp.Tasks.GameEvents;
import org.bukkit.*;

import java.util.HashMap;

public class GameVariables {

    // BORDER SIZE - PLAY AREA
    public static final int WORLD_SIZE = 1000;
    public static Location WorldSpawn;
    public static boolean DEBUG = false;

    public static HashMap<String, Object> GameItems = new HashMap<>();

    // GAME STATE
    public static GameEvents gameEvents;
    public static boolean feastPlatformSpawned = false;
    public static Location feastLocation;
    public static final int pregameTime = 5 * 60;       // Time between minimum amount of players reached and game start (Normal Game: 5 minutes)
    public static final int gracePeriodTime = 1 * 60;   // Time between game start and pvp start (Normal Game: 1 minute)
    public static final int preFeastTime = 13 * 60;      // Time between pvp start and feast spawn (Normal Game: 13 minutes)
    public static final int postFeastTime = 20 * 60;     // Time between feast spawn and deathmatch (Normal Game: 20 minutes)

    //KIT SETTINGS
    public static ChatColor kitDescriptionColor = ChatColor.GRAY; //All Of The Kit Description Chat Colors

    // World Reference and Keeps Track Of If Events Have Started Or Not
    public static World world;
    public static boolean EventsFired = true;

    // Kill leaderboard
    public static KillLeaderboard killLeaderboard;

    // This is in all .kits classes and checks if kits can be changed (turns false when game starts)
    public static boolean canChangeKit = true;

    // World Time When Game Starts
    public static long WorldTimeWhenGameStarts = 11000;

    // Keeps Track of current players and min players to start game
    public static int currentAmountOfPlayers;
    public static int minimumPlayersToStart = 1;
    public static HashMap<String, Kit> kits;

    public static class WorldBounds {
        public static int MINX = 0;
        public static int MAXX = 0;
        public static int MINY = 0;
        public static int MAXY = 256;
        public static int MINZ = 0;
        public static int MAXZ = 0;
    }
}
