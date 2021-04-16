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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

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
            int totalAmountOfQuests = 2;
            int random_int = random.nextInt(totalAmountOfQuests);
            if (random_int == 0) { playerQuests.put(player, new Quest(player, ChatColor.GREEN + "Novice Hunter: Kill 10 Mobs", BarColor.GREEN, BarStyle.SEGMENTED_10, "MobQuest", 10)); }
            if (random_int == 1) { playerQuests.put(player, new Quest(player, ChatColor.GREEN + "Novice Miner: Mine 10 Coal Ore", BarColor.GREEN, BarStyle.SEGMENTED_10, "CoalQuest", 10)); }
        } else {
            hasQuest.questVisible(true);
        }
        /* Kit functionality ends here */
    }

    double questBarComplete = 0.90d;

    // Medium Quests
    public void newRandomMediumQuest(Player player, Quest current){
        Random random = new Random();
        int totalAmountOfQuests = 2;
        int random_int = random.nextInt(totalAmountOfQuests);
        if(random_int == 0){ playerQuests.get(player).setTitle(ChatColor.BOLD + "King Of The Furnace: Smelt 10 Pieces Of Iron Ore"); current.setProgress(0d); current.setTag("SmeltIronQuest"); current.setBarColor(BarColor.WHITE); current.setMax(10d);}
        if(random_int == 1){ playerQuests.get(player).setTitle(ChatColor.BOLD + "Well Trained Hunter: Kill 20 Mobs"); current.setProgress(0d); current.setTag("MediumMobQuest"); current.setBarColor(BarColor.WHITE); current.setBarStyle(BarStyle.SEGMENTED_20); current.setMax(20d);}

    }


    // Hard Quests
    public void newRandomHardQuest(Player player, Quest current){
        Random random = new Random();
        int totalAmountOfQuests = 2;
        int random_int = random.nextInt(totalAmountOfQuests);
        if(random_int == 0){ playerQuests.get(player).setTitle(ChatColor.RED + "" + ChatColor.BOLD + "Expert Hunter: Kill 30 Mobs"); current.setProgress(0d); current.setTag("HardMobQuest"); current.setBarColor(BarColor.RED); current.setBarStyle(BarStyle.SOLID); current.setMax(30d);}
        if(random_int == 1){ playerQuests.get(player).setTitle(ChatColor.RED + "" + ChatColor.BOLD + "Expert Smelter: Smelt 20 Pieces Of Iron Ore"); current.setProgress(0d); current.setTag("SmeltIronHardQuest"); current.setBarColor(BarColor.RED); current.setBarStyle(BarStyle.SOLID); current.setBarStyle(BarStyle.SEGMENTED_20); current.setMax(20d);}
    }

    // Exotic Quests
    public void newRandomExoticQuest(Player player, Quest current){
        Random random = new Random();
        int totalAmountOfQuests = 2;
        int random_int = random.nextInt(totalAmountOfQuests);
        if(random_int == 0){ playerQuests.get(player).setTitle(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "God Of The Hunt: Kill 40 Mobs"); current.setProgress(0d); current.setTag("ExoticMobQuest"); current.setBarColor(BarColor.PURPLE); current.setBarStyle(BarStyle.SEGMENTED_10); current.setMax(40d);}
        if(random_int == 1){ playerQuests.get(player).setTitle(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "God Of The Forge: Smelt 40 Iron Ore"); current.setProgress(0d); current.setTag("SmeltIronExoticQuest"); current.setBarColor(BarColor.PURPLE); current.setBarStyle(BarStyle.SEGMENTED_20); current.setMax(40d);}
    }

    /* Kit event handlers start here */
    @EventHandler
    public void AnimalQuest(EntityDeathEvent e){
        Player player =  e.getEntity().getKiller();
        Inventory playerInventory = player.getInventory();
        String currentTag = playerQuests.get(player).getActiveTag();

        if (!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;


        if(currentTag.equals("MobQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                playerInventory.addItem(Quest.easyRandomQuestReward(player));
            }
        }
        else if(currentTag.equals("MediumMobQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                playerInventory.addItem(Quest.mediumRandomQuestReward(player));
                newRandomHardQuest(player, playerQuests.get(player));
            }
        }
        else if(currentTag.equals("HardMobQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                playerInventory.addItem(Quest.hardRandomQuestReward(player));
                newRandomExoticQuest(player, playerQuests.get(player));
            }
        }
        else if(currentTag.equals("ExoticMobQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                playerInventory.addItem(Quest.exoticRandomQuestReward(player));
            }
        }
    }

    @EventHandler
    public void MineQuest(BlockBreakEvent e){
        Player player =  e.getPlayer();

        if(!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;

        if(playerQuests.get(player).getActiveTag().equals("CoalQuest")){
            if(!(e.getBlock().getType().equals(Material.COAL_ORE))) return;
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() > questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);
                player.getInventory().addItem(Quest.easyRandomQuestReward(player));

                newRandomMediumQuest(player, playerQuests.get(player));
            }
        }

    }

    @EventHandler
    public void SmeltIronQuest(FurnaceExtractEvent e){
        Player player = e.getPlayer();
        String currentTag = playerQuests.get(player).getActiveTag();
        Inventory inventory = player.getInventory();

        if(!(PlayerData.playerHasKitActive(player, kitName.toLowerCase()))) return;
        if(currentTag.equals("SmeltIronQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                inventory.addItem(Quest.mediumRandomQuestReward(player));
            }
        }
        else if(currentTag.equals("SmeltIronHardQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                inventory.addItem(Quest.hardRandomQuestReward(player));
            }
        }
        else if(currentTag.equals("ExoticMobHardQuest")){
            playerQuests.get(player).increment(1);

            if(playerQuests.get(player).getProgress() >= questBarComplete){
                player.playSound(player.getLocation(), questCompleteSound, 1, 1);

                inventory.addItem(Quest.exoticRandomQuestReward(player));
            }
        }
    }
    /* Kit event handlers end here */
}
