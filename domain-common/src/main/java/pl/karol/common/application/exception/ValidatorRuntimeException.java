package pl.karol.common.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class ValidatorRuntimeException extends OfferRuntimeException {
    private final Map<String, Map<String, Collection<String>>> inValidObjects;
}
