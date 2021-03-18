package me.finneganmcguire.kit_pvp_minecraft.Player_Data;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlayerStorage {
    public static ArrayList<HashMap<String, String>> playerHashData = new ArrayList<HashMap<String, String>>();

    public static boolean playerHasKitActive(Player player, String kitname){
        for (int i = 0; i < playerHashData.size(); i++) {
            if(playerHashData.get(i).containsKey(player.getName()) && playerHashData.get(i).get(player.getName()).equals(kitname)){
                return true;
            }
        }

        return false;
    }

    public static void setPlayerNewKit(Player player, String kitname){
        for (int i = 0; i < playerHashData.size(); i++) {
            if(playerHashData.get(i).containsKey(player.getName())){
                playerHashData.get(i).put(player.getName(), kitname);
                System.out.println("Player Is Now: " + playerHashData.get(i).get(player.getName()) + " Kit");
            }
        }
    }
}
