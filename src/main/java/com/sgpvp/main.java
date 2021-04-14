package com.sgpvp;

import com.sgpvp.Commands.AdminCommands;
import com.sgpvp.Commands.Kills;
import com.sgpvp.Commands.Spectate;
import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.*;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import com.sgpvp.Kits.*;
import com.sgpvp.Spectator.TeleportToPlayer;
import com.sgpvp.Tasks.*;
import com.sgpvp.GlobalEvents.SpawnMushrooms;
import org.bukkit.*;
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

    // ON PLUGIN ENABLED
    @Override
    public void onEnable() {
        // Creates New World When Game Is Complete For Next Game
        CreateNewWorld();


        // CACTI SOUP Crafting Addition
        ItemStack soup_item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(this, "Cacti_Stew");
        ShapelessRecipe recipe = new ShapelessRecipe(key, soup_item);
        recipe.addIngredient(2, Material.CACTUS);
        recipe.addIngredient(1, Material.BOWL);
        Bukkit.addRecipe(recipe);

        // coco SOUP Crafting Addition
        NamespacedKey key2 = new NamespacedKey(this, "cocoa_stew");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, soup_item);
        recipe2.addIngredient(2, Material.COCOA_BEANS);
        recipe2.addIngredient(1, Material.BOWL);
        Bukkit.addRecipe(recipe2);

        GameVariables.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);

        PluginManager pluginManager = getServer().getPluginManager();

        // Setting static game variables
        GameVariables.WorldSpawn = GameVariables.world.getSpawnLocation();
        GameVariables.WorldBounds.MINX = GameVariables.WorldSpawn.getBlockX() - GameVariables.WORLD_SIZE /2;
        GameVariables.WorldBounds.MAXX = GameVariables.WorldSpawn.getBlockX() + GameVariables.WORLD_SIZE /2;
        GameVariables.WorldBounds.MINZ = GameVariables.WorldSpawn.getBlockZ() - GameVariables.WORLD_SIZE /2;
        GameVariables.WorldBounds.MAXZ = GameVariables.WorldSpawn.getBlockZ() + GameVariables.WORLD_SIZE /2;
        GameVariables.gameEvents = new GameEvents(this);

        // Scoreboard
        BukkitTask gameEvents = (new GameScoreboard()).runTaskTimer(this, 0, 20);

        // Spawn Mushrooms In World
        SpawnMushrooms.spawnInitialMushrooms();

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
            put("Groundhog", new Groundhog());
            put("Cultivator", new Cultivator());
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
            put("Explorer", new Explorer());
            put("Spirit", new Spirit());
            put("Adventurer", new Adventurer());
            put("Endermage", new Endermage());
            //put("Adventurer", new Adventurer());
        }};
        for (String kit : GameVariables.kits.keySet()) {
            try {
                getServer().getPluginCommand(kit).setExecutor(GameVariables.kits.get(kit));
            } catch (NullPointerException nullp) {
                System.out.println(nullp.getMessage() + "@ kit: " + kit);
            }
        }

        getServer().getPluginCommand("game").setExecutor(new AdminCommands());
        getCommand("game").setTabCompleter(new AdminCommands());

        getServer().getPluginCommand("spectate").setExecutor(new Spectate());

        getServer().getPluginCommand("kit").setExecutor(new GUI());

        getServer().getPluginCommand("stp").setExecutor(new TeleportToPlayer());

        getServer().getPluginCommand("kills").setExecutor(new Kills());

        //CUSTOM EVENTS
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(new SoupEvent(), this);
        pluginManager.registerEvents(new CompassTracker(), this);
        pluginManager.registerEvents(new KeepPlayersInsideBorder(), this);
        pluginManager.registerEvents(new SpawnMushrooms(), this);
        pluginManager.registerEvents(new Chat(), this);
        pluginManager.registerEvents(this, this);

    }

    public void enableKitEvents() {
        for (Kit kit : GameVariables.kits.values())
            getServer().getPluginManager().registerEvents(kit, this);
    }

    // WHEN PLUGIN IS TURNED OFF (SERVER SHUTDOWN)
    @Override
    public void onDisable() { }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        // IF GAME IN PROGRESS - TURN PLAYER JOINED INTO SPECTATOR
        e.getPlayer().setScoreboard(GameScoreboard.board);
        if (GameVariables.gameEvents.getGameStateID() > 0) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            Chat.SGPvPMessage(e.getPlayer(), "GAME ALREADY IN PROGRESS...");
        } else {

            GameVariables.currentAmountOfPlayers++;
            PlayerData.setPlayerNewKit(e.getPlayer(), null);

            e.getPlayer().setGameMode(GameMode.ADVENTURE);
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().addItem(new ItemStack(Material.SLIME_BALL, 1));

            for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
                e.getPlayer().removePotionEffect(effect.getType());
            }

            e.getPlayer().teleport(GameVariables.world.getSpawnLocation());
            Chat.SGPvPMessage(e.getPlayer(), ChatColor.RED + "Welcome " + e.getPlayer().getName() + " to SGPvP!");
        }

        // START EVENT TIMERS (MINIMUM AMOUNT OF PLAYERS FOUND)
        if(GameVariables.currentAmountOfPlayers >= GameVariables.minimumPlayersToStart){
            if(GameVariables.EventsFired){
                BukkitTask nighttimechecker = new NightTimeChecker(this).runTaskTimer(this, 0, 20);
                BukkitTask gameEvents = GameVariables.gameEvents.runTaskTimer(this, 0, 20);

                GameVariables.EventsFired = false;
            }
        }
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
        /*
        if(GameVariables.currentAmountOfPlayers <= 1){

            try{
                Chat.SGPvPMessage(ChatColor.GOLD + "CONGRATS " + e.getEntity().getKiller().getName() + " YOU WON!");
            } catch (Exception exception){
                Chat.SGPvPMessage(ChatColor.GOLD + "CONGRATS YOU WON!");
            }

            Chat.SGPvPMessage(ChatColor.DARK_RED + "Server Restarting In 15 Seconds, Thanks For Playing :)");
            BukkitTask countDownToGameStartTask = new EndGameKickPlayer(this).runTaskLater(this, 500); // Kick Player In 30 Sec
        }*/
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
        GameVariables.world = Bukkit.getWorld("KIT_PVP_WORLD2"); //wc.createWorld();
    }

    //Deletes Old World then creates new world
    public void CreateNewWorld(){
        WORLD_DATA();

    }
}
