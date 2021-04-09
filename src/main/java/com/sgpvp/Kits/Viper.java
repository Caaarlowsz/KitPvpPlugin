package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Viper extends Kit implements Listener {
    private com.sgpvp.main main;

    public void Viper(com.sgpvp.main main){
        this.main = main;
    }
    public String kitName = "Viper";

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
    public void onHitEnemy(EntityDamageByEntityEvent event){
        if (!(event.getEntity() instanceof Player)) return; // Not a player getting hit
        if (!(event.getDamager() instanceof Player)) return; // Not a player hitting

        Player playerWKit = (Player) event.getEntity();
        Player playerBeingDamaged = (Player) event.getDamager();
        PotionEffect viperPoision = new PotionEffect(PotionEffectType.POISON, 80, 1);

        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            if(PlayerData.playerHasKitActive(playerBeingDamaged, "viper")){

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
