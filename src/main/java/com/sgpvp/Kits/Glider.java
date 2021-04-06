package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Glider extends Kit{
    public String kitName = "Glider";
    private com.sgpvp.main main;

    public void Glider(com.sgpvp.main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        ItemStack glider = new ItemStack(Material.ELYTRA);
        ItemMeta glider_Data = glider.getItemMeta();
        glider_Data.setDisplayName(ChatColor.BLACK + "Glider");
        glider.setItemMeta(glider_Data);
        player.getInventory().addItem(glider);
        /* Kit functionality ends here */
    }
}
