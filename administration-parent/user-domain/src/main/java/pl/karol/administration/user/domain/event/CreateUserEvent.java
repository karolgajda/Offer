package pl.karol.administration.user.domain.event;


import pl.karol.common.domain.event.AbstractDomainEvent;

public class CreateUserEvent extends AbstractDomainEvent {

    private final String userId;


    public CreateUserEvent(String userId) {
        super();
        this.userId = userId;
    }

    public static CreateUserEvent create(String userId) {
        return new CreateUserEvent(userId);
    }
}
