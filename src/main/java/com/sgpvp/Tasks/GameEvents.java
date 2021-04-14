package com.sgpvp.Tasks;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.*;
import com.sgpvp.GlobalEvents.PlayerInteractions;
import com.sgpvp.main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class GameEvents extends BukkitRunnable {
    private int timer;
    private int gameStateID;
    private final main plugin;
    private final int startGameTime;
    private final int endGracePeriodTime;
    private final int spawnFeastTime;
    private final int startDeathmatchTime;

    // Intervals for each countdown
    private final Set<Integer> countdownIntervals = new HashSet<Integer>(){{
        add(600);
        add(300);
        add(120);
        add(60);
        add(15);
        add(10);
        add(5);
        add(4);
        add(3);
        add(2);
        add(1);
    }};

    public GameEvents(main plugin) {
        this.plugin = plugin;
        this.timer = 0;
        this.gameStateID = 0;
        this.startGameTime = GameVariables.pregameTime;
        this.endGracePeriodTime = startGameTime + GameVariables.gracePeriodTime;
        this.spawnFeastTime = endGracePeriodTime + GameVariables.preFeastTime;
        this.startDeathmatchTime = spawnFeastTime + GameVariables.postFeastTime;
    }

    @Override
    public void run() {
        switch (gameStateID) {
            case 0:
                gameStartCountdown();
                break;
            case 1:
                gracePeriodCountdown();
                break;
            case 2:
                feastSpawnCountdown();
                break;
            case 3:
                deathmatchCountdown();
                break;
        }
        timer++;
    }

    public void startGame() { gameStateID = 0; timer = startGameTime - 5; }
    public void endGracePeriod() { gameStateID = 1; timer = endGracePeriodTime - 5; }
    public void spawnFeast() { gameStateID = 2; timer = spawnFeastTime - 5; }
    public void startDeathmatch() { gameStateID = 3; timer = startDeathmatchTime - 5; }

    public int getGameStateID() { return gameStateID; }
    public String getGameStateString() {
        switch (gameStateID) {
            case 0: return "Pre-game";
            case 1: return "Grace period";
            case 2: return "Pre-feast";
            case 3: return "Pre-deathmatch";
            case 4: return "Deathmatch";
        }
        return "Unknown";
    }

    private void gameStartCountdown() {
        int sectionTime = startGameTime - timer;
        if (countdownIntervals.contains(sectionTime)) {
            if (sectionTime < 60)
                Chat.SGPVPGlobalTitle("Game starting", String.format("in %d seconds", sectionTime), "#FF3933", "#B52C28");
            else
                Chat.SGPVPGlobalTitle("Game starting", String.format("in %d minutes", sectionTime/60), "#FF3933", "#B52C28");
        }
        if (sectionTime == 0) gameStart();
    }

    private void gameStart() {
        Chat.SGPVPGlobalTitle("Game Has Started!", " ", "#FF3933", "");
        GameStartLogic.GameStart(GameVariables.world, plugin);

        PlayerInteractions.playerCanDropLava = false;
        gameStateID++;
    }
    private void gracePeriodCountdown() {
        int sectionTime = endGracePeriodTime - timer;
        if (countdownIntervals.contains(sectionTime)) {
            if (sectionTime < 60)
                Chat.SGPVPGlobalTitle("Grace period ends", String.format("in %d seconds", sectionTime), "", "");
            else
                Chat.SGPVPGlobalTitle("Grace period ends", String.format("in %d minutes", sectionTime/60), "", "");
        }
        if (sectionTime == 0) gracePeriodEnded();

    }
    private void gracePeriodEnded() {
        Chat.SGPVPGlobalTitle("Grace period has ended", " ", "", "");
        GameVariables.world.setPVP(true);
        PlayerInteractions.playerCanDropLava = true;

        gameStateID++;
    }
    private void feastSpawnCountdown() {
        int sectionTime = spawnFeastTime - timer;
        if (countdownIntervals.contains(sectionTime)) {
            if (sectionTime < 60)
                Chat.SGPvPMessage(ChatColor.LIGHT_PURPLE + String.format("Feast will spawn in %d seconds at (%d, %d)!", sectionTime, FeastLogic.feast_center_x, FeastLogic.feast_center_z));
            else
                Chat.SGPvPMessage(ChatColor.LIGHT_PURPLE + String.format("Feast will spawn in %d minutes at (%d, %d)!", sectionTime/60, FeastLogic.feast_center_x, FeastLogic.feast_center_z));
        }

        if (sectionTime == 120) FeastLogic.SpawnFeastPlatform();

        else if (sectionTime == 0) {
            FeastLogic.SpawnFeast();
            gameStateID++;
        }
    }
    private void deathmatchCountdown() {
        int sectionTime = startDeathmatchTime - timer;
        String deathmatchTitle_Color = "#A0322E";
        String deathmatchSubTitle_Color = "#901612";

        if (countdownIntervals.contains(sectionTime)) {
            if (sectionTime < 60)
                Chat.SGPVPGlobalTitle("Deathmatch", String.format("in %d seconds", sectionTime), deathmatchTitle_Color, deathmatchSubTitle_Color);
            else
                Chat.SGPVPGlobalTitle("Deathmatch", String.format("in %d minutes", sectionTime/60), deathmatchTitle_Color, deathmatchSubTitle_Color);
        }

        else if (sectionTime == 0){
            DeathmatchLogic.DeathmatchBegin();
            gameStateID++;
        }
    }
}
