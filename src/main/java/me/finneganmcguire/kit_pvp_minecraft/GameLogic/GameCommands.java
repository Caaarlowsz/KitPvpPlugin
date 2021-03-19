package me.finneganmcguire.kit_pvp_minecraft.GameLogic;

import me.finneganmcguire.kit_pvp_minecraft.Player_Data.PlayerStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class GameCommands implements CommandExecutor {
    public static World e;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOp()) return false;
            if (args[0].equals("start")) {
                if (args[1].equals("deathmatch")) DeathmatchLogic.DeathmatchBegin(e);
                if (args[1].equals("game")) GameStartLogic.GameStart(e);
            }
            if (args[0].equals("feast")) FeastLogic.SpawnFeast(e);

        } else {
        }
        return false;
    }
}
