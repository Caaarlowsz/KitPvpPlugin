package com.sgpvp.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Adventurer extends Kit{
    public String kitName = "Adventurer";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack map = new ItemStack(Material.MAP);
        ItemMeta map_meta = map.getItemMeta();
        map_meta.setDisplayName(ChatColor.YELLOW + "The Adventurers Map");

        map.setItemMeta(map_meta);

        player.getInventory().addItem(new ItemStack(map));

        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */

    /* Kit event handlers end here */
}
