package pl.karol.dictionary.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import pl.karol.dictionary.application.dto.message.CreateNewSkillItemDTOMessages;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewSkillItemDTO {

    private String parentId;
    @NotBlank(message = CreateNewSkillItemDTOMessages.CODE_NOT_BLANK)
    @Size(max = 100, message = CreateNewSkillItemDTOMessages.CODE_SIZE)
    private String code;

    public boolean isRoot() {
        return parentId == null;
    }

}
