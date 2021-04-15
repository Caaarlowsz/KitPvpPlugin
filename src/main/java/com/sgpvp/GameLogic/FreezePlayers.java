package com.sgpvp.GameLogic;

import com.sgpvp.Kits.TimeWizard;
import org.bukkit.GameMode;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class FreezePlayers implements Listener {
    public static boolean allFrozen = false;
    public static void toggleGlobalFreeze() {
        FreezePlayers.allFrozen = !FreezePlayers.allFrozen;
    }
    public static void toggleGlobalFreeze(int duration) {
        Thread freeze = new Thread(new Freeze(duration));
        freeze.start();
    }
    public void playerMove(PlayerMoveEvent event) {
        if (allFrozen) event.setCancelled(true);
    }
    private static class Freeze extends Thread {
        int duration;
        public Freeze(int duration) { this.duration = duration; }
        public void run(){
            allFrozen = true;
            try { Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace(); }
            allFrozen = false;
        }
    }
}
