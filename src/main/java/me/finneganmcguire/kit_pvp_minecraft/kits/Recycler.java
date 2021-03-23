package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
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
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;

public class Recycler implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Recycler(Kit_PvP_Minecraft main) {
        this.main = main;
    }

    public static HashMap<String, Integer> bowls = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (Kit_PvP_Minecraft.canChangeKit) {
                // REMOVE POTION EFFECTS
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
                PlayerStorage.setPlayerNewKit(player.getPlayer(), "recycler");
                Inventory inv = player.getInventory();
                inv.clear();

                bowls.put(player.getName(), 0);

                player.sendMessage("You Have Chosen: " + ChatColor.GREEN + ChatColor.BOLD + " Recycler! ");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor + KitDescriptions.recycler_Description);
            } else {
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }
        }
        return false;
    }
}
