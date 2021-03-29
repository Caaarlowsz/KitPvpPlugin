package me.finneganmcguire.kit_pvp_minecraft.kits;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    private Kit_PvP_Minecraft main;

    public void Chameleon(Kit_PvP_Minecraft main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.passName(kitName);
        super.onCommand(sender, command, label, args);
        return false;
    }

    @EventHandler
    public void ChangeToEntity(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();

        if(PlayerStorage.playerHasKitActive(player, "chameleon")){
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
            if(PlayerStorage.playerHasKitActive(playerhit, "chameleon")){
                if(DisguiseAPI.isDisguised(e.getEntity())){
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    console.getServer().dispatchCommand(console, "undisguiseplayer " + playerhit.getName());
                }
            }
        }
    }
}
