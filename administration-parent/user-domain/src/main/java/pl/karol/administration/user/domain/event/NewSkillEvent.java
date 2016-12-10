package pl.karol.administration.user.domain.event;


import lombok.RequiredArgsConstructor;
import pl.karol.common.domain.event.AbstractDomainEvent;

import java.util.Collection;

@RequiredArgsConstructor(staticName = "create")
public class NewSkillEvent extends AbstractDomainEvent {

    private final String userId;
    private final Collection<String> skills;
}
