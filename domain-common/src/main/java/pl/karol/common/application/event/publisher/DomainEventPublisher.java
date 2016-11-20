package pl.karol.common.application.event.publisher;

import lombok.extern.slf4j.Slf4j;
import pl.karol.common.application.event.listener.DomainEventListener;
import pl.karol.common.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> INSTANCE = ThreadLocal.withInitial(DomainEventPublisher::new);
    private static Collection<DomainEventListener> subscribers = new ArrayList<>();

    public static DomainEventPublisher getInstance() {
        return INSTANCE.get();
    }

    public void publish(final DomainEvent event) {
        if (subscribers.isEmpty()) {
            throw new DomainEventPublisherException("Subscribers collection is empty");
        }

        List<DomainEventListener> listeners = subscribers
                .stream()
                .filter(s -> s.canHandle(event.getClass()))
                .collect(Collectors.toList());

        if (listeners.isEmpty()) {
            throw new DomainEventPublisherException("Not found subscriber for event: " + event.getClass());
        }

        listeners.forEach(s -> s.handle(event));


    }

    public static void addSubscriber(final DomainEventListener listener) {
        subscribers.add(listener);
    }

}
