package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.ProgressBar;
import com.sgpvp.Kits.KitConfig.KitDescriptions;
import com.sgpvp.Kits.KitConfig.Quest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public abstract class Kit implements CommandExecutor, Listener {
    public String kitName = "Kit";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Chat.DebugMessage(player.getDisplayName() + " selected kit " + kitName);

            // REMOVE POTION EFFECTS
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }

            if (GameVariables.canChangeKit) {
                if(PlayerData.playerHasKitActive(player, "Adventurer")){
                    Adventurer.playerQuests.get(player).questVisible(false);
                }
                Inventory inv = player.getInventory();
                inv.clear();
                inv.addItem(new ItemStack(Material.SLIME_BALL, 1));
                if (player.getPlayer() == null) return false;
                PlayerData.setPlayerNewKit(player.getPlayer(), kitName.toLowerCase());

                Chat.SGPvPMessage(player, "You Have Chosen: " + KitDescriptions.color(kitName) + kitName);
                Chat.SGPvPMessage(player, GameVariables.kitDescriptionColor + KitDescriptions.description(kitName));
                initializeKit(player);
            } else
                Chat.SGPvPMessage(player, ChatColor.RED + "Sorry you can not change kits during the match");
        }
        return false;
    }
    public void passName(String name) { this.kitName = name; }
    abstract void initializeKit(Player player);
}
