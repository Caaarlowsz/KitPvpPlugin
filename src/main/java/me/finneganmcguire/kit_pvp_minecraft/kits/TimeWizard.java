package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class TimeWizard implements CommandExecutor {

    private Kit_PvP_Minecraft main;

    public void TimeWizardKit(Kit_PvP_Minecraft main){
        this.main = main;
    }

    public boolean kit_TimeWizardActive;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if(sender instanceof Player){
            Player player = (Player) sender;

            // REMOVE POTION EFFECTS
            for(PotionEffect effect : player.getActivePotionEffects()){
                player.removePotionEffect(effect.getType());
            }

            Material timewizClock = Material.CLOCK;
            ItemStack clock = new ItemStack(timewizClock);
            ItemMeta timewizclockmeta = clock.getItemMeta();

            PlayerStorage.setPlayerNewKit(player.getPlayer(), "timewizard");

            // ITEM DATA
            timewizclockmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Wizard Clock");
            timewizclockmeta.addEnchant(Enchantment.CHANNELING, 10, true);
            timewizclockmeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "This used to belong to one of the greatest wizards of all time...\n",ChatColor.WHITE + "Current Owner: " + player.getDisplayName()));
            timewizclockmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            // SET ITEM SATA
            clock.setItemMeta(timewizclockmeta);

            Inventory inv = player.getInventory();
            inv.clear();
            inv.addItem(clock);
            player.sendMessage("You Have Chosen: " + ChatColor.LIGHT_PURPLE + " TIME WIZARD! ");

        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }

    @EventHandler
    public void ClockActivated(PlayerInteractEvent p) {
        Player player = p.getPlayer();

        if(p.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(p.getItem().getType().equals(Material.CLOCK)){
                if(p.getItem().getItemMeta().getDisplayName().equals("Wizard Clock")){
                    if(PlayerStorage.playerHasKitActive(p.getPlayer(), "timewizard")){
                        // Time Wizard Clock Effects

                    }
                }
            }

        }
    }

    public void OnClockActivated(Player p){
        int PlayerX = p.getLocation().getBlockX();
        int PlayerZ = p.getLocation().getBlockZ();

    }
}
