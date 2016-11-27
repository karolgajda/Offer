package pl.karol.administration.user.application.event.listeners

import pl.karol.administration.user.domain.event.NewUserEvent
import pl.karol.common.domain.event.DomainEvent
import spock.lang.Specification

class NewUserEventListenerTest extends Specification {
    def listener

    def setup(){
        listener = new NewUserEventListener()
    }

    def "CanHandleCreateUserEvent"() {
        when:
        def canHandle = listener.canHandle(NewUserEvent.class)

        then:
        canHandle
    }

    def "CanHandleOtherEvent"() {
        when:
        def canHandle = listener.canHandle(DomainEvent.class)

        then:
        canHandle == false
    }
}
