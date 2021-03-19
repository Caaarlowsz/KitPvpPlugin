package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Wolverine implements CommandExecutor, Listener {

    private Kit_PvP_Minecraft main;

    public void WolverineKit(Kit_PvP_Minecraft main){
        this.main = main;
    }

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

                PlayerStorage.setPlayerNewKit(player.getPlayer(), "wolverine");

                player.sendMessage("You Have Chosen: " + ChatColor.DARK_RED + " WOLVERINE! ");

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
    public void NightListener(PlayerInteractEvent p){

        //Health Buff
        int AddedHealthBonus = 1;
        int HealthBonusTime = 10000;
        // Damage Buff
        int damageIncrease = 1;
        int damageIncreaseTime = 10000;
        // Speed Buff
        int speedIncrease = 1;
        int speedIncreaseTime = 10000;

        // Time Effects Come In
        int timeToTurn = 12000;

        PotionEffect wolverineHealth = new PotionEffect(PotionEffectType.HEALTH_BOOST, HealthBonusTime, AddedHealthBonus);
        PotionEffect wolverineStrength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, damageIncreaseTime, damageIncrease);
        PotionEffect wolverineSpeed = new PotionEffect(PotionEffectType.SPEED, speedIncreaseTime, speedIncrease);

        if(PlayerStorage.playerHasKitActive(p.getPlayer(), "wolverine")){
            if(p.getAction().equals(Action.PHYSICAL)){
                
            }
        }
    }
}
