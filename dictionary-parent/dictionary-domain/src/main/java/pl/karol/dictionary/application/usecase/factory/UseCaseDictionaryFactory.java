package pl.karol.dictionary.application.usecase.factory;

import lombok.AllArgsConstructor;
import pl.karol.dictionary.application.dto.CreateNewSkillItemDTO;
import pl.karol.dictionary.application.repository.port.SkillDictionaryRepository;
import pl.karol.dictionary.application.usecase.CreateNewSkillItem;
import pl.karol.dictionary.domain.model.factory.SkillDictionaryFactory;

@AllArgsConstructor
public class UseCaseDictionaryFactory {

    private final SkillDictionaryRepository dictionaryRepository;
    private final SkillDictionaryFactory skillDictionaryFactory;

    public CreateNewSkillItem createNewSkill(CreateNewSkillItemDTO dto){
        return new CreateNewSkillItem(dictionaryRepository,skillDictionaryFactory,dto);
    }
}
