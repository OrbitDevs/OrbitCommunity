package net.orbitdev.orbitcommunity.minecraft.menus.bug;

import com.google.common.collect.Maps;
import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.menus.editor.EditorMenu;
import net.orbitdev.orbitcommunity.minecraft.utils.CC;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.buttons.BackButton;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.pagination.PageButton;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.pagination.PaginatedMenu;
import net.orbitdev.orbitcommunity.shared.bug.Bug;
import net.orbitdev.orbitcommunity.shared.bug.BugManager;
import org.bukkit.entity.Player;

import java.util.Map;

public class BugMenu extends PaginatedMenu {
    private final BugManager bugManager;

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
        return CC.translate("&8Bugs");
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 9;
    }

    @Override
    public int getSize() {
        return 18;
    }

    public BugMenu() {
        this.bugManager = OrbitCommunity.get().getBugManager();
    }
    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        int i = 0;
        for (Bug bug : this.bugManager.getSuggestions()) {
            buttons.put(i, new BugButton(bug));
            ++i;
        }
        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
    }
}
