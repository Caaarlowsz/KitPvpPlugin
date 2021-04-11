package com.sgpvp.Kits;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Pyromancer extends Kit{
    public String kitName = "Pyromancer";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET);
        ItemStack flintAndSteel = new ItemStack(Material.FLINT_AND_STEEL);

        Inventory inv = player.getInventory();
        inv.addItem(lavaBucket, flintAndSteel);

        /* Kit functionality ends here */
    }
}

