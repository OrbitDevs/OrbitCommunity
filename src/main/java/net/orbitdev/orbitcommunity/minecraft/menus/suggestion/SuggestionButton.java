package net.orbitdev.orbitcommunity.minecraft.menus.suggestion;

import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.HeadsUtil;
import net.orbitdev.orbitcommunity.minecraft.utils.ItemBuilder;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import net.orbitdev.orbitcommunity.shared.suggestion.Suggestion;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SuggestionButton extends Button {

    private final Suggestion suggestion;

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        OrbitCommunity.get().getSuggestionsConfig().getStringList("SUGGESTIONS.LORE").forEach(text -> {
            if (text.contains("%head%")) {
                for (String line : HeadsUtil.getHead(suggestion.getPlayerDisplayName())) {
                    lore.add(line);
                }
                return;
            } else {
                lore.add(text.replace("%id%", String.valueOf(suggestion.getId())).replace("%player%", suggestion.getPlayerDisplayName()).replace("%status%", suggestion.getStatus().toString()).replace("%category%", suggestion.getCategory().toString()).replace("%suggestion%", suggestion.getSuggestion()));
            }
        });


        ItemBuilder material = new ItemBuilder(Material.STAINED_GLASS_PANE);
        material.name(OrbitCommunity.get().getSuggestionsConfig().getString("SUGGESTIONS.NAME").replace("%id%", String.valueOf(suggestion.getId())).replace("%player%", suggestion.getPlayerDisplayName()).replace("%status%", suggestion.getStatus().toString()).replace("%category%", suggestion.getCategory().toString()));
        material.lore(lore);
        return material.build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType type, int i) {
    }


    public SuggestionButton(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

}