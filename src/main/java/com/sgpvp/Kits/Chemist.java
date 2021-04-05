package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Chemist extends Kit{
    public String kitName = "Chemist";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (!GameVariables.canChangeKit) return false;
        //int extraHealthBoost = 0;

        if(sender instanceof Player){
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {
                Inventory inv = player.getInventory();
                for (ItemStack item : GameItems.getChemistPotions())
                    inv.addItem(item);
            }

        }
        return false;
    }
}
