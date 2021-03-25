package me.finneganmcguire.kit_pvp_minecraft.tasks;

import me.finneganmcguire.kit_pvp_minecraft.GameLogic.GracePeriodLogic;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft.*;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class NightTimeChecker extends BukkitRunnable {
    Kit_PvP_Minecraft plugin;

    public NightTimeChecker(Kit_PvP_Minecraft plugin){
        this.plugin = plugin;
    }

    public static boolean day;

    @Override
    public void run() {
        if(Kit_PvP_Minecraft.world.getTime() < 13000){
            // It is day
            day = true;
            sunwalkerEffect();
            werewolfEffect();
        } else if(Kit_PvP_Minecraft.world.getTime() >= 13000){
            // It is night
            day = false;
            sunwalkerEffect();
            werewolfEffect();
        }
    }

    public static boolean IsItDaytime(){
        return day;
    }


    // Effects When It Turns Night For Werewolf Players
    public void werewolfEffect(){
        for (int i = 0; i < Kit_PvP_Minecraft.world.getPlayers().size(); i++) {
            if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i),"werewolf")){

                // Potion Effects
                PotionEffect wolverineNightvision = new PotionEffect(PotionEffectType.NIGHT_VISION, 200000, 0);
                PotionEffect wolverineSpeed = new PotionEffect(PotionEffectType.SPEED, 200000, 1);
                PotionEffect wolverineSpectral = new PotionEffect(PotionEffectType.GLOWING, 200000, 0);

                if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i), "werewolf") && !day){
                    wolverineNightvision.apply(Kit_PvP_Minecraft.world.getPlayers().get(i));
                    wolverineSpeed.apply(Kit_PvP_Minecraft.world.getPlayers().get(i));
                    wolverineSpectral.apply(Kit_PvP_Minecraft.world.getPlayers().get(i));

                } else if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i), "werewolf") && day){
                    Kit_PvP_Minecraft.world.getPlayers().get(i).removePotionEffect(wolverineNightvision.getType());
                    Kit_PvP_Minecraft.world.getPlayers().get(i).removePotionEffect(wolverineSpeed.getType());
                    Kit_PvP_Minecraft.world.getPlayers().get(i).removePotionEffect(wolverineSpectral.getType());
                }
            }
        }
    }
    public void sunwalkerEffect(){
        for (int i = 0; i < Kit_PvP_Minecraft.world.getPlayers().size(); i++) {
            if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i),"sunwalker")){

                // Potion Effects
                PotionEffect sunwalkerSpeed = new PotionEffect(PotionEffectType.SPEED, 200000, 0);

                if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i), "sunwalker") && day){
                    sunwalkerSpeed.apply(Kit_PvP_Minecraft.world.getPlayers().get(i));

                } else if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i), "sunwalker") && !day){
                    Kit_PvP_Minecraft.world.getPlayers().get(i).removePotionEffect(sunwalkerSpeed.getType());
                }
            }
        }


    }
}
