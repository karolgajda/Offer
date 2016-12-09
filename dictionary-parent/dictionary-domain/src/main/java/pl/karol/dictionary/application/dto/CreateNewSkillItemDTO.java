package pl.karol.dictionary.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewSkillItemDTO {

    private String parentId;
    private String code;

    public boolean isRoot() {
        return parentId == null;
    }

}
