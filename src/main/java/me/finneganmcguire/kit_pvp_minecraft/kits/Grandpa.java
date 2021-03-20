package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class Grandpa implements CommandExecutor {

    private Kit_PvP_Minecraft main;

    public void Grandpa(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // REMOVE ACCESS TO OTHER KITS

        ItemStack grandpaStick = new ItemStack(Material.STICK);
        ItemMeta stickData = grandpaStick.getItemMeta();

        stickData.setDisplayName(ChatColor.BOLD + "Uncle Joe's Stick");
        stickData.setLore(Arrays.asList("This stick belongs to uncle joe"));
        stickData.addEnchant(Enchantment.KNOCKBACK, 2, true);

        grandpaStick.setItemMeta(stickData);

        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            if(Kit_PvP_Minecraft.canChangeKit){
                Inventory inv = player.getInventory();
                inv.clear();
                inv.addItem(grandpaStick);

                player.sendMessage("You Have Chosen: " + ChatColor.BOLD + " GRANDPA! ");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.grandpa_Description);
            } else{
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }

        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }
}
