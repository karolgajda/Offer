package pl.karol.administration.user.domain.event;


import pl.karol.common.domain.event.AbstractDomainEvent;

import java.util.Collection;

public class RemoveSkillEvent extends AbstractDomainEvent {

    private final String userId;
    private final Collection<String> skills;

    private RemoveSkillEvent(String userId, Collection<String> skills) {
        this.userId = userId;
        this.skills = skills;
    }

    public static RemoveSkillEvent create(String userId, Collection<String> skills) {
        return new RemoveSkillEvent(userId, skills);
    }
}
