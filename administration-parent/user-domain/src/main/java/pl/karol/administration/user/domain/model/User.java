package pl.karol.administration.user.domain.model;

import lombok.AccessLevel;
import lombok.Setter;
import pl.karol.administration.user.domain.event.ChangePasswordEvent;
import pl.karol.administration.user.domain.event.NewSkillEvent;
import pl.karol.administration.user.domain.event.NewUserEvent;
import pl.karol.administration.user.domain.event.RemoveSkillEvent;
import pl.karol.common.application.event.publisher.DomainEventPublisher;
import pl.karol.common.domain.model.Entity;
import pl.karol.common.utils.Asserts;

import java.util.Collection;
import java.util.HashSet;

@Setter(value = AccessLevel.PRIVATE)
public class User extends Entity {

    private String encodedPassword;
    private Collection<String> skills;

    private User(String userId, String encodedPassword) {
        super(userId);
        skills = new HashSet<>();
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
                .publish(ChangePasswordEvent.create(getId()));
    }

    private void setEncodedPassword(String encodedPassword) {
        Asserts.notEmpty(encodedPassword);
        this.encodedPassword = encodedPassword;
    }

    public void addSkills(Collection<String> skills) {
        Asserts.notNull(skills);
        this.skills.addAll(skills);
        DomainEventPublisher
                .getInstance()
                .publish(NewSkillEvent.create(getId(), skills));
    }

    public void removeSkills(Collection<String> skills) {
        Asserts.notNull(skills);
        this.skills.removeAll(skills);
        DomainEventPublisher
                .getInstance()
                .publish(RemoveSkillEvent.create(getId(), skills));
    }

    public void isUserGotSkill(String skill) {
        this.skills.contains(skill);
    }
}
