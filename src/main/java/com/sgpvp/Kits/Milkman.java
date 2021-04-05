package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.GameItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Milkman extends Kit{
    public String kitName = "Milkman";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (!GameVariables.canChangeKit) return false;

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (GameVariables.canChangeKit) {
                Inventory inv = player.getInventory();
                inv.addItem(GameItems.getMilkmanBucket());
            }
        }
        return false;
    }

    @EventHandler
    public void MilkBucketDrink(PlayerItemConsumeEvent p) {
        Player player = p.getPlayer();

        int timer_effects = 250;

        ItemStack bucket_normal = new ItemStack(Material.BUCKET, 1);

        // Milkman Effects
        PotionEffect speedBuff = new PotionEffect(PotionEffectType.SPEED, timer_effects, 0);
        PotionEffect regenBuff = new PotionEffect(PotionEffectType.REGENERATION, timer_effects, 0);
        PotionEffect fireResistBuff = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 400, 0);

        // Currently Effects All Milk Buckets
        if (p.getItem().getType().equals(Material.MILK_BUCKET) && PlayerData.playerHasKitActive(p.getPlayer(), "milkman")) {
            p.setItem(bucket_normal);
            GameVariables.SGPvPMessage(player.getName() + ":" + ChatColor.BOLD + " I am the Milkman and my milk is delicious!");
            speedBuff.apply(player);
            regenBuff.apply(player);
            fireResistBuff.apply(player);
        }

    }
}
