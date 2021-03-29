package com.sgpvp.kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Chemist extends Kit{
    public String kitName = "Chemist";
    private com.sgpvp.main main;

    public void Brawler(com.sgpvp.main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        int extraHealthBoost = 0;

        if(sender instanceof Player){
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {
                Inventory inv = player.getInventory();
                inv.clear();

            }

        }
        return false;
    }
}
