package pl.karol.administration.user.domain.model;

import lombok.AllArgsConstructor;
import pl.karol.common.domain.model.Entity;

@AllArgsConstructor
public class Person extends Entity {

    private String firstName;
    private String lastName;
}
