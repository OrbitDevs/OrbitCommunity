package net.orbitdev.orbitcommunity.minecraft.utils.menu.pagination;

import lombok.AllArgsConstructor;
import net.orbitdev.orbitcommunity.minecraft.utils.CC;
import net.orbitdev.orbitcommunity.minecraft.utils.ItemBuilder;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class JumpToPageButton extends Button {

    private final int page;
    private final PaginatedMenu menu;
    private final boolean current;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.current ? Material.ENCHANTED_BOOK : Material.BOOK, this.page);
        itemBuilder.name(CC.translate("&cPage " + this.page));

        if (this.current) {
            itemBuilder.lore(
                    "",
                    CC.translate("&aCurrent page")
            );
        }

        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        this.menu.modPage(player, this.page - this.menu.getPage());
        Button.playNeutral(player);
    }
}