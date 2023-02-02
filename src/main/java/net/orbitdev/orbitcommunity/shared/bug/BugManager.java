package net.orbitdev.orbitcommunity.shared.bug;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.orbitdev.orbitcommunity.shared.category.Category;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class BugManager {
    private Map<UUID, Bug> bugs;
    private Map<BugStatus, Integer> countByStatus;
    public BugManager() {
        this.bugs = Maps.newHashMap();
        this.countByStatus = Maps.newHashMap();
    }

    public Map<BugStatus, Integer> countBugsByStatus() {
        for (BugStatus status : BugStatus.values()) {
            countByStatus.put(status, 0);
        }
        for (Bug bug : getSuggestions()) {
            countByStatus.put(bug.getStatus(), countByStatus.get(bug.getStatus()) + 1);
        }
        return countByStatus;
    }

    public void addSuggestion(Category category, String suggestion, String playerDisplayname) {
        Bug s = new Bug(category, suggestion, playerDisplayname);
        bugs.put(s.getId(), s);
    }

    public Collection<Bug> getSuggestions() {
        return bugs.values();
    }

}