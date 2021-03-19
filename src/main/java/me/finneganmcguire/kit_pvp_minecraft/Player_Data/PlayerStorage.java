package me.finneganmcguire.kit_pvp_minecraft.Player_Data;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlayerStorage {
    public static HashMap<String, String> playerData = new HashMap<String, String>();

    public static boolean playerHasKitActive(Player player, String kitname){
        if (playerData.containsKey(player.getName())) return playerData.get(player.getName()) == kitname;
        System.out.println("Error: Player has not been registered!!!");
        playerData.put(player.getName(), "None");
        return false;
    }

    public static void setPlayerNewKit(Player player, String kitname){
        playerData.put(player.getName(), kitname);
    }

    public static void remove(Player player) {
        playerData.remove(player.getName());
    }
}
