package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Vampire extends Kit{
    public String kitName = "Vampire";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (!GameVariables.canChangeKit) return false;
        return false;
    }
    /* Kit event handlers start here */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKill(final EntityDeathEvent e){
        if (!(e.getEntity().getKiller().getType().equals(EntityType.PLAYER))) return;
        if (!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
        Player killer = e.getEntity().getKiller();
        if (!PlayerData.playerHasKitActive(killer, kitName.toLowerCase())) return;
        GameVariables.SGPvPMessage(killer, "A kill replenished your health.");
        if (killer.getHealth() != 20.0D && killer.getHealth() != 0.0D)
            killer.setHealth(20.0D);
    }
    /* Kit event handlers end here */
}
