package pl.karol.dictionary.application.dto.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateNewSkillItemDTOMessages {
    public static final String CODE_NOT_BLANK = "dto.validator.dictionary.CreateNewSkillItemDTO.code.notBlank";
    public static final String CODE_SIZE = "dto.validator.dictionary.CreateNewSkillItemDTO.code.size";
}
