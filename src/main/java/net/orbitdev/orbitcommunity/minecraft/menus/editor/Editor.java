package net.orbitdev.orbitcommunity.minecraft.menus.editor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.orbitdev.orbitcommunity.OrbitCommunity;
import net.orbitdev.orbitcommunity.minecraft.utils.ItemBuilder;
import net.orbitdev.orbitcommunity.shared.bug.BugManager;
import net.orbitdev.orbitcommunity.shared.bug.BugStatus;
import net.orbitdev.orbitcommunity.shared.suggestion.SuggestionManager;
import net.orbitdev.orbitcommunity.shared.suggestion.SuggestionStatus;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Editor {

    private final SuggestionManager suggestionsManager;
    private final BugManager bugManager;

    private List<String> lore;
    private String displayName;
    private String material;
    private int data;
    @Setter(value = AccessLevel.NONE)
    private final String name;
    private boolean glow;
    private int slot;

    public ItemStack getIcon(Player player) {
        List<String> lore = new ArrayList<>();
        this.lore.forEach(text -> {
            lore.add(text.replace("%suggestions_pending%", String.valueOf(suggestionsManager.countSuggestionsByStatus().get(SuggestionStatus.PENDING))).replace("%suggestions_approved%", String.valueOf(suggestionsManager.countSuggestionsByStatus().get(SuggestionStatus.APPROVED))).replace("%suggestions_denied%", String.valueOf(suggestionsManager.countSuggestionsByStatus().get(SuggestionStatus.DENIED))).replace("%bugs_fixed%", String.valueOf(bugManager.countBugsByStatus().get(BugStatus.FIXED))).replace("%bugs_revising%", String.valueOf(bugManager.countBugsByStatus().get(BugStatus.REVISING))).replace("%bugs_pending%", String.valueOf(bugManager.countBugsByStatus().get(BugStatus.PENDING))));
        });
        return new ItemBuilder(this.material).data(this.data).name(this.displayName).lore(lore).glow(this.glow).build();
    }

    public Editor(String name) {
        this.name = name;
        this.suggestionsManager = OrbitCommunity.get().getSuggestionManager();
        this.bugManager = OrbitCommunity.get().getBugManager();
    }
}
