package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fireman extends Kit{

    private Kit_PvP_Minecraft main;

    public void Fireman(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.onCommand(sender, command, label, args);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Kit_PvP_Minecraft.canChangeKit) {
                Inventory inv = player.getInventory();

                //Fireman Item Stuff
                ItemStack firemanWater = new ItemStack(Material.WATER_BUCKET);
                ItemMeta firemanWater_Data = firemanWater.getItemMeta();
                firemanWater.setItemMeta(firemanWater_Data);

                // Potion Effects
                int lengthOfResistance = 1000000;
                int strengthOfResistance = 0;
                PotionEffect firemanResistance = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, lengthOfResistance, strengthOfResistance);

                player.getInventory().addItem(firemanWater);
                firemanResistance.apply(player);

                player.sendMessage("You Have Chosen: " + ChatColor.DARK_RED+ " FIREMAN! ");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.fireman_Description);
            }
        }
        return false;
    }
}
