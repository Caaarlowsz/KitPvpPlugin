package com.sgpvp.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class ProgressBar {
    private BossBar progress;
    private double max;
    private double cur;
    public ProgressBar(Player player, String title, BarColor color, BarStyle style, int max) {
        this.progress = Bukkit.createBossBar(title, color, style);
        this.progress.addPlayer(player);
        this.progress.setProgress(0d);
        this.cur = 0;
        this.max = max;

    }
    public void increment() {
        cur += cur < max ? 1 : 0;
        this.progress.setProgress(cur/max);
        if (cur == max) removeAll();
    }
    public void removePlayer(Player player) {
        this.progress.removePlayer(player);
    }
    public void removeAll() {
        this.progress.removeAll();
    }
}
