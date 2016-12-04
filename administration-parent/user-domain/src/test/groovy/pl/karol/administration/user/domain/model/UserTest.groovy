package pl.karol.administration.user.domain.model

import pl.karol.administration.user.application.event.listeners.ChangePasswordEventListener
import pl.karol.administration.user.application.event.listeners.NewSkillEventListener
import pl.karol.administration.user.application.event.listeners.NewUserEventListener
import pl.karol.administration.user.application.event.listeners.RemoveSkillEventListener
import pl.karol.administration.user.domain.event.ChangePasswordEvent
import pl.karol.administration.user.domain.event.NewSkillEvent
import pl.karol.administration.user.domain.event.NewUserEvent
import pl.karol.administration.user.domain.event.RemoveSkillEvent
import pl.karol.administration.user.factory.UserFactory
import pl.karol.common.application.IDGeneratorImpl
import pl.karol.common.application.event.publisher.DomainEventPublisher
import pl.karol.common.application.exception.AssertOfferRuntimeException
import spock.lang.Specification


class UserTest extends Specification {

    def idGenerator
    def userFactory
    def createListener
    def changeListener
    def password
    def newSkillListener
    def removeSkillListener

    def setup() {
        password = "password";

        createListener = Spy(NewUserEventListener)
        DomainEventPublisher.addSubscriber(createListener)

        changeListener = Spy(ChangePasswordEventListener)
        DomainEventPublisher.addSubscriber(changeListener)

        newSkillListener = Spy(NewSkillEventListener)
        DomainEventPublisher.addSubscriber(newSkillListener)

        removeSkillListener = Spy(RemoveSkillEventListener)
        DomainEventPublisher.addSubscriber(removeSkillListener)

        idGenerator = new IDGeneratorImpl()
        userFactory = new UserFactory(idGenerator)
    }

    def "create new User"() {
        when:
        def user = userFactory.create(password)

        then:
        user != null;
        user.encodedPassword == password
        user.id != null
    }

    def "exception when new User hes null password"() {
        when:
        userFactory.create(null)

        then:
        thrown AssertOfferRuntimeException;
    }

    def "exception when new User hes empty password"() {
        when:
        userFactory.create("")

        then:
        thrown AssertOfferRuntimeException;
    }

    def "exception when new User hes withe space passowrd"() {
        when:
        userFactory.create(" ")

        then:
        thrown AssertOfferRuntimeException;
    }

    def "dispatch new  newUserEvent after create new user"() {
        setup:
        NewUserEvent newUserEvent

        when:
        def user = userFactory.create(password)
        def userId = user.id

        then:
        user != null
        1 * createListener.handle(_) >> { arguments -> newUserEvent = arguments[0] }
        newUserEvent instanceof NewUserEvent
        newUserEvent.userId == userId
    }

    def "check password"() {
        when:
        def user = userFactory.create(password)

        then:
        user.isPasswordEquals(password)
        user.isPasswordEquals("fail") == false
    }

    def "change password"() {
        setup:
        def user = userFactory.create(password)
        def changePassword = "changePassword"

        when:
        user.changeEncodedPassword(changePassword)


        then:
        user.isPasswordEquals(changePassword)
        user.isPasswordEquals(password) == false
        user.isPasswordEquals("fail") == false
    }

    def "change password event"() {
        setup:
        ChangePasswordEvent changePasswordEvent
        def user = userFactory.create(password)
        def userId = user.id
        def changePassword = "changePassword"

        when:
        user.changeEncodedPassword(changePassword)


        then:
        1 * changeListener.handle(_) >> { arguments -> changePasswordEvent = arguments[0] }
        changePasswordEvent instanceof ChangePasswordEvent
        changePasswordEvent.userId == userId

    }

    def "add null to user skills"() {
        setup:
        def user = userFactory.create(password)

        when:
        user.addSkills(null)

        then:
        thrown AssertOfferRuntimeException
    }

    def "add skills to user"() {
        setup:
        def user = userFactory.create(password)
        def skills = Arrays.asList("java", "c");

        when:
        user.addSkills(skills)

        then:
        user.isUserGotSkill("java")
        user.isUserGotSkill("c")
    }

    def "check user not got skill"() {
        setup:
        def user = userFactory.create(password)
        def skills = Arrays.asList("java", "c");

        when:
        user.addSkills(skills)

        then:
        !user.isUserGotSkill("js")
    }

    def "check send event after new skills"() {
        setup:
        NewSkillEvent newSkillEvent
        def user = userFactory.create(password)
        def userId = user.id
        def skills = Arrays.asList("java", "c");

        when:
        user.addSkills(skills)

        then:
        1 * newSkillListener.handle(_) >> { arguments -> newSkillEvent = arguments[0] }
        newSkillEvent instanceof NewSkillEvent
        newSkillEvent.userId == userId
        newSkillEvent.skills == skills
    }

    def "remove null skill from user"() {
        setup:
        def user = userFactory.create(password)

        when:
        user.removeSkills(null)

        then:
        thrown AssertOfferRuntimeException
    }

    def "remove skills from user"() {
        setup:
        def user = userFactory.create(password)
        def skills = Arrays.asList("java", "c", "js", "html");
        def skillsToRemove = Arrays.asList("js", "html");
        user.addSkills(skills)

        when:
        user.removeSkills(skillsToRemove)

        then:
        user.isUserGotSkill("java")
        user.isUserGotSkill("c")
        !user.isUserGotSkill("js")
        !user.isUserGotSkill("html")
    }

    def "check send event after remove skills"() {
        setup:
        RemoveSkillEvent removeSkillEvent
        def user = userFactory.create(password)
        def userId = user.id
        def skills = Arrays.asList("java", "c", "js", "html");
        def skillsToRemove = Arrays.asList("js", "html");
        user.addSkills(skills)

        when:
        user.removeSkills(skillsToRemove)

        then:
        1 * removeSkillListener.handle(_) >> { arguments -> removeSkillEvent = arguments[0] }
        removeSkillEvent instanceof RemoveSkillEvent
        removeSkillEvent.userId == userId
        removeSkillEvent.skills == skillsToRemove
    }
}
