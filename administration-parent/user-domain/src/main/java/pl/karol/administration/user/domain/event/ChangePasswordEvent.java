package pl.karol.administration.user.domain.event;


import pl.karol.common.domain.event.AbstractDomainEvent;

public class ChangePasswordEvent extends AbstractDomainEvent {

    private final String userId;


    public ChangePasswordEvent(String userId) {
        super();
        this.userId = userId;
    }

    public static ChangePasswordEvent create(String userId) {
        return new ChangePasswordEvent(userId);
    }
}
