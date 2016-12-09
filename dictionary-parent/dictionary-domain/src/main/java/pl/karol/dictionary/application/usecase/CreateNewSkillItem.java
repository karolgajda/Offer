package pl.karol.dictionary.application.usecase;

import lombok.AllArgsConstructor;
import pl.karol.common.application.Command;
import pl.karol.common.application.exception.OfferRuntimeException;
import pl.karol.dictionary.application.dto.CreateNewSkillItemDTO;
import pl.karol.dictionary.application.repository.port.SkillDictionaryRepository;
import pl.karol.dictionary.domain.model.SkillItem;
import pl.karol.dictionary.domain.model.factory.SkillDictionaryFactory;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class CreateNewSkillItem implements Command {

    private final SkillDictionaryRepository dictionaryRepository;
    private final SkillDictionaryFactory skillDictionaryFactory;
    private final CreateNewSkillItemDTO dto;


    @Override
    public void execute() {
        SkillItem skillItem = skillDictionaryFactory.create(dto.getCode());
        if (!dto.isRoot()) {
            String parentId = dto.getParentId();
            Optional<SkillItem> optionalParent = dictionaryRepository.findById(parentId);
            SkillItem parent = optionalParent.orElseThrow(
                    () -> new OfferRuntimeException("Not found element for id: " + parentId));
            parent.addItems(Arrays.asList(parent));
        } else {
            dictionaryRepository.save(skillItem);
        }
    }
}
