package com.sgpvp;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.*;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import com.sgpvp.Kits.*;
import com.sgpvp.Tasks.*;
import org.bukkit.*;
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

public final class main extends JavaPlugin implements Listener {

    // Timers For Events - [TIMERS START WHEN MINIMUM AMOUNT OF PLAYERS HAVE BEEN FOUND]
    public long GracePeriodDelayTimer = 2 * 1000; // Time Before Grace Period Ends (2min)
    public long GameStartDelayTimer = 1000; // Time Before Game Starts (1 min)
    public long ChestCircleDelayTimer = 13 * 1000; // Time Before Chest Circle Spawns (13 min)
    public long DeathmatchDelayTimer = 18 * 1000; // Time Before Deathmatch Starts (18 min)

    // ON PLUGIN ENABLED
    @Override
    public void onEnable() {
        // Creates New World When Game Is Complete For Next Game
        CreateNewWorld();

        GameVariables.gameState = GameVariables.gamestate_lobby;
        System.out.println("GAME STATE IS NOW: " + GameVariables.gameState);

        // CACTI SOUP Crafting Addition
        ItemStack soup_item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(this, "Cacti_Stew");
        ShapelessRecipe recipe = new ShapelessRecipe(key, soup_item);
        recipe.addIngredient(2, Material.CACTUS);
        recipe.addIngredient(1, Material.BOWL);
        Bukkit.addRecipe(recipe);

        GameVariables.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        // Current problem:
        // Variables that are static, effect all players. FIX IT!
        PluginManager pluginManager = getServer().getPluginManager();

        // Setting static game variables
        GameVariables.WorldSpawn = GameVariables.world.getSpawnLocation();
        GameVariables.WorldBounds.MINX = GameVariables.WorldSpawn.getBlockX() - GameVariables.WORLDSIZE/2;
        GameVariables.WorldBounds.MAXX = GameVariables.WorldSpawn.getBlockX() + GameVariables.WORLDSIZE/2;
        GameVariables.WorldBounds.MINZ = GameVariables.WorldSpawn.getBlockZ() - GameVariables.WORLDSIZE/2;
        GameVariables.WorldBounds.MAXZ = GameVariables.WorldSpawn.getBlockZ() + GameVariables.WORLDSIZE/2;

        // Spawn Mushrooms In World
        SpawnMushrooms.spawnMushrooms(GameVariables.world);

        //CUSTOM EVENTS
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(new SoupEvent(), this);
        pluginManager.registerEvents(new CompassTracker(), this);
        pluginManager.registerEvents(new KeepPlayersInsideBorder(), this);
        pluginManager.registerEvents(this, this);

        // Global Events
        pluginManager.registerEvents(new PlayerInteractions(), this);

        //COMMANDS & KITS
        GameVariables.kits = new HashMap<String,Kit>(){{
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
            //put("Groundhog", new Groundhog()); not working
            //put("Cultivator", new Cultivator()); not working
            put("Chemist", new Chemist());
            put("Sunwalker", new Sunwalker());
            put("Blacksmith", new Blacksmith());
            put("Viper", new Viper());
            put("Snail", new Snail());
            put("Stomper", new Stomper());
            put("Switcher", new Switcher());
            put("Phantom", new Phantom());
            put("Kangaroo", new Kangaroo());
            put("Bomber", new Bomber());
            put("Thor", new Thor());
            put("Vampire", new Vampire());
            put("Assassin", new Assassin());
            put("Monk", new Monk());

        }};
        for (String kit : GameVariables.kits.keySet())
            getServer().getPluginCommand(kit).setExecutor(GameVariables.kits.get(kit));
        for (Kit kit : GameVariables.kits.values())
            pluginManager.registerEvents(kit, this);

        getServer().getPluginCommand("game").setExecutor(new AdminCommands());
        getCommand("game").setTabCompleter(new AdminCommands());

        for(Player p : this.getServer().getOnlinePlayers()) {
            p.setDisplayName(GameVariables.getPrefix(p) + p.getName());
        }
    }

    // WHEN PLUGIN IS TURNED OFF (SERVER SHUTDOWN)
    @Override
    public void onDisable() {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().setDisplayName(GameVariables.getPrefix(e.getPlayer()) + e.getPlayer().getName());

        // IF GAME IN PROGRESS - TURN PLAYER JOINED INTO SPECTATOR
        if(!GameVariables.gameState.equals(GameVariables.gamestate_lobby)){
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            GameVariables.SGPvPMessage(e.getPlayer(), "GAME ALREADY IN PROGRESS...");
        } else {

            GameVariables.currentAmountOfPlayers++;
            PlayerData.setPlayerNewKit(e.getPlayer(), null);

            e.getPlayer().setGameMode(GameMode.ADVENTURE);
            e.getPlayer().getInventory().clear();

            for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
                e.getPlayer().removePotionEffect(effect.getType());
            }

            e.getPlayer().teleport(GameVariables.world.getSpawnLocation());
            GameVariables.SGPvPMessage(ChatColor.RED + "Welcome " + e.getPlayer().getName() + " To KIT PVP!");
        }

        // START EVENT TIMERS (MINIMUM AMOUNT OF PLAYERS FOUND)
        if(GameVariables.currentAmountOfPlayers >= GameVariables.minimumPlayersToStart){
            if(GameVariables.EventsFired){
                BukkitTask chestsTask = new ChestCircleSpawnTask(this).runTaskTimer(this, ChestCircleDelayTimer, 20);
                BukkitTask deathmatchTask = new DeathmatchTask(this).runTaskTimer(this, DeathmatchDelayTimer, 20);
                BukkitTask gamestartTask = new GameStartTask(this).runTaskLater(this, GameStartDelayTimer);
                BukkitTask countDownToGameStartTask = new CountDownToStartTask(this).runTaskTimer(this, GameStartDelayTimer - 300, 20);
                BukkitTask countDownGracePeriodTask = new CountDownGracePeriodTask(this).runTaskTimer(this, GracePeriodDelayTimer - 999, 20);
                BukkitTask graceperiodTask = new GracePeriodEndTask(this).runTaskLater(this, GracePeriodDelayTimer + 200);
                BukkitTask nighttimechecker = new NightTimeChecker(this).runTaskTimer(this, GameStartDelayTimer, 20);

                GameVariables.EventsFired = false;
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
            GameVariables.currentAmountOfPlayers--;
        }
        PlayerData.remove(e.getPlayer());
    }

    @EventHandler
    public void OnPlayerDie(PlayerDeathEvent e){
        GameVariables.currentAmountOfPlayers--;
        System.out.println(GameVariables.currentAmountOfPlayers);
        e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);

        if(GameVariables.currentAmountOfPlayers <= 1){

            try{
                GameVariables.SGPvPMessage(ChatColor.GOLD + "CONGRATS " + e.getEntity().getKiller().getName() + " YOU WON!");
            } catch (Exception exception){
                GameVariables.SGPvPMessage(ChatColor.GOLD + "CONGRATS YOU WON!");
            }


            GameVariables.SGPvPMessage(ChatColor.DARK_RED + "Server Restarting In 15 Seconds, Thanks For Playing :)");
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
        GameVariables.world = Bukkit.getWorld("KIT_PVP_WORLD2"); //wc.createWorld();
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
