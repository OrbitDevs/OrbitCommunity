package net.orbitdev.orbitcommunity;

import lombok.Getter;
import lombok.Setter;
import net.orbitdev.orbitcommunity.minecraft.commands.bug.BugCommand;
import net.orbitdev.orbitcommunity.minecraft.commands.editor.EditorCommand;
import net.orbitdev.orbitcommunity.minecraft.commands.suggestion.SuggestionCommand;
import net.orbitdev.orbitcommunity.minecraft.menus.editor.EditorManager;
import net.orbitdev.orbitcommunity.minecraft.utils.command.CommandManager;
import net.orbitdev.orbitcommunity.minecraft.utils.file.FileConfig;
import net.orbitdev.orbitcommunity.minecraft.utils.menu.ButtonListener;
import net.orbitdev.orbitcommunity.shared.bug.BugManager;
import net.orbitdev.orbitcommunity.shared.suggestion.SuggestionManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
public final class OrbitCommunity extends JavaPlugin {

    @Getter
    public static OrbitCommunity instance;

    private EditorManager editorManager;
    private SuggestionManager suggestionManager;
    private BugManager bugManager;

    private FileConfig editorMenuConfig, settingsConfig, suggestionsConfig, bugsConfig;

    @Override
    public void onEnable() {
        loadConfigs();
        this.suggestionManager = new SuggestionManager();
        this.bugManager = new BugManager();
        registerCommands();
        registerListeners();
        this.editorManager = new EditorManager();
        this.editorManager.load();

    }

    @Override
    public void onDisable() {
    }

    private void loadConfigs() {
        this.editorMenuConfig = new FileConfig(this, "editor.yml");
        this.settingsConfig = new FileConfig(this, "settings.yml");
        this.suggestionsConfig = new FileConfig(this, "suggestions.yml");
        this.bugsConfig = new FileConfig(this, "bugs.yml");
    }
    private void registerCommands() {
        new CommandManager(this, Collections.emptyList());
        new EditorCommand();
        new SuggestionCommand();
        new BugCommand();
    }
    private void registerListeners() {
        Arrays.asList(
                new ButtonListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }


    public static OrbitCommunity get() {
        return getPlugin(OrbitCommunity.class);
    }
}
