package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class GameScoreboard extends BukkitRunnable {
    public static Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective obj = board.registerNewObjective("SGPvP", "dummy", " --- SGPvP --- ");

    public GameScoreboard() {
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score onlineName = obj.getScore(ChatColor.GRAY + "» Online");
        onlineName.setScore(15);

        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);

        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);

        Score strGameState = obj.getScore(ChatColor.GRAY + "» Game State");
        strGameState.setScore(13);
        Team gameStateString = board.registerNewTeam("gameStateString");
        gameStateString.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(12);

        Score strTimeRemaining = obj.getScore(ChatColor.GRAY + "» Time Remaining");
        strTimeRemaining.setScore(11);
        Team timeRemaining = board.registerNewTeam("timeRemaining");
        timeRemaining.addEntry(ChatColor.RED + "" + ChatColor.GREEN);
        obj.getScore(ChatColor.RED + "" + ChatColor.GREEN).setScore(10);

        Score strTopKills = obj.getScore(ChatColor.GRAY + "» Top Kills");
        strTopKills.setScore(9);
        Team topKills1 = board.registerNewTeam("topKills1");
        topKills1.addEntry(ChatColor.GREEN + "" + ChatColor.GREEN);
        obj.getScore(ChatColor.GREEN + "" + ChatColor.GREEN).setScore(8);
        Team topKills2 = board.registerNewTeam("topKills2");
        topKills2.addEntry(ChatColor.GREEN + "" + ChatColor.BLUE);
        obj.getScore(ChatColor.GREEN + "" + ChatColor.BLUE).setScore(7);
        Team topKills3 = board.registerNewTeam("topKills3");
        topKills3.addEntry(ChatColor.GREEN + "" + ChatColor.LIGHT_PURPLE);
        obj.getScore(ChatColor.GREEN + "" + ChatColor.LIGHT_PURPLE).setScore(6);
    }

    @Override
    public void run() { updateScoreboard(); }

    public void updateScoreboard() {

        if (Bukkit.getOnlinePlayers().size() == 0)
            board.getTeam("onlineCounter").setPrefix(ChatColor.DARK_RED + "0" + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        else board.getTeam("onlineCounter").setPrefix(ChatColor.DARK_RED + "" + Bukkit.getOnlinePlayers().size() + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());

        board.getTeam("gameStateString").setPrefix(ChatColor.GREEN + GameVariables.gameEvents.getGameStateString());
        board.getTeam("timeRemaining").setPrefix(ChatColor.AQUA + "" + GameVariables.gameEvents.getTimeRemaining());

        //int topKills = GameVariables.killLeaderboard.getTopKillNum(0);
        //String strTopKills = GameVariables.killLeaderboard.getTopKillName(0);

        //board.getTeam("topKills1").setPrefix(ChatColor.GOLD + "1. " + GameVariables.killLeaderboard.getTopKillName(0) + ": " + GameVariables.killLeaderboard.getTopKillNum(0));
        //board.getTeam("topKills2").setPrefix(ChatColor.GRAY + "2. " + GameVariables.killLeaderboard.getTopKillName(1) + ": " + GameVariables.killLeaderboard.getTopKillNum(1));
        //board.getTeam("topKills3").setPrefix(ChatColor.GRAY + "3. " + GameVariables.killLeaderboard.getTopKillName(2) + ": " + GameVariables.killLeaderboard.getTopKillNum(2));

    }
}
