package pl.karol.administration.user.domain.model;

import pl.karol.administration.user.domain.event.ChangePasswordEvent;
import pl.karol.administration.user.domain.event.CreateUserEvent;
import pl.karol.common.application.event.publisher.DomainEventPublisher;
import pl.karol.common.domain.model.Entity;


public class User extends Entity {

    private String userId;
    private String encodedPassword;

    public User(String userId, String encodedPassword) {
        this.userId = userId;
        this.encodedPassword = encodedPassword;
        DomainEventPublisher
                .getInstance()
                .publish(CreateUserEvent.create(userId));
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
