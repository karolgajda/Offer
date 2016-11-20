package pl.karol.common.domain.event;

import java.time.LocalDateTime;

public class AbstractDomainEvent implements DomainEvent {

    private final LocalDateTime createAt;

    protected AbstractDomainEvent() {
        createAt = LocalDateTime.now();
    }
}
