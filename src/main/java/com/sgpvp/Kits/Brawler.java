package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Brawler extends Kit {
    public String kitName = "Brawler";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        int extraHealthBoost = 0;

        PotionEffect brawlerExtraHealth = new PotionEffect(PotionEffectType.HEALTH_BOOST, 10000000, extraHealthBoost);

        brawlerExtraHealth.apply(player);
        player.setHealth(player.getHealth() + 2);
        /* Kit functionality ends here */
    }

    @EventHandler
    public void WhenFistOut(PlayerInteractEvent e){
        int damageBoost = 0; //dmg 1
        int damageBoostTime = 10;

        PotionEffect brawlerDamage = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, damageBoostTime, damageBoost);

        Player player = e.getPlayer();

        // If Player isn't holding an Item, give more damage per punch
        if(!e.hasItem() && PlayerData.playerHasKitActive(e.getPlayer(), "brawler")){
            brawlerDamage.apply(player);
        }
    }
}
