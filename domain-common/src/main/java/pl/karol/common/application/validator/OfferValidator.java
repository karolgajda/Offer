package pl.karol.common.application.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.karol.common.application.exception.ValidatorRuntimeException;

import javax.validation.ConstraintViolation;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfferValidator {

    public static void valid(Object o) {
        Set<ConstraintViolation<Object>> validate = ValidatorInstance.getInstance().validate(o);
        if (!validate.isEmpty()) {
            Map<String, Map<String, Collection<String>>> errorsMap = convertToErrorMap(validate);

            throw new ValidatorRuntimeException(errorsMap);
        }
    }

    private static Map<String, Map<String, Collection<String>>> convertToErrorMap(Set<ConstraintViolation<Object>> validate) {
        Map<String, Map<String, Collection<String>>> errorsMap = new HashMap<>();
        validate.forEach(v -> {
            String rootBeanClass = v.getRootBeanClass().getSimpleName();
            String field = v.getPropertyPath().toString();
            String message = v.getMessage();
            if (errorsMap.containsKey(rootBeanClass)) {
                Map<String, Collection<String>> errorsMapOnClass = errorsMap.get(rootBeanClass);
                if (errorsMapOnClass.containsKey(field)) {
                    errorsMapOnClass.get(field).add(message);
                } else {
                    Collection<String> errors = new HashSet<>();
                    errors.add(message);
                    errorsMapOnClass.put(field, errors);
                }
            } else {
                Map<String, Collection<String>> errorsMapOnClass = new HashMap<>();
                Collection<String> errors = new HashSet<>();
                errors.add(message);
                errorsMapOnClass.put(field, errors);
                errorsMap.put(rootBeanClass, errorsMapOnClass);
            }
        });
        return errorsMap;
    }
}
