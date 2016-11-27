package pl.karol.administration.user.domain.model;

import lombok.AccessLevel;
import lombok.Setter;
import pl.karol.administration.user.domain.event.ChangePasswordEvent;
import pl.karol.administration.user.domain.event.NewUserEvent;
import pl.karol.common.application.event.publisher.DomainEventPublisher;
import pl.karol.common.domain.model.Entity;

@Setter(value = AccessLevel.PRIVATE)
public class User extends Entity {

    private String userId;
    private String encodedPassword;

    private User(String userId, String encodedPassword) {
        setUserId(userId);
        setEncodedPassword(encodedPassword);
        DomainEventPublisher
                .getInstance()
                .publish(NewUserEvent.create(userId));
    }

    public static User create(String userId, String encodedPassword) {
        return new User(userId, encodedPassword);
    }

    public boolean isPasswordEquals(String password) {
        return encodedPassword.equals(password);
    }

    public void changeEncodedPassword(String encodedPassword) {
        setEncodedPassword(encodedPassword);
        DomainEventPublisher
                .getInstance()
                .publish(ChangePasswordEvent.create(userId));
    }

    private void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}
