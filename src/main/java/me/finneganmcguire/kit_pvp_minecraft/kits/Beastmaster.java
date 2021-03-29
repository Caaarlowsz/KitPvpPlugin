package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.GameData.GameVariables;
import me.finneganmcguire.kit_pvp_minecraft.GameData.PlayerData;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Beastmaster extends Kit {
    public String kitName = "Beastmaster";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (GameVariables.canChangeKit) {
                //Beastmaster Items
                ItemStack bones = new ItemStack(Material.BONE, 4);
                ItemStack wolfSpawnEggs = new ItemStack(Material.WOLF_SPAWN_EGG, 2);

                Inventory inv = player.getInventory();
                inv.addItem(bones, wolfSpawnEggs);
            }
        }
        return false;
    }

    @EventHandler
    public void WhenFistOut(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof Wolf)) return; // Not a wolf clicked

        Wolf wolf = (Wolf) e.getRightClicked();
        e.getPlayer().getLocation();
        int amountOfBones = e.getPlayer().getInventory().getItemInMainHand().getAmount();

        if(PlayerData.playerHasKitActive(e.getPlayer(), "beastmaster")){
            if(e.getRightClicked().getType().equals(EntityType.WOLF)){
                if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BONE)){
                    if(!wolf.isTamed()){
                        wolf.setTamed(true);
                        wolf.setOwner(e.getPlayer());
                        amountOfBones--;
                        e.getPlayer().getInventory().getItemInMainHand().setAmount(amountOfBones);
                    }
                }
            }
        }
    }
}
