package pl.karol.administration.user.domain.event;


import lombok.RequiredArgsConstructor;
import pl.karol.common.domain.event.AbstractDomainEvent;

@RequiredArgsConstructor(staticName = "create")
public class ChangePasswordEvent extends AbstractDomainEvent {

    private final String userId;
}
