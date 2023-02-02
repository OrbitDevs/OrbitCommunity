package net.orbitdev.orbitcommunity.minecraft.menus.suggestion;

import com.google.common.collect.Maps;
import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.menus.editor.EditorMenu;
import net.orbitdev.orbitcommunity.minecraft.utils.CC;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.buttons.BackButton;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.pagination.PageButton;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.pagination.PaginatedMenu;
import net.orbitdev.orbitcommunity.shared.suggestion.Suggestion;
import net.orbitdev.orbitcommunity.shared.suggestion.SuggestionManager;
import org.bukkit.entity.Player;

import java.util.Map;

public class SuggestionMenu extends PaginatedMenu {
    private final SuggestionManager suggestionManager;

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        buttons.put(9, new PageButton(-1, this));
        buttons.put(13, new BackButton(new EditorMenu()));
        buttons.put(17, new PageButton(1, this));
        return buttons;
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("&8Suggestions");
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 9;
    }

    @Override
    public int getSize() {
        return 18;
    }

    public SuggestionMenu() {
        this.suggestionManager = OrbitCommunity.get().getSuggestionManager();
    }
    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        int i = 0;
        for (Suggestion suggestion : this.suggestionManager.getSuggestions()) {
            buttons.put(i, new SuggestionButton(suggestion));
            ++i;
        }
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }
}
