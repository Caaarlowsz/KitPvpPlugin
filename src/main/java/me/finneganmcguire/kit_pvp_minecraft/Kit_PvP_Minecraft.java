package me.finneganmcguire.kit_pvp_minecraft;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.*;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameCommands;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameEndsLogic;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class Kit_PvP_Minecraft extends JavaPlugin implements Listener {

    //KIT SETTINGS
    public static ChatColor kitDescriptionColor = ChatColor.GRAY; //All Of The Kit Description Chat Colors

    // World Reference and Keeps Track Of If Events Have Started Or Not
    public static World world;
    public static boolean EventsFired = true;
    public static final int WORLDSIZE = 1500;

    // This is in all .kits classes and checks if kits can be executed (turns false when game starts)
    public static boolean canChangeKit = true;

    // World Time When Game Starts
    public long TimeWhenGameStarts = 11000;

    // Keeps Track of current players and min players to start game
    public static int currentAmountOfPlayers;
    public static int minimumPlayersToStart = 1;

    // Timers For Events
    public long GracePeriodDelayTimer = 2 * 1000; // Time Before Grace Period Ends (2min)
    public long GameStartDelayTimer = 1000; // Time Before Game Starts (1 min)
    public long ChestCircleDelayTimer = 3 * 1000; // Time Before Chest Circle Spawns (13 min)
    public long DeathmatchDelayTimer = 4 * 1000; // Time Before Deathmatch Starts

    // ON PLUGIN ENABLED
    @Override
    public void onEnable() {

        GameState.gameState = GameState.gamestate_lobby;

        // Current problem:
            // Variables that are static, effect all players. FIX IT!
        PluginManager pluginManager = getServer().getPluginManager();

        // Creates New World When Game Is Complete For Next Game
        //CreateNewWorld();

        //SpawnMushrooms.spawnMushrooms(world);

        // CUSTOM RECIPES
        //Bukkit.addRecipe(Soups.cactiSoup());

        //CUSTOM EVENTS
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(new SoupEvent(), this);
        pluginManager.registerEvents(new CompassTracker(), this);
        pluginManager.registerEvents(this, this);

        // Global Events
        pluginManager.registerEvents(new PlayerInteractions(), this);

        //COMMANDS & KITS
        getServer().getPluginCommand("TimeWizard").setExecutor(new TimeWizard()); // Needs Work

        getServer().getPluginCommand("Werewolf").setExecutor(new Werewolf()); // Needs Work

        getServer().getPluginCommand("Brawler").setExecutor(new Brawler()); // Stabel and Working
        pluginManager.registerEvents(new Brawler(), this); // Registers Events Like Free Hand

        getServer().getPluginCommand("Chameleon").setExecutor(new Chameleon()); // Stabel and Working
        pluginManager.registerEvents(new Chameleon(), this); // Registers Events Like Free Hand

        getServer().getPluginCommand("Lumberjack").setExecutor(new Lumberjack()); // Stable and Working

        getServer().getPluginCommand("Grandpa").setExecutor(new Grandpa()); //Stable and Working

        getServer().getPluginCommand("Turtle").setExecutor(new Turtle()); // Stable and Working
        pluginManager.registerEvents(new Turtle(), this); // Registers Events Like Shifting

        getServer().getPluginCommand("Milkman").setExecutor(new Milkman()); //Needs Work
        pluginManager.registerEvents(new Milkman(), this); // Registers Events

        getServer().getPluginCommand("Fireman").setExecutor(new Fireman()); //Stable and Working

        getServer().getPluginCommand("Pyromancer").setExecutor(new Pyromancer());

        getServer().getPluginCommand("Beastmaster").setExecutor(new Beastmaster());
        pluginManager.registerEvents(new Beastmaster(), this); // Registers Events

        //getServer().getPluginCommand("Chemist").setExecutor(new Chemist());

        getServer().getPluginCommand("game").setExecutor(new GameCommands());
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        currentAmountOfPlayers++;

        if(!GameState.gameState.equals(GameState.gamestate_lobby)){
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().sendMessage("GAME ALREADY IN PROGRESS...");
        } else {
            PlayerStorage.setPlayerNewKit(e.getPlayer(), null);

            e.getPlayer().setGameMode(GameMode.ADVENTURE);
            e.getPlayer().getInventory().clear();

            for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
                e.getPlayer().removePotionEffect(effect.getType());
            }

            e.getPlayer().teleport(world.getSpawnLocation());
            Bukkit.broadcastMessage(ChatColor.RED + "Welcome " + e.getPlayer().getName() + " To KIT PVP!");
        }

        if(currentAmountOfPlayers >= minimumPlayersToStart){
            if(EventsFired){
                BukkitTask chestsTask = new ChestCircleSpawnTask(this).runTaskTimer(this, ChestCircleDelayTimer, 20);
                BukkitTask deathmatchTask = new DeathmatchTask(this).runTaskTimer(this, DeathmatchDelayTimer, 20);
                BukkitTask gamestartTask = new GameStartTask(this).runTaskLater(this, GameStartDelayTimer);
                BukkitTask countDownToGameStartTask = new CountDownToStartTask(this).runTaskTimer(this, GameStartDelayTimer - 300, 20);
                BukkitTask countDownGracePeriodTask = new CountDownGracePeriodTask(this).runTaskTimer(this, GracePeriodDelayTimer - 999, 20);
                BukkitTask graceperiodTask = new GracePeriodEndTask(this).runTaskLater(this, GracePeriodDelayTimer + 200);

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
        currentAmountOfPlayers--;
        PlayerStorage.remove(e.getPlayer());
    }

    @EventHandler
    public void OnPlayerDie(PlayerDeathEvent e){
            currentAmountOfPlayers--;
            System.out.println(currentAmountOfPlayers);
            e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);

            if(currentAmountOfPlayers <= 1){
                    List<LivingEntity> livingEntities = world.getLivingEntities();
                for (int i = 0; i < livingEntities.size(); i++) {
                    if(livingEntities.get(i).getType().equals(EntityType.PLAYER)){
                        String playerLeftName = livingEntities.get(i).getName();
                        Bukkit.broadcastMessage("CONGRATS " + playerLeftName + " YOU WON!");
                    }
                }
                Bukkit.broadcastMessage("Server Restarting In 15 Seconds, Thanks For Playing :)");
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
        WorldCreator wc = new WorldCreator("KIT_PVP_WORLD2");
        wc.type(WorldType.NORMAL);
        wc.generateStructures(false);
        world = wc.createWorld();
        world.setTime(TimeWhenGameStarts);
        world.setGameRule(GameRule.DO_ENTITY_DROPS, true);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
        world.setGameRule(GameRule.DO_MOB_LOOT, true);
        world.setDifficulty(Difficulty.NORMAL);
    }

    //Deletes Old World then creates new world
    public void CreateNewWorld(){
        // DELETE PREVIOUS WORLD.
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
    }
}
