package com.sgpvp.Kits;

import com.sgpvp.GameData.GameVariables;
import com.sgpvp.GameData.PlayerData;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Chameleon extends Kit {
    public String kitName = "Chameleon";
    private com.sgpvp.main main;

    public void Chameleon(com.sgpvp.main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        return super.onCommand(sender, command, label, args);
    }
    void initializeKit(Player player) {
        /* Kit functionality starts here */



        /* Kit functionality ends here */
    }

    @EventHandler
    public void ChangeToEntity(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();

        if(PlayerData.playerHasKitActive(player, "chameleon")){
            EntityType playerRightClickEntity = e.getRightClicked().getType();
            DisguiseType mobType = DisguiseType.getType(playerRightClickEntity);
            MobDisguise mobDisguise = new MobDisguise(mobType, true);

            DisguiseAPI.disguiseEntity(e.getPlayer(), mobDisguise);
            e.getPlayer().sendMessage("You are now disguised as a " + ChatColor.BOLD + playerRightClickEntity.getName());
        }
    }

    //When hit undisguise the hit player
    @EventHandler
    public void IfHit(EntityDamageEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER){
            Player playerhit = (Player) e.getEntity();
            if(PlayerData.playerHasKitActive(playerhit, "chameleon")){
                if(DisguiseAPI.isDisguised(e.getEntity())){
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    console.getServer().dispatchCommand(console, "undisguiseplayer " + playerhit.getName());
                }
            }
        }
    }
}
