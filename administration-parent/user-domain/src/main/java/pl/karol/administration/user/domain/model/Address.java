package pl.karol.administration.user.domain.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.karol.common.domain.model.ValueObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address extends ValueObject {

    private String city;
    private String street;
    private String buildingNumber;
    private String flatNumber;

}
