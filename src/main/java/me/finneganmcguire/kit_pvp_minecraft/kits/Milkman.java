package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.GameData.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Milkman extends Kit{

    private Kit_PvP_Minecraft main;

    public void Milkman(Kit_PvP_Minecraft main) {
        this.main = main;
    }
    public String kitName = "Milkman";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        ItemStack milk_bucket = new ItemStack(Material.MILK_BUCKET, 1);
        ItemMeta milk_bucket_data = milk_bucket.getItemMeta();
        milk_bucket_data.setDisplayName(ChatColor.BOLD + "The Milkman's Bucket Of Milk");
        milk_bucket_data.setLore(Arrays.asList("Gives Speed 1", "Gives Regeneration 1", "Gives Fire Resistants 1"));
        milk_bucket.setItemMeta(milk_bucket_data);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {

                Inventory inv = player.getInventory();
                inv.addItem(milk_bucket);
            }

        }
        return false;
    }

    @EventHandler
    public void MilkBucketDrink(PlayerItemConsumeEvent p) {
        Player player = p.getPlayer();

        int timer_effects = 1000;

        ItemStack bucket_normal = new ItemStack(Material.BUCKET, 1);

        // Milkman Effects
        PotionEffect speedBuff = new PotionEffect(PotionEffectType.SPEED, timer_effects, 0);
        PotionEffect regenBuff = new PotionEffect(PotionEffectType.REGENERATION, timer_effects, 0);
        PotionEffect fireResistBuff = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 400, 0);

        // Currently Effects All Milk Buckets
        try{
            if (p.getItem().getType().equals(Material.MILK_BUCKET) && PlayerData.playerHasKitActive(p.getPlayer(), "milkman")) {

                p.setItem(bucket_normal);
                GameVariables.SGPvPMessage(player.getName() + ":" + ChatColor.BOLD + " I am the Milkman and my milk is delicious!");
                speedBuff.apply(player);
                regenBuff.apply(player);
                fireResistBuff.apply(player);
            }
        } catch (Exception e){
            System.out.println("Didnt Work");
        }

    }
}
