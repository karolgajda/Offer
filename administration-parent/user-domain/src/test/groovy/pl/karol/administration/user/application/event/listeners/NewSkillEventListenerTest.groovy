package pl.karol.administration.user.application.event.listeners

import pl.karol.administration.user.domain.event.ChangePasswordEvent
import pl.karol.administration.user.domain.event.NewSkillEvent
import pl.karol.common.domain.event.DomainEvent
import spock.lang.Specification

class NewSkillEventListenerTest extends Specification {

    def listener

    def setup(){
        listener = new NewSkillEventListener()
    }

    def "CanHandleChangePasswordEvent"() {
        when:
        def canHandle = listener.canHandle(NewSkillEvent.class)

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
