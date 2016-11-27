package pl.karol.administration.user.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.karol.common.domain.model.Entity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Person extends Entity {

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
