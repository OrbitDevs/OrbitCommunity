package net.orbitdev.orbitcommunity.shared.suggestion;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.orbitdev.orbitcommunity.shared.category.Category;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class SuggestionManager {
    private Map<UUID, Suggestion> suggestions;
    private Map<SuggestionStatus, Integer> countByStatus;
    public SuggestionManager() {
        this.suggestions = Maps.newHashMap();
        this.countByStatus = Maps.newHashMap();
    }

    public Map<SuggestionStatus, Integer> countSuggestionsByStatus() {
        for (SuggestionStatus status : SuggestionStatus.values()) {
            countByStatus.put(status, 0);
        }
        for (Suggestion suggestion : getSuggestions()) {
            countByStatus.put(suggestion.getStatus(), countByStatus.get(suggestion.getStatus()) + 1);
        }
        return countByStatus;
    }

    public void addSuggestion(Category category, String suggestion, String playerDisplayname) {
        Suggestion s = new Suggestion(category, suggestion, playerDisplayname);
        suggestions.put(s.getId(), s);
    }

    public Collection<Suggestion> getSuggestions() {
        return suggestions.values();
    }

}