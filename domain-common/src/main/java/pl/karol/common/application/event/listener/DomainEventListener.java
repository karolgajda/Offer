package pl.karol.common.application.event.listener;


import pl.karol.common.domain.event.DomainEvent;

public interface DomainEventListener {


    boolean canHandle(Class<?> eventClass);

    void handle(DomainEvent event);


}
