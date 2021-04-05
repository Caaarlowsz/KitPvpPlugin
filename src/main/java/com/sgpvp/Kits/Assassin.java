package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import com.sgpvp.GameLogic.ProgressBar;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Assassin extends Kit{
    public String kitName = "Assassin";
    public int attackCooldown = 10 * 1000;
    public int damageBuff = 3;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if (!GameVariables.canChangeKit) return false;

        /* Kit functionality starts here */



        /* Kit functionality ends here */

        return false;
    }
    /* Kit event handlers start here */
    @EventHandler
    public void onSneak(final PlayerToggleSneakEvent e){
        Player player = e.getPlayer();
        if (!PlayerData.playerHasKitActive(player, kitName.toLowerCase())) return;
        if (cooldowns.containsKey(player) && cooldowns.get(player)) return;
        Thread sneaking = new Thread(new Sneaking(player));
        sneaking.start();
    }
    HashMap<Player, Boolean> cooldowns = new HashMap<>();
    HashMap<Player, Boolean> powerAttack = new HashMap<>();
    private class Sneaking extends Thread {
        Player player;
        Sneaking(Player p) { this.player = p; }
        public void run() {
            ProgressBar progress = new ProgressBar(player, "Attack charging: ", BarColor.BLUE, BarStyle.SOLID, 50);
            try {
                Thread.sleep(100);
                for (int s = 0; s < 50; s++) {
                    progress.increment();
                    if (!player.isSneaking()) {
                        progress.removeAll();
                        return;
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cooldowns.put(player, true);
            progress.removePlayer(player);
            GameVariables.SGPvPMessage(player, "Your attack is now charged!");
            powerAttack.put(player, true);
            try {
                Thread.sleep(attackCooldown);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cooldowns.put(player, false);
        }
    }
    @EventHandler
    public void onHitEnemy(EntityDamageByEntityEvent event){
        if (!(event.getDamager() instanceof Player)) return;
        Player attacker = (Player) event.getDamager();
        if (!powerAttack.containsKey(attacker))
            powerAttack.put(attacker, false);
        if (!powerAttack.get(attacker)) return;
        double damage = event.getDamage();
        damage += damageBuff;
        event.setDamage(damage);
        powerAttack.put(attacker, false);
        GameVariables.SGPvPMessage(attacker, "Your hit dealt massive damage.");
    }
    /* Kit event handlers end here */
}
