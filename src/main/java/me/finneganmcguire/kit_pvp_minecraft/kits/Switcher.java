package me.finneganmcguire.kit_pvp_minecraft.kits;

        import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
        import org.bukkit.ChatColor;
        import org.bukkit.Location;
        import org.bukkit.Material;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandSender;
        import org.bukkit.entity.Entity;
        import org.bukkit.entity.Player;
        import org.bukkit.entity.Snowball;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.entity.EntityDamageByEntityEvent;
        import org.bukkit.event.entity.EntityDamageEvent;
        import org.bukkit.event.entity.ProjectileHitEvent;
        import org.bukkit.event.entity.ProjectileLaunchEvent;
        import org.bukkit.inventory.Inventory;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.PlayerInventory;
        import org.bukkit.inventory.meta.ItemMeta;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

        import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.PROJECTILE;

/*
    IMPORTANT!
    For the game to register this new kit the following must be done:
        - The command for this kit must be added to the plugin.yml
        - A reference to this kit must be added to the kits table
          in the Kit_PvP_Minecraft class.
        - If the kit contains event handlers then the kit must be
          registered in Kit_PvP_Minecraft (pluginManager.registerEvents)
        - A kit description and color must also be added in the
          KitConfig.KitDescriptions class
 */

public class Switcher extends Kit{
    public String kitName = "Switcher";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        ItemStack switcherBalls = new ItemStack(Material.SNOWBALL, 10);
        ItemMeta meta = switcherBalls.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls"));
        switcherBalls.setItemMeta(meta);

        ((Player)sender).getInventory().addItem(switcherBalls);
        return false;
    }

    List<UUID> switcherBalls = new ArrayList<UUID>();
    ItemStack switcherBall;

    //Marks the thrown snowball as a Switcher's Orb
    @EventHandler
    public void markSnowball(ProjectileLaunchEvent event) {
    if(event.getEntity() instanceof Snowball && event.getEntity().getShooter() instanceof Player && ((Player) event.getEntity().getShooter()).hasPermission("kit.switcher")) {
        Player player = (Player) event.getEntity().getShooter();
        PlayerInventory inventory = player.getInventory();
        if (!PlayerStorage.playerHasKitActive(player, kitName.toLowerCase())) return;
        if(inventory.getItemInMainHand().getType() != Material.AIR &&
            inventory.getItemInMainHand().getType() == Material.SNOWBALL &&
            inventory.getItemInMainHand().getItemMeta().hasDisplayName() &&
            inventory.getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls"))) {

            switcherBalls.add(event.getEntity().getUniqueId());
            switcherBall = inventory.getItemInMainHand();
        }
        else if(inventory.getItemInOffHand().getType() != Material.AIR &&
            inventory.getItemInOffHand().getType() == Material.SNOWBALL &&
            inventory.getItemInOffHand().getItemMeta().hasDisplayName() &&
            inventory.getItemInOffHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes ('&', "&b&lSwitcher Balls"))) {
                if(inventory.getItemInMainHand().getType() != Material.SNOWBALL) {
                    switcherBalls.add(event.getEntity().getUniqueId());
                    switcherBall = inventory.getItemInOffHand();
                }
            }
        }
    }

    //[Switch!] Active Ability
    @EventHandler
    public void switcher(ProjectileHitEvent event) {
    //If entity that hit another entity is a snow ball AND the entity hit was a player
    if (event.getEntity() instanceof Snowball && switcherBalls.contains(event.getEntity().getUniqueId()) && event.getHitEntity() instanceof Player) {
        //Temporarily store the snow ball to get the player who threw the snow ball
        Snowball snowball = (Snowball) event.getEntity();
        //Store the player in a variable
        Player player = (Player) snowball.getShooter();
        //Save the switcher's current location
        Location switcherLocation = player.getLocation();
        //Save the victim's current location
        Location victimLocation = event.getHitEntity().getLocation();
        //Teleport the switcher to the victim's saved location
        player.teleport(victimLocation);
        //Teleport the victim to the switcher's saved location
        event.getHitEntity().teleport(switcherLocation);
        //Send a confirmation message
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou switched locations with " + (((Player) event.getHitEntity()).getDisplayName() + ".")));
        ((Player)event.getHitEntity()).sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou switched locations with " + (player.getDisplayName() + ".")));
        //
        switcherBalls.remove(snowball.getUniqueId());
        }
    }
}

//if (!PlayerStorage.playerHasKitActive((Player) damager, kitName.toLowerCase())) return;