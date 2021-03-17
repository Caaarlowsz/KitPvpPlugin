package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
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

            // ITEM DATA
            timewizclockmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Wizard Clock");
            timewizclockmeta.addEnchant(Enchantment.CHANNELING, 10, true);
            timewizclockmeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "This used to belong to one of the greatest wizards of all time...\n",ChatColor.WHITE + "Current Owner: " + player.getDisplayName()));
            timewizclockmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            // SET ITEM SATA
            clock.setItemMeta(timewizclockmeta);

            kit_TimeWizardActive = true;

            if(player.isOp() && kit_TimeWizardActive){
                Inventory inv = player.getInventory();
                inv.clear();



                if(kit_TimeWizardActive){
                    inv.addItem(clock);
                }

                player.sendMessage("You Have Chosen: " + ChatColor.LIGHT_PURPLE + " TIME WIZARD! ");
            } else{
                player.sendMessage(ChatColor.RED + "YOU DO NOT HAVE ACCESS TO THIS KIT");
            }

        }
        else {
            main.getLogger().info("You Have To Be Player To Get Kit");
        }

        return false;
    }
}
