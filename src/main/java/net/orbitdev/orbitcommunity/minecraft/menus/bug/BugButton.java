package net.orbitdev.orbitcommunity.minecraft.menus.bug;

import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.HeadsUtil;
import net.orbitdev.orbitcommunity.minecraft.utils.ItemBuilder;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import net.orbitdev.orbitcommunity.shared.bug.Bug;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BugButton extends Button {

    private final Bug bugs;

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        OrbitCommunity.get().getBugsConfig().getStringList("BUGS.LORE").forEach(text -> {
            if (text.contains("%head%")) {
                for (String line : HeadsUtil.getHead(bugs.getPlayerDisplayName())) {
                    lore.add(line);
                }
                return;
            } else {
                lore.add(text.replace("%id%", String.valueOf(bugs.getId())).replace("%player%", bugs.getPlayerDisplayName()).replace("%status%", bugs.getStatus().toString()).replace("%category%", bugs.getCategory().toString()).replace("%bug%", bugs.getSuggestion()));
            }
        });


        ItemBuilder material = new ItemBuilder(Material.STAINED_GLASS_PANE);
        material.name(OrbitCommunity.get().getBugsConfig().getString("BUGS.NAME").replace("%id%", String.valueOf(bugs.getId())).replace("%player%", bugs.getPlayerDisplayName()).replace("%status%", bugs.getStatus().toString()).replace("%category%", bugs.getCategory().toString()));
        material.lore(lore);
        return material.build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType type, int i) {
    }


    public BugButton(Bug bugs) {
        this.bugs = bugs;
    }

}