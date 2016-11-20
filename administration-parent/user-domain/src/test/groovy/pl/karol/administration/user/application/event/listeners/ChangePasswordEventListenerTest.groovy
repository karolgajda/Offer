package pl.karol.administration.user.application.event.listeners

import pl.karol.administration.user.domain.event.ChangePasswordEvent
import pl.karol.common.domain.event.DomainEvent
import spock.lang.Specification

class ChangePasswordEventListenerTest extends Specification {

    def listener

    def setup(){
        listener = new ChangePasswordEventListener()
    }

    def "CanHandleChangePasswordEvent"() {
        when:
        def canHandle = listener.canHandle(ChangePasswordEvent.class)

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
