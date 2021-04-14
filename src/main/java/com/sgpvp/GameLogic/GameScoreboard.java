package com.sgpvp.GameLogic;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class GameScoreboard extends BukkitRunnable {
    public static Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective obj = board.registerNewObjective("SGPvP", "dummy", " --- SGPvP --- ");
    Score[] lines = {
            obj.getScore(""),
            obj.getScore("Game starts in: "),
            obj.getScore(""),
            obj.getScore("Top Kills:"),
            obj.getScore("1."),
            obj.getScore("2."),
            obj.getScore("3.")};

    public GameScoreboard() {
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score onlineName = obj.getScore(ChatColor.GRAY + "» Online");
        onlineName.setScore(15);

        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);

        if (Bukkit.getOnlinePlayers().size() == 0) {
            onlineCounter.setPrefix(ChatColor.DARK_RED + "0" + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        } else {
            onlineCounter.setPrefix("" + ChatColor.DARK_RED + Bukkit.getOnlinePlayers().size() + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        }

        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);

        Score gameState = obj.getScore(ChatColor.GRAY + "» Game State");
        gameState.setScore(13);

        Team gameStateString = board.registerNewTeam("gameStateString");
        gameStateString.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        gameStateString.setPrefix(ChatColor.GREEN + GameVariables.gameEvents.getGameStateString());
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(12);
    }

    @Override
    public void run() { updateScoreboard(); }

    public void updateScoreboard() {

        lines[2].setScore(GameVariables.gameEvents.getGameStateID());

        if (Bukkit.getOnlinePlayers().size() == 0) {
            board.getTeam("onlineCounter").setPrefix(ChatColor.DARK_RED + "0" + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        } else {
            board.getTeam("onlineCounter").setPrefix(ChatColor.DARK_RED + "" + Bukkit.getOnlinePlayers().size() + ChatColor.RED + "/" + ChatColor.DARK_RED + Bukkit.getMaxPlayers());
        }
        board.getTeam("gameStateString").setPrefix(ChatColor.GREEN + GameVariables.gameEvents.getGameStateString());

    }
}
