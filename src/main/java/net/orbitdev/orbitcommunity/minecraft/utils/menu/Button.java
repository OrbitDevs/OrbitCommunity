package net.orbitdev.orbitcommunity.minecraft.utils.menu;

import net.orbitdev.orbitcommunity.minecraft.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    public static Button placeholder(Material material, final int data, String name, boolean glow) {
        return (new Button() {
            public ItemStack getButtonItem(Player player) {
                return new ItemBuilder(material).data(data).name(name).glow(glow).build();
            }
        });
    }

    public static void playFail(Player player) {
        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 20F, 0.1F);

    }

    public static void playSuccess(Player player) {
        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 20F, 15F);
    }

    public static void playNeutral(Player player) {
        player.playSound(player.getLocation(), Sound.DIG_GRASS, 20F, 1F);
    }

    public abstract ItemStack getButtonItem(Player player);

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {

    }
    public void close(Player player) {
        player.closeInventory();
    }

    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return false;
    }

    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean shouldShift(Player player, int slot, ClickType clickType) {
        return true;
    }
}
