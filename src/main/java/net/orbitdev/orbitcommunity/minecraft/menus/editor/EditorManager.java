package net.orbitdev.orbitcommunity.minecraft.menus.editor;

import com.google.common.collect.Maps;
import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.file.FileConfig;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

public class EditorManager {
    private final Map<String, Editor> items;

    public EditorManager() {
        this.items = Maps.newHashMap();
    }

    public Map<String, Editor> getItems() {
        return this.items;
    }

    public void load() {
        FileConfig cosmeticConfig = OrbitCommunity.get().getEditorMenuConfig();
        this.items.clear();
        ConfigurationSection section = cosmeticConfig.getConfiguration().getConfigurationSection("ITEMS");
        if (section == null) {
            return;
        }
        for (String s : section.getKeys(false)) {
            Editor editor = new Editor(s);
            editor.setDisplayName(section.getString(s + ".NAME"));
            editor.setLore(section.getStringList(s + ".LORE"));
            editor.setMaterial(section.getString(s + ".MATERIAL"));
            editor.setData(section.getInt(s + ".DATA"));
            editor.setSlot(section.getInt(s + ".SLOT"));
            editor.setGlow(section.getBoolean(s + ".GLOW"));
            this.items.put(s, editor);
        }
    }
}
