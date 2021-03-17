package me.finneganmcguire.kit_pvp_minecraft;

import me.finneganmcguire.kit_pvp_minecraft.kits.*;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.graalvm.compiler.word.Word;

import java.io.File;

public final class Kit_PvP_Minecraft extends JavaPlugin implements Listener {

    public static int worldIterations = 1;
    public static World world;

    @Override
    public void onEnable() {

        PluginManager pluginManager = getServer().getPluginManager();

        // BACKGROUND WORLD EVENTS
        CreateNewWorld();

        //CUSTOM EVENTS
        pluginManager.registerEvents(new GUI(), this);
        pluginManager.registerEvents(this, this);

        //COMMANDS & KITS
        getServer().getPluginCommand("TimeWizard").setExecutor(new TimeWizard()); // Needs Work
        getServer().getPluginCommand("Wolverine").setExecutor(new Wolverine()); // Needs Work

        getServer().getPluginCommand("Brawler").setExecutor(new Brawler()); // Stabel and Working
        pluginManager.registerEvents(new Brawler(), this); // Registers Events Like Free Hand

        getServer().getPluginCommand("Lumberjack").setExecutor(new Lumberjack()); // Stable and Working
        getServer().getPluginCommand("Grandpa").setExecutor(new Grandpa()); //Stable and Working

        getServer().getPluginCommand("Turtle").setExecutor(new Turtle()); // Stable and Working

        pluginManager.registerEvents(new Turtle(), this); // Registers Events Like Shifting
        getServer().getPluginCommand("Milkman").setExecutor(new Milkman()); //Stable and Working
        pluginManager.registerEvents(new Milkman(), this); // Registers Events

        getServer().getPluginCommand("Fireman").setExecutor(new Fireman()); //Stable and Working
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        e.getPlayer().getInventory().clear();

        for(PotionEffect effect : e.getPlayer().getActivePotionEffects()){
            e.getPlayer().removePotionEffect(effect.getType());
        }

        e.getPlayer().teleport(world.getSpawnLocation());
        Bukkit.broadcastMessage(ChatColor.RED + "Welcome " + e.getPlayer().getName() + " To KIT PVP!");
    }

    @EventHandler
    public void OnPlayerDie(PlayerDeathEvent e){
        e.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);
    }

    public void CreateNewWorld(){
            WorldCreator wc = new WorldCreator("KIT_PVP_WORLD_2");
            wc.type(WorldType.NORMAL);
            wc.generateStructures(false);
            world = wc.createWorld();
    }



}
