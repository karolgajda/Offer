package pl.karol.administration.user.domain.event;


import pl.karol.common.domain.event.AbstractDomainEvent;

import java.util.Collection;

public class NewSkillEvent extends AbstractDomainEvent {

    private final String userId;
    private final Collection<String> skills;

    private NewSkillEvent(String userId, Collection<String> skills) {
        this.userId = userId;
        this.skills = skills;
    }

    public static NewSkillEvent create(String userId, Collection<String> skills) {
        return new NewSkillEvent(userId, skills);
    }
}
