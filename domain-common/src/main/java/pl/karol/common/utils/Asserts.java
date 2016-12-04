package pl.karol.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.karol.common.application.exception.AssertOfferRuntimeException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Asserts {

    public static void notNull(Object o) {
        notNull(o, "Object cant by NULL");
    }

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new AssertOfferRuntimeException(message);
        }
    }

    public static void notEmpty(String s) {
        notEmpty(s, "String can by empty");
    }

    public static void notEmpty(String s, String message) {
        notNull(s);
        if (s.trim().equals("")) {
            throw new AssertOfferRuntimeException(message);
        }
    }
}
