package pl.karol.administration.user.application.event.listeners;


import pl.karol.administration.user.domain.event.ChangePasswordEvent;
import pl.karol.common.application.event.listener.DomainEventListener;
import pl.karol.common.domain.event.DomainEvent;

public class ChangePasswordEventListener implements DomainEventListener {

    @Override
    public boolean canHandle(Class<?> eventClass) {
        return eventClass == ChangePasswordEvent.class;
    }

    @Override
    public void handle(DomainEvent event) {

    }
}
