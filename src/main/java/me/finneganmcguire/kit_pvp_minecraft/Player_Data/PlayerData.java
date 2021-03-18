package me.finneganmcguire.kit_pvp_minecraft.Player_Data;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    public Player player;
    public String currentActiveKit;

    public PlayerData(Player player, String currentActiveKit){
        this.player = player;
        this.currentActiveKit = currentActiveKit;
    }
}
