package pl.karol.administration.user.domain.event;


import pl.karol.common.domain.event.AbstractDomainEvent;

public class NewUserEvent extends AbstractDomainEvent {

    private final String userId;

    private NewUserEvent(String userId) {
        this.userId = userId;
    }

    public static NewUserEvent create(String userId) {
        return new NewUserEvent(userId);
    }
}
