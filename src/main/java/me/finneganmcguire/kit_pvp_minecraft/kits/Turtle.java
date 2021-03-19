package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Turtle implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void Turtle(Kit_PvP_Minecraft main){
        this.main = main;
    }

    String kitDescription = "The Turtle Kit Is A Very Good At Defense. \nHolding Sneak/Shift Gives You Resistance 2 & Weakness 2";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(Kit_PvP_Minecraft.canChangeKit){
                // REMOVE POTION EFFECTS
                for(PotionEffect effect : player.getActivePotionEffects()){
                    player.removePotionEffect(effect.getType());
                }

                Inventory inv = player.getInventory();
                inv.clear();
                player.sendMessage("You Have Chosen: " + ChatColor.GREEN + " TURTLE! ");
                PlayerStorage.setPlayerNewKit(player.getPlayer(), "turtle");
                player.sendMessage(Kit_PvP_Minecraft.kitDescriptionColor  + kitDescription);
            } else{
                player.sendMessage(ChatColor.RED + "Sorry You Cannot Change Kits During The Match");
            }

        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }
        return false;
    }

    @EventHandler
    public void WhenShiftHandler(PlayerToggleSneakEvent p) {

        int damageBoost = 1;
        int damageBoostTime = 1000000000;

        PotionEffect turtleShield = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, damageBoostTime, damageBoost);
        PotionEffect turtleDebuf = new PotionEffect(PotionEffectType.WEAKNESS, 1000000000, 1);


        // WHEN PLAYER PRESSES SHIFT
        if(!p.getPlayer().isSneaking() && PlayerStorage.playerHasKitActive(p.getPlayer(), "turtle")){
            turtleShield.apply(p.getPlayer());
            turtleDebuf.apply(p.getPlayer());
        }

        // WHEN PLAYER RELEASES SHIFT
        else if(p.getPlayer().isSneaking() && PlayerStorage.playerHasKitActive(p.getPlayer(), "turtle")){
            p.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            p.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
        }

    }
}
