package pl.karol.administration.user.application.event.listeners

import pl.karol.administration.user.domain.event.RemoveSkillEvent
import pl.karol.common.domain.event.DomainEvent
import spock.lang.Specification

class RemoveSkillEventListenerTest extends Specification {

    def listener

    def setup() {
        listener = new RemoveSkillEventListener()
    }

    def "CanHandleChangePasswordEvent"() {
        when:
        def canHandle = listener.canHandle(RemoveSkillEvent.class)

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
