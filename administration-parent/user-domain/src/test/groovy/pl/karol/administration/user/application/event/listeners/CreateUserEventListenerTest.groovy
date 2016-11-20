package pl.karol.administration.user.application.event.listeners

import pl.karol.administration.user.domain.event.ChangePasswordEvent
import pl.karol.administration.user.domain.event.CreateUserEvent
import pl.karol.common.domain.event.DomainEvent
import spock.lang.Specification

class CreateUserEventListenerTest extends Specification {
    def listener

    def setup(){
        listener = new CreateUserEventListener()
    }

    def "CanHandleCreateUserEvent"() {
        when:
        def canHandle = listener.canHandle(CreateUserEvent.class)

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
