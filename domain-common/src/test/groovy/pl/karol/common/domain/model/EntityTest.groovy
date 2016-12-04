package pl.karol.common.domain.model

import pl.karol.common.application.IDGeneratorImpl
import pl.karol.common.application.exception.AssertOfferRuntimeException

class EntityTest extends spock.lang.Specification {

    def "create entity with null id"() {
        when:
        new TestEntity(null)

        then:
        thrown AssertOfferRuntimeException;
    }

    def "exception when create entity with empty id"() {
        when:
        new TestEntity("")

        then:
        thrown AssertOfferRuntimeException;
    }

    def "exception when create entity with withe space id"() {
        when:
        new TestEntity(" ")

        then:
        thrown AssertOfferRuntimeException;
    }

    def "compare entity the same"() {
        setup:
        def factory = new TestEntityFactory();
        when:
        def instance = factory.create();

        then:
        instance == instance;
    }

    def "compare different entity"() {
        setup:
        def factory = new TestEntityFactory();
        when:
        def instance1 = factory.create();
        def instance2 = factory.create();

        then:
        instance1 != instance2;
    }

    class TestEntity extends Entity {

        TestEntity(String id) {
            super(id)
        }
    }

    class TestEntityFactory{
        def idGenerator = new IDGeneratorImpl();

        def create(){
            return new TestEntity(idGenerator.generate());
        }
    }

}
