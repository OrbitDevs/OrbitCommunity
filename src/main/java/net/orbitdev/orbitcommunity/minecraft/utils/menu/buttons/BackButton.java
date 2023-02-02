package net.orbitdev.orbitcommunity.minecraft.utils.menu.buttons;

import lombok.AllArgsConstructor;
import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.ItemBuilder;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class BackButton extends Button {

    private final Menu back;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(OrbitCommunity.get().getSettingsConfig().getString("BUTTONS.BACK_BUTTON.MATERIAL"))
                .name(OrbitCommunity.get().getSettingsConfig().getString("BUTTONS.BACK_BUTTON.NAME"))
                .lore(OrbitCommunity.get().getSettingsConfig().getStringList("BUTTONS.BACK_BUTTON.LORE"))
                .data(OrbitCommunity.get().getSettingsConfig().getInt("BUTTONS.BACK_BUTTON.DATA"))
                .glow(OrbitCommunity.get().getSettingsConfig().getBoolean("BUTTONS.BACK_BUTTON.GLOW"))
                .build();

    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        Button.playNeutral(player);
        this.back.openMenu(player);
    }
}