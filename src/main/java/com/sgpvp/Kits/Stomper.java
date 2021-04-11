package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.List;

public class Stomper extends Kit{
    public String kitName = "Stomper";
    public double stomperRange = 3.0;
    public double minimumFallDistance = 4.0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }

    HashMap<Player, Float> fallingDistance = new HashMap<>();
    @EventHandler
    public void onFall(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        float distance = player.getFallDistance();
        if (distance == 0.0 && fallingDistance.containsKey(player)
            && fallingDistance.get(player) > minimumFallDistance)
            stomp(player, fallingDistance.get(player));
        fallingDistance.put(player, distance);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(final EntityDamageEvent e){
        if (!(e.getEntityType().equals(EntityType.PLAYER))) return;
        if (!PlayerData.playerHasKitActive((Player) e.getEntity(), kitName.toLowerCase())) return;
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            e.setDamage(Math.min(minimumFallDistance, e.getDamage()));
    }

    public void stomp(Player p, float dist) {
        List<Entity> nearby = p.getNearbyEntities(stomperRange, stomperRange, stomperRange);
        for (Entity ent: nearby) {
            if (ent instanceof Damageable) {
                Chat.SGPvPMessage(p, "You stomped on " + ent.getName());
                ent.sendMessage(p.getName() + " just stomped on you!");
                ((org.bukkit.entity.LivingEntity) ent).damage(dist);
            }
        }

    }
}
