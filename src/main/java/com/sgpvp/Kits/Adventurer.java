package com.sgpvp.Kits;

import com.sgpvp.GameData.PlayerData;
import com.sgpvp.Kits.KitConfig.Quest;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Random;

public class Adventurer extends Kit{

    public String kitName = "Adventurer";

    Sound questCompleteSound = Sound.UI_TOAST_CHALLENGE_COMPLETE;

    public static HashMap<Player, Quest> playerQuests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Pass kit name and sender data to parent.
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */

        ItemStack map = new ItemStack(Material.MAP);
        ItemMeta map_meta = map.getItemMeta();
        map_meta.setDisplayName(ChatColor.YELLOW + "The Adventurers Map");
        map.setItemMeta(map_meta);

        player.getInventory().addItem(new ItemStack(map));

        Quest hasQuest = playerQuests.get(player);
        if(hasQuest == null) {
            Random random = new Random();
            int totalAmountOfQuests = 3;
            int random_int = random.nextInt(totalAmountOfQuests);
            if (random_int == 0) { playerQuests.put(player, new Quest(player, ChatColor.GREEN + "Easy Quest: Kill 10 Mobs", BarColor.GREEN, BarStyle.SEGMENTED_10, "MobQuest")); }
            if (random_int == 1) { playerQuests.put(player, new Quest(player, ChatColor.GREEN + "Easy Quest: Mine 10 Coal Ore", BarColor.GREEN, BarStyle.SEGMENTED_10, "CoalQuest")); }
            if (random_int == 2) { playerQuests.put(player, new Quest(player, ChatColor.GREEN + "Easy Quest: Craft 20 Bowls", BarColor.GREEN, BarStyle.SEGMENTED_20, "BowlQuest")); }
        } else {
            hasQuest.questVisible(true);
        }
        /* Kit functionality ends here */
    }

    // Medium Quests
    public void newRandomMediumQuest(Player player, Quest current){
        Random random = new Random();
        int totalAmountOfQuests = 1;
        int random_int = random.nextInt(totalAmountOfQuests);
        if(random_int == 0){ playerQuests.get(player).setTitle(ChatColor.BOLD + "Medium Quest: Smelt 10 Pieces Of Iron Ore"); current.setProgress(0); current.setTag("SmeltIronQuest"); current.setBarColor(BarColor.WHITE);}
    }

    // Hard Quests
    public void newRandomHardQuest(Player player, Quest current){
        Random random = new Random();
        int totalAmountOfQuests = 1;
        int random_int = random.nextInt(totalAmountOfQuests);
        if(random_int == 0){ playerQuests.get(player).setTitle(ChatColor.RED + "Hard Quest: Kill 2 Players"); current.setProgress(0); current.setTag("KillQuest"); current.setBarColor(BarColor.RED); current.setBarStyle(BarStyle.SOLID);}
    }

    // Exotic Quests
    public void newRandomExoticQuest(Player player, Quest current){
        Random random = new Random();
        int totalAmountOfQuests = 1;
        int random_int = random.nextInt(totalAmountOfQuests);
        if(random_int == 0){ playerQuests.get(player).setTitle(ChatColor.LIGHT_PURPLE + "Exotic Quest: Kill 10 Players"); current.setProgress(0); current.setTag("ExoticKillQuest"); current.setBarColor(BarColor.PURPLE); current.setBarStyle(BarStyle.SEGMENTED_10);}
    }

    // Hard Quests

    /* Kit event handlers start here */
    @EventHandler
    public void AnimalQuest(EntityDeathEvent e){
        Player player =  e.getEntity().getKiller();

        if(!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;

        if(playerQuests.get(player).getActiveTag().equals("MobQuest")){
            playerQuests.get(player).increment(0.1);

            if(playerQuests.get(player).getProgress() > 0.9){
                player.playSound(player.getLocation(), questCompleteSound, 1, 2);
                player.getInventory().addItem(Quest.mobQuestReward());

                newRandomMediumQuest(player, playerQuests.get(player));
            }
        }

        if(playerQuests.get(player).getActiveTag().equals("KillQuest")){
            if(!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
            playerQuests.get(player).increment(0.5);

            if(playerQuests.get(player).getProgress() > 0.8){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);
                player.getInventory().addItem(Quest.killPlayersQuestReward());

                newRandomExoticQuest(player, playerQuests.get(player));
            }
        }

        if(playerQuests.get(player).getActiveTag().equals("ExoticKillQuest")){
            if(!(e.getEntity().getType().equals(EntityType.PLAYER))) return;
            playerQuests.get(player).increment(0.1);

            if(playerQuests.get(player).getProgress() > 0.9){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);
                player.getInventory().addItem(Quest.exoticKillPlayersQuestReward());
            }
        }
    }

    @EventHandler
    public void MineQuest(BlockBreakEvent e){
        Player player =  e.getPlayer();

        if(!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;

        if(playerQuests.get(player).getActiveTag().equals("CoalQuest")){
            if(!(e.getBlock().getType().equals(Material.COAL_ORE))) return;
            playerQuests.get(player).increment(0.1);

            if(playerQuests.get(player).getProgress() > 0.9){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);
                player.getInventory().addItem(Quest.coalQuestReward());

                newRandomMediumQuest(player, playerQuests.get(player));
            }
        }

    }

    @EventHandler
    public void SmeltIronQuest(FurnaceExtractEvent e){
        Player player = e.getPlayer();

        if(!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;
        if(!(playerQuests.get(player).getActiveTag().equals("SmeltIronQuest"))) return;
        if(e.getItemType().equals(Material.IRON_INGOT)){
            playerQuests.get(player).increment(e.getItemAmount() * 0.1);

            if(playerQuests.get(player).getProgress() > 0.9){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);
                player.getInventory().addItem(Quest.smeltIronQuestReward());

                newRandomHardQuest(e.getPlayer(), playerQuests.get(player));
            }
        }
    }

    @EventHandler
    public void OnCraft(CraftItemEvent e){
        Player player = (Player) e.getWhoClicked();
        if(!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;

        if(playerQuests.get(player).getActiveTag().equals("BowlQuest")){
            if(!(e.getInventory().getResult().getType().equals(Material.BOWL))) return;

            playerQuests.get(player).increment(0.1);
            if(playerQuests.get(player).getProgress() > 0.9){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);
                player.getInventory().addItem(Quest.bowlQuestReward());

                newRandomMediumQuest(player, playerQuests.get(player));
            }
        }


    }
    /* Kit event handlers end here */
}
