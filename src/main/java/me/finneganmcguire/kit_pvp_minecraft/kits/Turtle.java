package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
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

    public boolean turtleKit_Active;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            if(player.isOp()){
                Inventory inv = player.getInventory();
                inv.clear();

                player.sendMessage("You Have Chosen: " + ChatColor.GREEN + " TURTLE! ");
                turtleKit_Active = true;

            } else{
                player.sendMessage(ChatColor.RED + "YOU DO NOT HAVE ACCESS TO THIS KIT");
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

        // WHEN PLAYER PRESSES SHIFT
        if(!p.getPlayer().isSneaking() && turtleKit_Active){
            turtleShield.apply(p.getPlayer());
        }

        // WHEN PLAYER RELEASES SHIFT
        else if(p.getPlayer().isSneaking() && turtleKit_Active){
            p.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        }

    }
}
