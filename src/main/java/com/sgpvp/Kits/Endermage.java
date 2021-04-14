package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.Chat;
import com.sgpvp.GameLogic.ProgressBar;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class Endermage extends Kit{
    public String kitName = "Endermage"; // Try to keep this the same as the class name <3
    public int cooldown = 50 * 1000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */
        Inventory inv = player.getInventory();
        inv.addItem(new ItemStack(Material.END_PORTAL_FRAME,1));
        /* Kit functionality ends here */
    }
    /* Kit event handlers start here */
    @EventHandler
    public void portalActivate(BlockPlaceEvent e){
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (!block.getType().equals(Material.END_PORTAL_FRAME)) return;
        List<Entity> nearby = player.getNearbyEntities(5, 256, 5);
        Location location = block.getLocation();
        for (Entity ent : nearby) {
            if (ent.getType().equals(EntityType.PLAYER)) {
                Player p = (Player) ent;
                if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                    Chat.SGPvPMessage(player, "You teleported " + ent.getName());
                    p.teleport(location);
                }
            }
        }
        Thread portal = new Thread(new Portal(player));
        portal.start();
        block.setType(Material.AIR);
    }

    private class Portal extends Thread {
        Player player;
        Portal(Player p) { this.player = p; }
        public void run(){
            ProgressBar cooldownProgress = new ProgressBar(player, "Cooldown: ", BarColor.GREEN, BarStyle.SOLID, cooldown);

            for (int i = 0; i < cooldown ; i++) {
                try {
                    Thread.sleep(1);
                    cooldownProgress.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Inventory inv = player.getInventory();
            inv.addItem(new ItemStack(Material.END_PORTAL_FRAME,1));
            cooldownProgress.removePlayer(player);
        }
    }
    /* Kit event handlers end here */
}
