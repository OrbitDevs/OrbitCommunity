package net.orbitdev.orbitcommunity.shared.suggestion;

import lombok.Getter;
import lombok.Setter;
import net.orbitdev.orbitcommunity.shared.category.Category;

import java.util.UUID;

@Getter
@Setter
public class Suggestion {
    private final UUID id;
    private SuggestionStatus status;
    private Category category;
    private String playerDisplayName;
    private String suggestion;

    public Suggestion(Category category, String suggestion, String playerDisplayName) {
        this.id = UUID.randomUUID();
        this.category = category;
        this.suggestion = suggestion;
        this.playerDisplayName = playerDisplayName;
        this.status = SuggestionStatus.PENDING;


    }

}