package pl.karol.dictionary.application.usecase

import pl.karol.common.application.IDGeneratorImpl
import pl.karol.dictionary.application.dto.CreateNewSkillItemDTO
import pl.karol.dictionary.application.repository.port.SkillDictionaryRepository
import pl.karol.dictionary.application.usecase.factory.UseCaseDictionaryFactory
import pl.karol.dictionary.domain.model.SkillItem
import pl.karol.dictionary.domain.model.factory.SkillDictionaryFactory
import spock.lang.Specification


class CreateNewSkillItemTest extends Specification {
    def dictionaryRepository
    def skillDictionaryFactory
    def useCaseDictionaryFactory

    void setup() {
        dictionaryRepository = Mock(SkillDictionaryRepository)
        skillDictionaryFactory = new SkillDictionaryFactory(new IDGeneratorImpl())
        useCaseDictionaryFactory = new UseCaseDictionaryFactory(dictionaryRepository, skillDictionaryFactory)


    }

    def "create new root item"() {
        setup:
        def skillItem
        def createNewSkillItemDTO = CreateNewSkillItemDTO.builder()
                .code("root")
                .build();
        def createNewSkill = useCaseDictionaryFactory.createNewSkill(createNewSkillItemDTO);

        when:
        createNewSkill.execute()

        then:
        1 * dictionaryRepository.save(_) >> { arguments -> skillItem = arguments[0] }
        skillItem.code == "root"
        skillItem.id != null
    }

    def "create new item as child"() {
        setup:
        def skillItem
        def createNewSkillItemDTO = CreateNewSkillItemDTO.builder()
                .code("child")
                .parentId("1")
                .build();
        def createNewSkill = useCaseDictionaryFactory.createNewSkill(createNewSkillItemDTO);

        def parent = new SkillItem("1", "root")
        dictionaryRepository.findById(_) >> Optional.of(parent)

        when:
        createNewSkill.execute()

        then:
        parent.items.size() == 1
    }
}
