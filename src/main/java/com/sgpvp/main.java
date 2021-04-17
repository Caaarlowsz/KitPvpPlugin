package com.sgpvp;

import com.sgpvp.Commands.AdminCommands;
import com.sgpvp.Commands.Kills;
import com.sgpvp.Commands.Spectate;
import com.sgpvp.GameData.GameLog;
import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.*;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import com.sgpvp.Kits.*;
import com.sgpvp.Spectator.TeleportToPlayer;
import com.sgpvp.Tasks.*;
import com.sgpvp.GlobalEvents.SpawnMushrooms;
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

import java.io.File;
import java.util.HashMap;

public final class main extends JavaPlugin implements Listener {

    // ON PLUGIN ENABLED
    @Override
    public void onEnable() {
        // Setup world
        createNewWorld();

        GameVariables.plugin = this;
        GameLog.setupLogFolder();

        GameVariables.gameEvents = new GameEvents();
        GameVariables.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        GameVariables.world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 0);
        GameVariables.setupWorldSpawn();
        GameScoreboard scoreboard = new GameScoreboard();
        scoreboard.runTaskTimer(this, 0, 20);
        SpawnMushrooms.spawnInitialMushrooms();
        addRecipes();

        // Kits, commands, and events
        initializeKits();
        registerCommands();
        registerEvents();

    }

    // WHEN PLUGIN IS TURNED OFF (SERVER SHUTDOWN)
    @Override
    public void onDisable() {
        GameLog.saveEvent("\n --- Game Ended --- \n");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        // IF GAME IN PROGRESS - TURN PLAYER JOINED INTO SPECTATOR
        Player player = e.getPlayer();
        player.setScoreboard(GameScoreboard.board);
        if (GameVariables.gameEvents.getGameStateID() > 0) {
            playerJoinDuringGame(player);
        } else {
            playerJoinBeforeGame(player);
        }

        // Start the game when the minimum number of players have joined
        attemptGameStart();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            GameVariables.currentAmountOfPlayers--;
        }
        PlayerData.remove(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        GameVariables.currentAmountOfPlayers--;
        System.out.println(GameVariables.currentAmountOfPlayers);
        e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);
        GameLog.saveEvent("Death: " + e.getDeathMessage());
        if(PlayerData.playerHasKitActive(e.getEntity().getPlayer(), "Adventurer")){
            Adventurer.playerQuests.get(e.getEntity().getPlayer()).questVisible(false);
        }
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
        try {
            GameVariables.world = Bukkit.getWorlds().get(0); //wc.createWorld();
        } catch (Exception exception) {
            Chat.DebugMessage("World not found.");
        }
    }

    //Deletes Old World then creates new world
    public void createNewWorld(){
        WORLD_DATA();

    }
    // Initializes and stores all kits in GameVariables.kits hashmap
    private void initializeKits() {
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
        }};
    }
    // Enables all kit events (called on game start)
    public void enableKitEvents() {
        for (Kit kit : GameVariables.kits.values())
            getServer().getPluginManager().registerEvents(kit, this);
    }
    // Register all commands
    private void registerCommands() {
        for (String kit : GameVariables.kits.keySet()) {
            try {
                getServer().getPluginCommand(kit).setExecutor(GameVariables.kits.get(kit));
            } catch (NullPointerException nullPointerException) {
                System.out.println(nullPointerException.getMessage() + "@ kit: " + kit);
            }
        }

        getServer().getPluginCommand("game").setExecutor(new AdminCommands());
        getCommand("game").setTabCompleter(new AdminCommands());
        getServer().getPluginCommand("spectate").setExecutor(new Spectate());
        getServer().getPluginCommand("kit").setExecutor(new GUI());
        getServer().getPluginCommand("stp").setExecutor(new TeleportToPlayer());
        getServer().getPluginCommand("kills").setExecutor(new Kills());
    }
    // Register all event listeners
    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerInteractions(), this);
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(new SoupEvent(), this);
        pluginManager.registerEvents(new CompassTracker(), this);
        pluginManager.registerEvents(new KeepPlayersInsideBorder(), this);
        pluginManager.registerEvents(new SpawnMushrooms(), this);
        pluginManager.registerEvents(new Chat(), this);
        pluginManager.registerEvents(new FreezePlayers(), this);
        pluginManager.registerEvents(this, this);
    }
    // Add recipes
    private void addRecipes() {
        // Cacti stew
        ItemStack soup_item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(this, "cacti_Stew");
        ShapelessRecipe recipe = new ShapelessRecipe(key, soup_item);
        recipe.addIngredient(2, Material.CACTUS);
        recipe.addIngredient(1, Material.BOWL);
        Bukkit.addRecipe(recipe);

        // Cocoa stew
        NamespacedKey key2 = new NamespacedKey(this, "cocoa_stew");
        ShapelessRecipe recipe2 = new ShapelessRecipe(key2, soup_item);
        recipe2.addIngredient(2, Material.COCOA_BEANS);
        recipe2.addIngredient(1, Material.BOWL);
        Bukkit.addRecipe(recipe2);
    }
    // Called when a player joins after the game has started
    private void playerJoinDuringGame(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        Chat.SGPvPMessage(player, "GAME ALREADY IN PROGRESS...");
    }
    // Called when a player joins before the game has started
    private void playerJoinBeforeGame(Player player) {
        GameVariables.currentAmountOfPlayers++;
        PlayerData.setPlayerNewKit(player, null);

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.SLIME_BALL, 1));

        for(PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());

        player.teleport(GameVariables.world.getSpawnLocation());
        Chat.SGPvPMessage(player, ChatColor.RED + "Welcome " + player.getName() + " to SGPvP!");
    }
    // Attempts to start the game when a player joins
    private void attemptGameStart() {
        if(GameVariables.currentAmountOfPlayers >= GameVariables.minimumPlayersToStart){
            if(GameVariables.EventsFired){
                (new NightTimeChecker(this)).runTaskTimer(this, 0, 20);
                GameVariables.gameEvents.runTaskTimer(this, 0, 20);

                GameVariables.EventsFired = false;
            }
        }
    }
}
