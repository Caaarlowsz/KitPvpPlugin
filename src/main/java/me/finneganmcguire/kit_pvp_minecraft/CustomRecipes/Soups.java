package me.finneganmcguire.kit_pvp_minecraft.CustomRecipes;

import me.finneganmcguire.kit_pvp_minecraft.Kit_PvP_Minecraft;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import javax.xml.stream.events.Namespace;

public class Soups {

    public static Kit_PvP_Minecraft plugin;

    public void Soups(Kit_PvP_Minecraft plugin){ this.plugin = plugin; }

    public static ShapelessRecipe cactiSoup(){
        ItemStack soup_item = new ItemStack(Material.MUSHROOM_STEW);

        NamespacedKey key = new NamespacedKey(plugin, "Cacti_Stew");

        ShapelessRecipe recipe = new ShapelessRecipe(key, soup_item);

        recipe.addIngredient(2, Material.CACTUS);
        recipe.addIngredient(1, Material.BOWL);

        return recipe;
    }
}
