package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Viper extends Kit implements Listener {
    private Kit_PvP_Minecraft main;

    public void Viper(Kit_PvP_Minecraft main){
        this.main = main;
    }
    public String kitName = "Viper";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }

    @EventHandler
    public void onHitEnemy(EntityDamageByEntityEvent event){
        if (!(event.getEntity() instanceof Player)) return; // Not a player getting hit
        if (!(event.getDamager() instanceof Player)) return; // Not a player hitting

        Player playerWKit = (Player) event.getEntity();
        Player playerBeingDamaged = (Player) event.getDamager();
        PotionEffect viperPoision = new PotionEffect(PotionEffectType.POISON, 80, 0);

        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            if(PlayerStorage.playerHasKitActive(playerBeingDamaged, "viper")){

                double randomNumber = Math.random() * 10;

                if(randomNumber < 7){
                    return;
                } else {
                    viperPoision.apply(playerWKit);
                }
            }
        }
    }
}
