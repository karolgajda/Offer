package pl.karol.administration.user.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
