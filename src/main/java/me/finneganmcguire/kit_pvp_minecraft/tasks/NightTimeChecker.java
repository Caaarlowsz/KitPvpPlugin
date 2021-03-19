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
            wolverineEffect();
        } else if(Kit_PvP_Minecraft.world.getTime() >= 13000){
            // It is night
            day = false;
            wolverineEffect();
        }
    }

    public static boolean IsItDaytime(){
        return day;
    }


    // Effects When It Turns Night For Wolverine
    public void wolverineEffect(){
        for (int i = 0; i < Kit_PvP_Minecraft.world.getPlayers().size(); i++) {
            if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i),"wolverine")){

                PotionEffect wolverineStrength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200000, 0);
                PotionEffect wolverineHealth = new PotionEffect(PotionEffectType.HEALTH_BOOST, 200000, 1);

                if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i), "wolverine") && !day){
                    wolverineHealth.apply(Kit_PvP_Minecraft.world.getPlayers().get(i));
                    wolverineStrength.apply(Kit_PvP_Minecraft.world.getPlayers().get(i));
                } else if(PlayerStorage.playerHasKitActive(Kit_PvP_Minecraft.world.getPlayers().get(i), "wolverine") && day){
                    Kit_PvP_Minecraft.world.getPlayers().get(i).removePotionEffect(wolverineHealth.getType());
                    Kit_PvP_Minecraft.world.getPlayers().get(i).removePotionEffect(wolverineStrength.getType());
                }
            }
        }


    }
}
