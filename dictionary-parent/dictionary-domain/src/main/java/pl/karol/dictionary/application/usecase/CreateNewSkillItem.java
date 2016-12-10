package pl.karol.dictionary.application.usecase;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import pl.karol.common.application.Command;
import pl.karol.common.application.exception.NotFoundElementRuntimeException;
import pl.karol.common.application.validator.OfferValidator;
import pl.karol.dictionary.application.dto.CreateNewSkillItemDTO;
import pl.karol.dictionary.application.repository.port.SkillDictionaryRepository;
import pl.karol.dictionary.domain.model.SkillItem;
import pl.karol.dictionary.domain.model.factory.SkillDictionaryFactory;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class CreateNewSkillItem implements Command {

    @NonNull
    private final SkillDictionaryRepository dictionaryRepository;
    @NonNull
    private final SkillDictionaryFactory skillDictionaryFactory;
    @NonNull
    private final CreateNewSkillItemDTO createNewSkillItemDTO;


    @Override
    public void execute() {
        OfferValidator.valid(createNewSkillItemDTO);

        SkillItem skillItem = skillDictionaryFactory.create(createNewSkillItemDTO.getCode());
        if (!createNewSkillItemDTO.isRoot()) {
            String parentId = createNewSkillItemDTO.getParentId();
            Optional<SkillItem> optionalParent = dictionaryRepository.findById(parentId);
            SkillItem parent = optionalParent.orElseThrow(
                    () -> new NotFoundElementRuntimeException(SkillItem.class, parentId));
            parent.addItems(Arrays.asList(parent));
        } else {
            dictionaryRepository.save(skillItem);
        }
    }
}
