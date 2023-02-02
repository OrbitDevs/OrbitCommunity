package net.orbitdev.orbitcommunity.minecraft.menus.editor;

import lombok.AllArgsConstructor;
import net.orbitdev.orbitcommunity.minecraft.menus.bug.BugMenu;
import net.orbitdev.orbitcommunity.minecraft.menus.suggestion.SuggestionMenu;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class EditorButton extends Button {

    private Editor editor;

    @Override
    public void clicked(Player player, int slot, ClickType type, int i) {
        if (this.editor.getName().equalsIgnoreCase("BUGS")) {
            Button.playSuccess(player);
            new BugMenu().openMenu(player);
        }
        else if (this.editor.getName().equalsIgnoreCase("LICENSES")) {
            Button.playSuccess(player);
        }
        else if (this.editor.getName().equalsIgnoreCase("SUGGESTIONS")) {
            Button.playSuccess(player);
            new SuggestionMenu().openMenu(player);
        }
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return this.editor.getIcon(player);
    }
}
