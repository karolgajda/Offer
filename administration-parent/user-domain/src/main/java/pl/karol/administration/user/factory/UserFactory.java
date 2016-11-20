package pl.karol.administration.user.factory;

import lombok.AllArgsConstructor;
import pl.karol.administration.user.domain.model.User;
import pl.karol.common.application.IDGenerator;

@AllArgsConstructor
public class UserFactory {

    private final IDGenerator generator;


    public User create(String password) {
        return new User(generator.generate(), password);
    }

}
