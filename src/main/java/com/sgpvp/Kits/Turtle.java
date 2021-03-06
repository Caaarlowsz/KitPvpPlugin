package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Turtle extends Kit {

    public String kitName = "Turtle";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }

    @EventHandler
    public void WhenShiftHandler(PlayerToggleSneakEvent p) {

        int damageBoost = 1;
        int damageBoostTime = 1000000000;

        PotionEffect turtleShield = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, damageBoostTime, damageBoost);
        PotionEffect turtleDebuff = new PotionEffect(PotionEffectType.WEAKNESS, 100000000, 0);


        // WHEN PLAYER PRESSES SHIFT
        if(!p.getPlayer().isSneaking() && PlayerData.playerHasKitActive(p.getPlayer(), "turtle")){
            turtleShield.apply(p.getPlayer());
            turtleDebuff.apply(p.getPlayer());
        }

        // WHEN PLAYER RELEASES SHIFT
        else if(p.getPlayer().isSneaking() && PlayerData.playerHasKitActive(p.getPlayer(), "turtle")){
            p.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
        }

    }
}
