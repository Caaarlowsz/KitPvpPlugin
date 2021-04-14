package com.sgpvp.GameData;

import com.sgpvp.Kits.Kit;
import com.sgpvp.Kits.KitConfig.KitDescriptions;
import com.sgpvp.Tasks.GameEvents;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class GameVariables {

    // BORDER SIZE - PLAY AREA
    public static final int WORLDSIZE = 1000;
    public static Location WorldSpawn;
    public static boolean DEBUG = false;

    public static HashMap<String, Object> GameItems = new HashMap<>();

    // GAME STATE
    public static GameEvents gameEvents;
    public static String gameState = null;
    public static String gamestate_lobby = "lobby";
    public static String gamestate_graceperiod = "graceperiod";
    public static String gamestate_main = "main";
    public static String gamestate_deathmatch = "deathmatch";
    public static boolean feastPlatformSpawned = false;
    public static Location feastLocation;
    public static final int pregameTime = 3 * 60;       // Time between minimum amount of players reached and game start
    public static final int gracePeriodTime = 2 * 60;   // Time between game start and pvp start
    public static final int preFeastTime = 5 * 60;      // Time between pvp start and feast spawn
    public static final int postFeastTime = 5 * 60;     // Time between feast spawn and deathmatch

    //KIT SETTINGS
    public static ChatColor kitDescriptionColor = ChatColor.GRAY; //All Of The Kit Description Chat Colors

    // World Reference and Keeps Track Of If Events Have Started Or Not
    public static World world;
    public static boolean EventsFired = true;

    // This is in all .kits classes and checks if kits can be changed (turns false when game starts)
    public static boolean canChangeKit = true;

    // World Time When Game Starts
    public static long WorldTimeWhenGameStarts = 11000;

    // Keeps Track of current players and min players to start game
    public static int currentAmountOfPlayers;
    public static int minimumPlayersToStart = 1;
    public static HashMap<String, Kit> kits;

    // Returns The Current Game State
    public static String getCurrentGameState(){ return gameState; }

    public static class WorldBounds {
        public static int MINX = 0;
        public static int MAXX = 0;
        public static int MINY = 0;
        public static int MAXY = 256;
        public static int MINZ = 0;
        public static int MAXZ = 0;
    }
}
