package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Snail extends Kit implements Listener {
    private Kit_PvP_Minecraft main;

    public void Snail(Kit_PvP_Minecraft main){
        this.main = main;
    }
    public String kitName = "Snail";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Kit_PvP_Minecraft.canChangeKit) {
                PlayerStorage.setPlayerNewKit(player.getPlayer(), "snail");
            }
        }
        return false;
    }

    @EventHandler
    public void onHitEnemy(EntityDamageByEntityEvent event){

        Player playerWKit = (Player) event.getEntity();
        Player playerBeingDamaged = (Player) event.getDamager();
        PotionEffect snailSlow = new PotionEffect(PotionEffectType.SLOW, 80, 0);

        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            if(PlayerStorage.playerHasKitActive(playerBeingDamaged, "snail")){

                double randomNumber = Math.random() * 10;

                if(randomNumber < 7){
                    return;
                } else {
                    snailSlow.apply(playerWKit);
                }
            }
        }
    }
}
