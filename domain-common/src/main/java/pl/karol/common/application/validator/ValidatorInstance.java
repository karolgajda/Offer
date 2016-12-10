package pl.karol.common.application.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidatorInstance {

    public static final Validator getInstance() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
