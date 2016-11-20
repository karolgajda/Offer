package pl.karol.common.application.event.publisher

import pl.karol.common.application.event.listener.DomainEventListener
import pl.karol.common.domain.event.DomainEvent

class DomainEventPublisherTest extends spock.lang.Specification {

    def cleanup() {
        DomainEventPublisher.subscribers = new ArrayList<>()
    }

    def "SetUpDomainPublisher"() {
        when:
        def instance = DomainEventPublisher.getInstance();

        then:
        instance != null
        DomainEventPublisher.subscribers.size() == 0
    }

    def "TryPublishEventWithEmptySubscriber"() {
        setup:
        def instance = DomainEventPublisher.getInstance();
        def fEvent = new FirstEvent()

        when:
        instance.publish(fEvent)

        then:
        thrown(DomainEventPublisherException)
    }

    def "AddSubscriber"() {
        when:
        DomainEventPublisher.addSubscriber(new FirstListener())

        then:
        DomainEventPublisher.subscribers.size() == 1
    }

    def "Publish"() {
        setup:
        def fListener = Spy(FirstListener)
        def sListener = Spy(SecondListener)

        def fEvent = new FirstEvent()
        def sEvent = new SecondEvent()

        DomainEventPublisher.addSubscriber(fListener)
        DomainEventPublisher.addSubscriber(sListener)

        def instance = DomainEventPublisher.getInstance();


        when:
        instance.publish(fEvent)
        instance.publish(fEvent)
        instance.publish(sEvent)

        then:
        2 * fListener.handle(_)
        1 * sListener.handle(_)

    }

    def "PublishEventWithNotAddSubscriber"() {
        setup:
        def fListener = Spy(FirstListener)

        def sEvent = new SecondEvent()

        DomainEventPublisher.addSubscriber(fListener)

        def instance = DomainEventPublisher.getInstance();


        when:
        instance.publish(sEvent)

        then:
        thrown(DomainEventPublisherException)

    }

    class FirstEvent implements DomainEvent {

    }

    class FirstListener implements DomainEventListener {

        @Override
        boolean canHandle(Class<?> eventClass) {
            return eventClass == FirstEvent.class
        }

        @Override
        void handle(DomainEvent event) {

        }
    }


    class SecondEvent implements DomainEvent {

    }


    class SecondListener implements DomainEventListener {

        @Override
        boolean canHandle(Class<?> eventClass) {
            return eventClass == SecondEvent.class
        }

        @Override
        void handle(DomainEvent event) {

        }
    }


}
