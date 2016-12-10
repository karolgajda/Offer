package pl.karol.dictionary.domain.model;

import pl.karol.common.domain.model.Entity;
import pl.karol.common.utils.Asserts;

import java.util.Collection;
import java.util.HashSet;

public class SkillItem extends Entity {

    private String code;
    private Collection<SkillItem> items;

    public SkillItem(String id, String code) {
        super(id);
        setCode(code);
        this.items = new HashSet<>();
    }

    private void setCode(String code) {
        Asserts.notEmpty(code);
        this.code = code;
    }

    public void addItems(Collection<SkillItem> items) {
        Asserts.notNull(items);
        this.items.addAll(items);
    }

    public boolean isItemExist(SkillItem item) {
        return this.items.contains(item);
    }
}
