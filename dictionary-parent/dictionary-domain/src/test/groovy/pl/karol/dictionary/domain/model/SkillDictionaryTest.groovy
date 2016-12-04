package pl.karol.dictionary.domain.model

import pl.karol.common.application.IDGeneratorImpl
import pl.karol.common.application.exception.AssertOfferRuntimeException
import pl.karol.dictionary.factory.SkillDictionaryFactory

class SkillDictionaryTest extends spock.lang.Specification {
    def skillDictionaryFactory


    void setup() {
        def generatorId = new IDGeneratorImpl()
        skillDictionaryFactory = new SkillDictionaryFactory(generatorId);
    }

    def "create new Skill"() {
        when:
        def root = skillDictionaryFactory.create("root")

        then:
        root != null;
        root.code == "root"
        root.id != null
    }

    def "exception when new Skill hes null code"() {
        when:
        skillDictionaryFactory.create(null)

        then:
        thrown AssertOfferRuntimeException;
    }

    def "exception when new Skill hes empty code"() {
        when:
        skillDictionaryFactory.create("")

        then:
        thrown AssertOfferRuntimeException;
    }

    def "exception when new Skill hes withe space code"() {
        when:
        skillDictionaryFactory.create(" ")

        then:
        thrown AssertOfferRuntimeException;
    }

    def "compare two skill"() {
        when:
        def root = skillDictionaryFactory.create("root")
        def leaf = skillDictionaryFactory.create("leaf")

        then:
        root != leaf;
        root == root;
    }

    def "add collection items to dictionary"() {
        setup:
        def root = skillDictionaryFactory.create("root")
        def leafJava = skillDictionaryFactory.create("Java")
        def leafC = skillDictionaryFactory.create("c")

        def items = Arrays.asList(leafJava, leafC)

        when:
        root.addItems(items)

        then:
        root.isItemExist(leafJava);
        root.isItemExist(leafC);
    }

    def "exception when collection items is null"() {
        setup:
        def root = skillDictionaryFactory.create("root")

        when:
        root.addItems(null)

        then:
        thrown AssertOfferRuntimeException
    }


}
