package pl.karol.administration.user.domain.model

import pl.karol.administration.user.application.event.listeners.ChangePasswordEventListener
import pl.karol.administration.user.application.event.listeners.CreateUserEventListener
import pl.karol.administration.user.factory.UserFactory
import pl.karol.common.application.IDGeneratorImpl
import pl.karol.common.application.event.publisher.DomainEventPublisher
import spock.lang.Specification


class UserTest extends Specification {

    def idGenerator
    def userFactory
    def createListener
    def changeListener
    def password

    def setup() {
        password = "password";

        createListener = Spy(CreateUserEventListener)
        DomainEventPublisher.addSubscriber(createListener)

        changeListener = Spy(ChangePasswordEventListener)
        DomainEventPublisher.addSubscriber(changeListener)

        idGenerator = new IDGeneratorImpl()
        userFactory = new UserFactory(idGenerator)
    }

    def "CreateUser"() {
        when:
        def user = userFactory.create(password)

        then:
        user != null
        1 * createListener.handle(_)
    }

    def "IsPasswordEquals"() {
        when:
        def user = userFactory.create(password)

        then:
        user.isPasswordEquals(password)
        user.isPasswordEquals("fail") == false
    }

    def "ChangeEncodedPassword"() {
        setup:
        def user = userFactory.create(password)
        def changePassword = "changePassword"

        when:
        user.changeEncodedPassword(changePassword)


        then:
        user.isPasswordEquals(changePassword)
        user.isPasswordEquals(password) == false
        user.isPasswordEquals("fail") == false
        1 * changeListener.handle(_)
    }
}
