package pl.karol.common.application.event.publisher;

import pl.karol.common.application.exception.OfferRuntimeException;

public class DomainEventPublisherException extends OfferRuntimeException {

    public DomainEventPublisherException() {
    }

    public DomainEventPublisherException(String message) {
        super(message);
    }
}
