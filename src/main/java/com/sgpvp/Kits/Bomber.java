package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Bomber extends Kit{
    public Material kitItem = Material.TNT;
    public String kitName = "Bomber"; // Try to keep this the same as the class name <3
    public int explosionStrength = 2; // 3 is about a tnt blast

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(final EntityDamageEvent e){
        if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
        if (!PlayerData.playerHasKitActive((Player) e.getEntity(), kitName.toLowerCase())) return;
        if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
            e.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(final EntityDeathEvent e){
        if (!(e.getEntity().getKiller().getType().equals(EntityType.PLAYER))) return;
        Player killer = e.getEntity().getKiller();
        //GameVariables.SGPvPMessage(killer, "you exploded " + e.getEntity().getName());
        if (!PlayerData.playerHasKitActive(killer, kitName.toLowerCase())) return;
        Location explosionLocation = e.getEntity().getLocation();
        GameVariables.world.createExplosion(explosionLocation,explosionStrength, false, true, killer);
    }
    /* Kit event handlers end here */
}
