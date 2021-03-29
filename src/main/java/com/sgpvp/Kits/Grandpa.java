package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Grandpa extends Kit{
    public String kitName = "Grandpa";
    private com.sgpvp.main main;

    public void Grandpa(com.sgpvp.main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        ItemStack grandpaStick = new ItemStack(Material.STICK);
        ItemMeta stickData = grandpaStick.getItemMeta();

        stickData.setDisplayName(ChatColor.BOLD + "Uncle Joe's Stick");
        stickData.setLore(Arrays.asList("This stick belongs to uncle joe"));
        stickData.addEnchant(Enchantment.KNOCKBACK, 2, true);

        grandpaStick.setItemMeta(stickData);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {
                Inventory inv = player.getInventory();
                inv.addItem(grandpaStick);

            }
        }
        return false;
    }
}
