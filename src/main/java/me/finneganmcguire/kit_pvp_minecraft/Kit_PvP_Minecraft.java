package me.finneganmcguire.kit_pvp_minecraft;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.DeathmatchLogic;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameCommands;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.SoupEvent;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.finneganmcguire.kit_pvp_minecraft.kits.*;
import me.finneganmcguire.kit_pvp_minecraft.tasks.*;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
//import org.graalvm.compiler.word.Word;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public final class Kit_PvP_Minecraft extends JavaPlugin implements Listener {

    public static World world;
    public static boolean EventsFired = true;

    public static int currentAmountOfPlayers;
    public static int totalAmountOfPlayersOnServer = 1;

    public long GracePeriodDelayTimer = 4000;
    public long GameStartDelayTimer = 500;
    public long ChestCircleDelayTimer = 8000;
    public long DeathmatchDelayTimer = 100000;

    // ON PLUGIN ENABLED
    @Override
    public void onEnable() {

        // Current problem:
            // Variables that are static, effect all players. FIX IT!
        PluginManager pluginManager = getServer().getPluginManager();

        // BACKGROUND WORLD EVENTS
        CreateNewWorld();

        //CUSTOM EVENTS
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(new SoupEvent(), this);
        pluginManager.registerEvents(this, this);

        GameCommands.e = world;

        //COMMANDS & KITS
        getServer().getPluginCommand("TimeWizard").setExecutor(new TimeWizard()); // Needs Work

        getServer().getPluginCommand("Wolverine").setExecutor(new Wolverine()); // Needs Work

        getServer().getPluginCommand("Brawler").setExecutor(new Brawler()); // Stabel and Working
        pluginManager.registerEvents(new Brawler(), this); // Registers Events Like Free Hand

        getServer().getPluginCommand("Lumberjack").setExecutor(new Lumberjack()); // Stable and Working

        getServer().getPluginCommand("Grandpa").setExecutor(new Grandpa()); //Stable and Working

        getServer().getPluginCommand("Turtle").setExecutor(new Turtle()); // Stable and Working
        pluginManager.registerEvents(new Turtle(), this); // Registers Events Like Shifting

        getServer().getPluginCommand("Milkman").setExecutor(new Milkman()); //Needs Work
        pluginManager.registerEvents(new Milkman(), this); // Registers Events

        getServer().getPluginCommand("Fireman").setExecutor(new Fireman()); //Stable and Working

        getServer().getPluginCommand("Pyromancer").setExecutor(new Pyromancer());

        getServer().getPluginCommand("game").setExecutor(new GameCommands());

    }



    @Override
    public void onDisable() {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        currentAmountOfPlayers++;

        if(currentAmountOfPlayers >= totalAmountOfPlayersOnServer){
            if(EventsFired){
                BukkitTask chestsTask = new ChestCircleSpawnTask(this).runTaskLater(this, ChestCircleDelayTimer);
                BukkitTask deathmatchTask = new DeathmatchTask(this).runTaskLater(this, DeathmatchDelayTimer);
                BukkitTask gamestartTask = new GameStartTask(this).runTaskLater(this, GameStartDelayTimer);
                BukkitTask countdownTask = new CountDownTask(this).runTaskTimer(this, GameStartDelayTimer - 300, 20);
                BukkitTask graceperiodTask = new GracePeriodEndTask(this).runTaskLater(this, GracePeriodDelayTimer);
                EventsFired = false;
            }
        }

        // Create Player Hash Data
        //HashMap<String, String> player_data = new HashMap<String, String>();
        //player_data.put(e.getPlayer().getName(), null);

        // Add Player To Global Hash Database
        PlayerStorage.setPlayerNewKit(e.getPlayer(), null);

        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        e.getPlayer().getInventory().clear();

        for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
            e.getPlayer().removePotionEffect(effect.getType());
        }

        e.getPlayer().teleport(world.getSpawnLocation());
        Bukkit.broadcastMessage(ChatColor.RED + "Welcome " + e.getPlayer().getName() + " To KIT PVP!");
    }

    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent e){
        currentAmountOfPlayers--;
        PlayerStorage.remove(e.getPlayer());
    }

    @EventHandler
    public void OnPlayerDie(PlayerDeathEvent e){
        e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);
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
        WorldCreator wc = new WorldCreator("KIT_PVP_WORLD");
        wc.type(WorldType.NORMAL);
        wc.generateStructures(false);
        world = wc.createWorld();
    }

    //Deletes Old World then creates new world
    public void CreateNewWorld(){
        // DELETE PREVIOUS WORLD.
        String filepath = "KIT_PVP_WORLD";
        File worldDir = new File(filepath);
        boolean result = deleteDirectory(worldDir);

        // AFTER DELETE - CREATE NEW WORLD.
        if (result){
            WORLD_DATA();
            System.out.println(ChatColor.BLUE + "New World Generated");
        }

        // IF WORLD GETS SOMEHOW DELETED BEFORE HAND, THIS WILL CREATE A NEW WORLD.
        else {
            WORLD_DATA();
            System.out.println(ChatColor.RED + "Didnt Find Previous World To Delete, Making New World...");
        }
    }





}
