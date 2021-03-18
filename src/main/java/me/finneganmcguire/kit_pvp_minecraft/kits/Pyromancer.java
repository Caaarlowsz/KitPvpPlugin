package me.finneganmcguire.kit_pvp_minecraft.kits;
import com.google.gson.internal.$Gson$Preconditions;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Pyromancer implements CommandExecutor {

    private Kit_PvP_Minecraft main;

    public void Pyromancer(Kit_PvP_Minecraft main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            Inventory inv = player.getInventory();
            inv.clear();

            // Class Items
            ItemStack lavabucket = new ItemStack(Material.LAVA_BUCKET);
            ItemStack flintandsteel = new ItemStack(Material.FLINT_AND_STEEL);

            inv.addItem(lavabucket, flintandsteel);

            player.sendMessage("You Have Chosen: " + ChatColor.DARK_RED + ChatColor.BOLD + " PYROMANCER! ");

        } else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }
        return false;
    }
}

