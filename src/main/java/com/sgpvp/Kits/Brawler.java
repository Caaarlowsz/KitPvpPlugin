package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Brawler extends Kit {
    public String kitName = "Brawler";
    private com.sgpvp.main main;

    public void Brawler(com.sgpvp.main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        int extraHealthBoost = 0;

        PotionEffect brawlerExtraHealth = new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000000, extraHealthBoost);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {
                brawlerExtraHealth.apply(player);
                player.setHealth(player.getHealth() + 2);

            }
        }
        return false;
    }

    @EventHandler
    public void WhenFistOut(PlayerInteractEvent e){
        int damageBoost = 0; //dmg 1
        int damageBoostTime = 10;

        PotionEffect brawlerDamage = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, damageBoostTime, damageBoost);

        Action action = e.getAction();
        Player player = e.getPlayer();
        Inventory inv = e.getPlayer().getInventory();

        // If Player Isnt holding an Item, give more damage per punch
        if(!e.hasItem() && PlayerData.playerHasKitActive(e.getPlayer(), "brawler")){
            brawlerDamage.apply(player);
        }
    }
}
