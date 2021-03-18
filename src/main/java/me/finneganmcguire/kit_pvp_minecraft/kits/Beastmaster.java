package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Beastmaster implements CommandExecutor {

    private Kit_PvP_Minecraft main;

    public void Lumberjack(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            //Beastmaster Items
            ItemStack bones = new ItemStack(Material.BONE, 3);
            ItemStack wolfSpawnEggs = new ItemStack(Material.WOLF_SPAWN_EGG, 2);

            if(player.isOp()){
                Inventory inv = player.getInventory();
                inv.clear();

                inv.addItem(bones, wolfSpawnEggs);

                player.sendMessage("You Have Chosen: " + ChatColor.DARK_GREEN + " BEASTMASTER!");
            } else{
                player.sendMessage(ChatColor.RED + "YOU DO NOT HAVE ACCESS TO THIS KIT");
            }

        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }
}
