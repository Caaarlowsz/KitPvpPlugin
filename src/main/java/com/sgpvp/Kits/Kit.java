package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.Kits.KitConfig.KitDescriptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;

public abstract class Kit implements CommandExecutor, Listener {
    public String kitName = "Kit";
    private com.sgpvp.main main;
    public static Material kitItem = Material.GRASS_BLOCK;

    public void Kit(com.sgpvp.main main){
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

            if (GameVariables.canChangeKit) {
                Inventory inv = player.getInventory();
                inv.clear();
                PlayerData.setPlayerNewKit(player.getPlayer(), kitName.toLowerCase());
                player.setDisplayName(GameVariables.getPrefix(player) + player.getName());
                GameVariables.SGPvPMessage(player, "You Have Chosen: " + KitDescriptions.color(kitName) + kitName);
                GameVariables.SGPvPMessage(player, GameVariables.kitDescriptionColor + KitDescriptions.description(kitName));
                initializeKit(player);
            } else
                GameVariables.SGPvPMessage(player, ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }
        return false;
    }
    public void passName(String name) { this.kitName = name; }
    abstract void initializeKit(Player player);
}
