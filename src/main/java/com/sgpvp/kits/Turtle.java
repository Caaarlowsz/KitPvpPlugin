package com.sgpvp.kits;

import com.sgpvp.GameData.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Turtle extends Kit {

    private com.sgpvp.main main;

    public void Turtle(com.sgpvp.main main){
        this.main = main;
    }
    public String kitName = "Turtle";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }

    @EventHandler
    public void WhenShiftHandler(PlayerToggleSneakEvent p) {

        int damageBoost = 1;
        int damageBoostTime = 1000000000;

        PotionEffect turtleShield = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, damageBoostTime, damageBoost);
        PotionEffect turtleDebuf = new PotionEffect(PotionEffectType.WEAKNESS, 100000000, 0);


        // WHEN PLAYER PRESSES SHIFT
        if(!p.getPlayer().isSneaking() && PlayerData.playerHasKitActive(p.getPlayer(), "turtle")){
            turtleShield.apply(p.getPlayer());
            turtleDebuf.apply(p.getPlayer());
        }

        // WHEN PLAYER RELEASES SHIFT
        else if(p.getPlayer().isSneaking() && PlayerData.playerHasKitActive(p.getPlayer(), "turtle")){
            p.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
        }

    }
}
