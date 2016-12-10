package pl.karol.dictionary.domain.model.factory;

import lombok.AllArgsConstructor;
import pl.karol.common.application.IDGenerator;
import pl.karol.dictionary.domain.model.SkillItem;

@AllArgsConstructor
public final class SkillDictionaryFactory {

    private final IDGenerator idGenerator;

    public SkillItem create(String code) {
        return new SkillItem(idGenerator.generate(), code);
    }
}
