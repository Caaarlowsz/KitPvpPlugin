package com.sgpvp.GameData;

import com.sgpvp.GameLogic.Chat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PlayerData {
    public static HashMap<String, String> playerData = new HashMap<String, String>();
    public static HashMap<String, Integer> bowls = new HashMap<>();

    public static boolean playerHasKitActive(Player player, String kitname){
        if (playerData.containsKey(player.getName()))
            return kitname.toLowerCase().equals(playerData.get(player.getName()));
        playerData.put(player.getName(), "None");
        return false;
    }

    public static void setPlayerNewKit(Player player, String kitname){
        playerData.put(player.getName(), kitname);
    }
    public static String getPlayerKit(Player player){
        return playerData.get(player.getName());
    }
    public static boolean playerHasKit(Player player){
        if (playerData.containsKey(player.getName())) return !playerHasKitActive(player,"None");
        playerData.put(player.getName(), "None");
        return false;
    }

    public static void remove(Player player) {
        playerData.remove(player.getName());
    }

    public static boolean recycleBowl(Player player) {
        if (!bowls.containsKey(player.getName()))
            bowls.put(player.getName(), 0);
        bowls.put(player.getName(), bowls.get(player.getName()) + 1);
        Chat.SGPvPMessage(player, "You have recycled " + bowls.get(player.getName()) + " bowls");
        return bowls.get(player.getName()) % 3 == 0;

    }

    public static List<Player> getAlive() {
        List<Player> alive = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isDead()) continue;
            if (player.getGameMode().equals(GameMode.SURVIVAL) ||
                player.getGameMode().equals(GameMode.ADVENTURE))
                alive.add(player);
        }
        return alive;
    }
}
