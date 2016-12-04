package pl.karol.dictionary.factory;

import lombok.AllArgsConstructor;
import pl.karol.common.application.IDGenerator;
import pl.karol.dictionary.domain.model.SkillDictionary;

@AllArgsConstructor
public class SkillDictionaryFactory {

    private final IDGenerator idGenerator;

    public SkillDictionary create(String code) {
        return new SkillDictionary(idGenerator.generate(), code);
    }
}
