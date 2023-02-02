package net.orbitdev.orbitcommunity.minecraft.menus.editor;

import com.google.common.collect.Maps;
import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.CC;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Button;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.Map;

public class EditorMenu extends Menu {
    @Override
    public String getTitle(Player player) {
        return CC.translate(OrbitCommunity.get().getEditorMenuConfig().getString("TITLE"));
    }

    @Override
    public int getSize() {
        return OrbitCommunity.get().getEditorMenuConfig().getInt("SIZE");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        for (Editor editor : OrbitCommunity.get().getEditorManager().getItems().values()) {
                buttons.put(editor.getSlot(), new EditorButton(editor));
                setPlaceholder(OrbitCommunity.get().getEditorMenuConfig().getBoolean("FILLER"));
        }
        return buttons;
    }
}
