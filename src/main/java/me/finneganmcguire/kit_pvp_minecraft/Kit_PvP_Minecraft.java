package me.finneganmcguire.kit_pvp_minecraft;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.*;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameCommands;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.SoupEvent;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.SpawnMushrooms;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.GameState;
import me.finneganmcguire.kit_pvp_minecraft.GlobalEvents.PlayerInteractions;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.*;
import me.finneganmcguire.kit_pvp_minecraft.kits.Chameleon;
import me.finneganmcguire.kit_pvp_minecraft.kits.TimeWizard;
import me.finneganmcguire.kit_pvp_minecraft.tasks.*;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class Kit_PvP_Minecraft extends JavaPlugin implements Listener {

    //KIT SETTINGS
    public static ChatColor kitDescriptionColor = ChatColor.GRAY; //All Of The Kit Description Chat Colors

    // World Reference and Keeps Track Of If Events Have Started Or Not
    public static World world;
    public static boolean EventsFired = true;

    // BORDER SIZE - PLAY AREA
    public static final int WORLDSIZE = 600;

    // This is in all .kits classes and checks if kits can be changed (turns false when game starts)
    public static boolean canChangeKit = true;

    // World Time When Game Starts
    public static long WorldTimeWhenGameStarts = 11000;

    // Keeps Track of current players and min players to start game
    public static int currentAmountOfPlayers;
    public static int minimumPlayersToStart = 1;

    // Timers For Events - [TIMERS START WHEN MINIMUM AMOUNT OF PLAYERS HAVE BEEN FOUND]
    public long GracePeriodDelayTimer = 2 * 1000; // Time Before Grace Period Ends (2min)
    public long GameStartDelayTimer = 1000; // Time Before Game Starts (1 min)
    public long ChestCircleDelayTimer = 13 * 1000; // Time Before Chest Circle Spawns (13 min)
    public long DeathmatchDelayTimer = 18 * 1000; // Time Before Deathmatch Starts (18 min)

    public static HashMap<String, Kit> kits;

    // ON PLUGIN ENABLED
    @Override
    public void onEnable() {
        // Creates New World When Game Is Complete For Next Game
        CreateNewWorld();

        GameState.gameState = GameState.gamestate_lobby;
        System.out.println("GAME STATE IS NOW: " + GameState.gameState);

        // CACTI SOUP Crafting Addition
        ItemStack soup_item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(this, "Cacti_Stew");
        ShapelessRecipe recipe = new ShapelessRecipe(key, soup_item);
        recipe.addIngredient(2, Material.CACTUS);
        recipe.addIngredient(1, Material.BOWL);
        Bukkit.addRecipe(recipe);

        Kit_PvP_Minecraft.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        // Current problem:
        // Variables that are static, effect all players. FIX IT!
        PluginManager pluginManager = getServer().getPluginManager();

        // Setting static game variables
        GameVariables.WorldSpawn = world.getSpawnLocation();
        GameVariables.WORLDSIZE = WORLDSIZE;
        GameVariables.WorldBounds.MINX = GameVariables.WorldSpawn.getBlockX() - WORLDSIZE/2;
        GameVariables.WorldBounds.MAXX = GameVariables.WorldSpawn.getBlockX() + WORLDSIZE/2;
        GameVariables.WorldBounds.MINZ = GameVariables.WorldSpawn.getBlockZ() - WORLDSIZE/2;
        GameVariables.WorldBounds.MAXZ = GameVariables.WorldSpawn.getBlockZ() + WORLDSIZE/2;

        // Spawn Mushrooms In World
        SpawnMushrooms.spawnMushrooms(world);

        //CUSTOM EVENTS
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(new SoupEvent(), this);
        pluginManager.registerEvents(new CompassTracker(), this);
        pluginManager.registerEvents(new KeepPlayersInsideBorder(), this);
        pluginManager.registerEvents(this, this);

        // Global Events
        pluginManager.registerEvents(new PlayerInteractions(), this);

        //COMMANDS & KITS
        kits = new HashMap<String,Kit>(){{
            put("TimeWizard", new TimeWizard());
            put("Werewolf", new Werewolf());
            put("Brawler", new Brawler());
            put("Chameleon", new Chameleon());
            put("Lumberjack", new Lumberjack());
            put("Grandpa", new Grandpa());
            put("Turtle", new Turtle());
            put("Milkman", new Milkman());
            put("Fireman", new Fireman());
            put("Pyromancer", new Pyromancer());
            put("Recycler", new Recycler());
            put("Beastmaster", new Beastmaster());
            put("Glider", new Glider());
            put("Groundhog", new Groundhog());
            put("Cultivator", new Cultivator());
        }};
        for (String kit : kits.keySet())
            getServer().getPluginCommand(kit).setExecutor(kits.get(kit));
        pluginManager.registerEvents(new Brawler(), this);
        pluginManager.registerEvents(new Chameleon(), this);
        pluginManager.registerEvents(new Turtle(), this);
        pluginManager.registerEvents(new Milkman(), this);
        pluginManager.registerEvents(new Beastmaster(), this);
        pluginManager.registerEvents(new Groundhog(), this);
        pluginManager.registerEvents(new Cultivator(), this);

        getServer().getPluginCommand("game").setExecutor(new GameCommands());
    }

    // WHEN PLUGIN IS TURNED OFF (SERVER SHUTDOWN)
    @Override
    public void onDisable() {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){

        // IF GAME IN PROGRESS - TURN PLAYER JOINED INTO SPECTATOR
        if(!GameState.gameState.equals(GameState.gamestate_lobby)){
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage("GAME ALREADY IN PROGRESS...");
        } else {

            currentAmountOfPlayers++;
            PlayerStorage.setPlayerNewKit(e.getPlayer(), null);

            e.getPlayer().setGameMode(GameMode.ADVENTURE);
            e.getPlayer().getInventory().clear();

            for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
                e.getPlayer().removePotionEffect(effect.getType());
            }

            e.getPlayer().teleport(world.getSpawnLocation());
            Bukkit.broadcastMessage(ChatColor.RED + "Welcome " + e.getPlayer().getName() + " To KIT PVP!");
        }

        // START EVENT TIMERS (MINIMUM AMOUNT OF PLAYERS FOUND)
        if(currentAmountOfPlayers >= minimumPlayersToStart){
            if(EventsFired){
                BukkitTask chestsTask = new ChestCircleSpawnTask(this).runTaskTimer(this, ChestCircleDelayTimer, 20);
                BukkitTask deathmatchTask = new DeathmatchTask(this).runTaskTimer(this, DeathmatchDelayTimer, 20);
                BukkitTask gamestartTask = new GameStartTask(this).runTaskLater(this, GameStartDelayTimer);
                BukkitTask countDownToGameStartTask = new CountDownToStartTask(this).runTaskTimer(this, GameStartDelayTimer - 300, 20);
                BukkitTask countDownGracePeriodTask = new CountDownGracePeriodTask(this).runTaskTimer(this, GracePeriodDelayTimer - 999, 20);
                BukkitTask graceperiodTask = new GracePeriodEndTask(this).runTaskLater(this, GracePeriodDelayTimer + 200);
                BukkitTask nighttimechecker = new NightTimeChecker(this).runTaskTimer(this, GameStartDelayTimer, 20);

                EventsFired = false;
            }
        }

        // Create Player Hash Data
        //HashMap<String, String> player_data = new HashMap<String, String>();
        //player_data.put(e.getPlayer().getName(), null);

        // Add Player To Global Hash Database

    }

    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent e){

        if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            currentAmountOfPlayers--;
        }
        PlayerStorage.remove(e.getPlayer());
    }

    @EventHandler
    public void OnPlayerDie(PlayerDeathEvent e){
        currentAmountOfPlayers--;
        System.out.println(currentAmountOfPlayers);
        e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);

        if(currentAmountOfPlayers <= 1){

            try{
                Bukkit.broadcastMessage(ChatColor.GOLD + "CONGRATS " + e.getEntity().getKiller().getName() + " YOU WON!");
            } catch (Exception exception){
                Bukkit.broadcastMessage(ChatColor.GOLD + "CONGRATS YOU WON!");
            }


            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Server Restarting In 15 Seconds, Thanks For Playing :)");
            BukkitTask countDownToGameStartTask = new EndGameKickPlayer(this).runTaskLater(this, 500); // Kick Player In 30 Sec
        }
    }

    // Delete Files Functionality
    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public void WORLD_DATA(){
        //WorldCreator wc = new WorldCreator("KIT_PVP_WORLD2");
        //wc.type(WorldType.NORMAL);
        //wc.generateStructures(false);
        world = Bukkit.getWorld("KIT_PVP_WORLD2"); //wc.createWorld();
    }

    //Deletes Old World then creates new world
    public void CreateNewWorld(){
        // DELETE PREVIOUS WORLD.
        WORLD_DATA();

        /*
        String filepath = "KIT_PVP_WORLD2";
        File worldDir = new File(filepath);
        boolean result = deleteDirectory(worldDir);

        // AFTER DELETE - CREATE NEW WORLD.
        if (result){
            WORLD_DATA();
            System.out.println(ChatColor.BLUE + "New World Generated");
        }

        // IF WORLD GETS SOME HOW DELETED BEFORE HAND, THIS WILL CREATE A NEW WORLD.
        else {
            WORLD_DATA();
            System.out.println(ChatColor.RED + "Didnt Find Previous World To Delete, Making New World...");
        }

         */
    }
}
