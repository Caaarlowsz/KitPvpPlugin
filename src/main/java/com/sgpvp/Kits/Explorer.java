package com.sgpvp.Kits;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Explorer extends Kit {
    public String kitName = "Explorer";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        player.getInventory().addItem(new ItemStack(Material.SADDLE, 1));
        player.getInventory().addItem(new ItemStack(Material.STRIDER_SPAWN_EGG, 1));
        player.getInventory().addItem(new ItemStack(Material.PIG_SPAWN_EGG, 1));
        player.getInventory().addItem(new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK, 1));
        player.getInventory().addItem(new ItemStack(Material.CARROT_ON_A_STICK, 1));
        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */

    /* Kit event handlers end here */
}
