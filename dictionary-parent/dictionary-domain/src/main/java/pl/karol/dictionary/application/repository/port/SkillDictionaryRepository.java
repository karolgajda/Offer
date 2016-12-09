package pl.karol.dictionary.application.repository.port;

import pl.karol.dictionary.domain.model.SkillItem;

import java.util.Optional;

public interface SkillDictionaryRepository {

    void save(SkillItem skillItem);
    Optional<SkillItem> findById(String id);
}
