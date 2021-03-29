package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class Hades extends Kit{
    public String kitName = "Hades";
    private Kit_PvP_Minecraft main;

    public Hades(Kit_PvP_Minecraft main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (GameVariables.canChangeKit) {

                /*
                //Hades' Ingot
                Material hadesIngot = Material.IRON_INGOT;
                ItemStack hadesIngot = new ItemStack(iron_ingot);
                ItemMeta iron_ingot_meta = iron_ingot_meta.getItemMeta();

                //Ingot Meta Data
                iron_ingot_meta.setDisplayName(ChatColor.RED + "Hades' Reckoning");
                iron_ingot_meta.setLore(Arrays.asList(ChatColor.RED + "This ingot is used to control the minds and hearts of the world.\n"));
                iron_ingot.setItemMeta(iron_ingot_meta);
                */

                //Inventory
                Inventory inv = player.getInventory();
                //inv.addItem(iron_ingot);
            }
        }

        return false;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityTarget(EntityTargetLivingEntityEvent evt) {
        if (evt.getTarget() instanceof Player) {
            evt.setCancelled(true);
            evt.setTarget(null);
        }
        //If the mob is already targeting another entity
        if (evt.getEntity() instanceof Mob) {
            Mob mob = (Mob) evt.getEntity();
            if (mob.getTarget() instanceof Player) {
                mob.setTarget(null);
            }
        }
    }
}

