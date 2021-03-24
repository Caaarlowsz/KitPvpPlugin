package me.finneganmcguire.kit_pvp_minecraft.Player_Data;
import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GameVariables;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlayerStorage {
    public static HashMap<String, String> playerData = new HashMap<String, String>();
    public static HashMap<String, Integer> bowls = new HashMap<>();

    public static boolean playerHasKitActive(Player player, String kitname){
        if (playerData.containsKey(player.getName())) return playerData.get(player.getName()) == kitname;
        playerData.put(player.getName(), "None");
        return false;
    }

    public static void setPlayerNewKit(Player player, String kitname){
        playerData.put(player.getName(), kitname);
    }

    public static void remove(Player player) {
        playerData.remove(player.getName());
    }

    public static boolean recycleBowl(Player player) {
        if (!bowls.containsKey(player.getName()))
            bowls.put(player.getName(), 0);
        bowls.put(player.getName(), bowls.get(player.getName()) + 1);
        player.sendMessage("You have recycled " + bowls.get(player.getName()) + " bowls");
        return bowls.get(player.getName()) % 3 == 0;

    }
}
