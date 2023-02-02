package net.orbitdev.orbitcommunity.shared.bug;

import lombok.Getter;
import lombok.Setter;
import net.orbitdev.orbitcommunity.shared.category.Category;

import java.util.UUID;

@Getter
@Setter
public class Bug {
    private final UUID id;
    private BugStatus status;
    private Category category;
    private String playerDisplayName;
    private String suggestion;

    public Bug(Category category, String suggestion, String playerDisplayName) {
        this.id = UUID.randomUUID();
        this.category = category;
        this.suggestion = suggestion;
        this.playerDisplayName = playerDisplayName;
        this.status = BugStatus.PENDING;


    }

}