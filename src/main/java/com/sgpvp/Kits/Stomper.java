package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class Stomper extends Kit{
    public String kitName = "Stomper";
    public double stomperDamage = 3.0;
    public double stomperRange = 3.0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageEvent(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        double dist = p.getFallDistance();
        if (dist < 4) return;
        if (!PlayerData.playerHasKitActive(p, kitName.toLowerCase())) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            List<Entity> nearby = e.getEntity().getNearbyEntities(stomperRange, stomperRange, stomperRange);
            for (Entity ent: nearby)
                if (ent instanceof Player) {
                    ent.sendMessage(p.getName() + " just stomped on you!");
                    double damage =  dist;
                    ((org.bukkit.entity.LivingEntity) ent).damage(damage);
                }
        }
    }
}
