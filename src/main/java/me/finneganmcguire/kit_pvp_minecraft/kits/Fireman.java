package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
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

public class Fireman implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Brawler(Kit_PvP_Minecraft main){
        this.main = main;
    }

    // Local Fireman
    public boolean kit_FiremanActive;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            if(player.isOp()){
                Inventory inv = player.getInventory();
                inv.clear();

                //Fireman Item Stuff
                ItemStack firemanWater = new ItemStack(Material.WATER_BUCKET);
                ItemMeta firemanWater_Data = firemanWater.getItemMeta();
                firemanWater.setItemMeta(firemanWater_Data);

                // Potion Effects
                int lengthOfResistance = 1000000;
                int strengthOfResistance = 1;
                PotionEffect firemanResistance = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, lengthOfResistance, strengthOfResistance);

                player.getInventory().addItem(firemanWater);
                firemanResistance.apply(player);


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
