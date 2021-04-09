package com.sgpvp.Kits;

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
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        Inventory inv = player.getInventory();
        for (ItemStack item : GameItems.getChemistPotions())
            inv.addItem(item);


        /* Kit functionality ends here */
    }
}
