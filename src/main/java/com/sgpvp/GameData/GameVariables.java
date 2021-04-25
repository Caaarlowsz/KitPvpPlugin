package com.sgpvp.GameData;

import com.sgpvp.GameLogic.GameScoreboard;
import com.sgpvp.GameLogic.KillLeaderboard;
import com.sgpvp.Kits.Kit;
import com.sgpvp.Tasks.GameEvents;
import com.sgpvp.main;
import org.bukkit.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class GameVariables {
    // main class (plugin)
    public static main plugin;

    // Game identification
    public static final long gameID = generateID();

    // BORDER SIZE - PLAY AREA
    public static final int WORLD_SIZE = 1000;
    public static Location WorldSpawn;

    public static boolean DEBUG = false;

    public static HashMap<String, Object> GameItems = new HashMap<>();

    // GAME STATE
    public static GameEvents gameEvents;
    public static boolean feastPlatformSpawned = false;
    public static Location feastLocation;
    public static final int pregameTime = 6 * 60;      // Time between minimum amount of players reached and game start (Normal Game: 5 minutes)
    public static final int gracePeriodTime = 60;  // Time between game start and pvp start (Normal Game: 1 minute)
    public static final int preFeastTime = 12 * 60;     // Time between pvp start and feast spawn (Normal Game: 13 minutes)
    public static final int postFeastTime = 20 * 60;    // Time between feast spawn and deathmatch (Normal Game: 20 minutes)

    //KIT SETTINGS
    public static ChatColor kitDescriptionColor = ChatColor.GRAY; //All Of The Kit Description Chat Colors

    // World Reference and Keeps Track Of If Events Have Started Or Not
    public static World world;
    public static boolean EventsFired = true;

    // Scoreboard and leaderboards
    public static GameScoreboard gameScoreboard;
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

    public static void setupWorldSpawn() {
        WorldSpawn = world.getSpawnLocation();
        WorldBounds.MINX = WorldSpawn.getBlockX() - WORLD_SIZE /2;
        WorldBounds.MAXX = WorldSpawn.getBlockX() + WORLD_SIZE /2;
        WorldBounds.MINZ = WorldSpawn.getBlockZ() - WORLD_SIZE /2;
        WorldBounds.MAXZ = WorldSpawn.getBlockZ() + WORLD_SIZE /2;
    }
    private static long generateID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return Long.parseLong(dtf.format(now));
    }
}
