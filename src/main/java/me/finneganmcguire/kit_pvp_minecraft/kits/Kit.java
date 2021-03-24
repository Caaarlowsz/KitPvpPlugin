package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

public class Kit implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Kit(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }

            if (Kit_PvP_Minecraft.canChangeKit) {
                Inventory inv = player.getInventory();
                inv.clear();
            } else
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }
        return false;
    }
}
