package com.sgpvp.GameLogic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;

public class KillLeaderboard implements Listener {
    HashMap<String, Integer> leaderboard = new HashMap<>();
    int topKillNums[] = {-1, -1, -1};
    String topKillNames[] = {"", "", ""};
    @EventHandler
    public void onKill(final EntityDeathEvent e){
        if (e.getEntity().getKiller() == null) return;
        Player killer = e.getEntity().getKiller();
        String name = killer.getName();
        if (leaderboard.containsKey(name))
            leaderboard.put(name, leaderboard.get(name));
        else
            leaderboard.put(name, 1);
        if (leaderboard.get(name) > topKillNums[0]) {
            topKillNums[2] = topKillNums[1];
            topKillNames[2] = topKillNames[1];
            topKillNums[1] = topKillNums[0];
            topKillNames[1] = topKillNames[0];
            topKillNums[0] = leaderboard.get(name);
            topKillNames[0] = name;
        } else if (leaderboard.get(name) > topKillNums[1]) {
            topKillNums[2] = topKillNums[1];
            topKillNames[2] = topKillNames[1];
            topKillNums[1] = leaderboard.get(name);
            topKillNames[1] = name;
        } else if (leaderboard.get(name) > topKillNums[1]) {
            topKillNums[2] = leaderboard.get(name);
            topKillNames[2] = name;
        }
    }
    public String getTopKillName(int i) {
        return topKillNames[i];
    }
    public int getTopKillNum(int i) {
        return topKillNums[i];
    }

    public int getKills(Player player) {
        return getKills(player.getName());
    }
    public int getKills(String name) {
        return leaderboard.containsKey(name) ? leaderboard.get(name) : 0;
    }

}
