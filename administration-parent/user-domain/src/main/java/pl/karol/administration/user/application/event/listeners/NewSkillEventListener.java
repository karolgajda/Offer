package pl.karol.administration.user.application.event.listeners;


import pl.karol.administration.user.domain.event.NewSkillEvent;
import pl.karol.administration.user.domain.event.NewUserEvent;
import pl.karol.common.application.event.listener.DomainEventListener;
import pl.karol.common.domain.event.DomainEvent;

public class NewSkillEventListener implements DomainEventListener {

    @Override
    public boolean canHandle(Class<?> eventClass) {
        return eventClass == NewSkillEvent.class;
    }

    @Override
    public void handle(DomainEvent event) {
    }
}
