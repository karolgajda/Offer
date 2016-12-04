package pl.karol.dictionary.domain.model;

import pl.karol.common.domain.model.Entity;
import pl.karol.common.utils.Asserts;

import java.util.Collection;
import java.util.HashSet;

public class SkillDictionary extends Entity {

    private String code;
    private Collection<SkillDictionary> items;

    public SkillDictionary(String id, String code) {
        super(id);
        setCode(code);
        this.items = new HashSet<>();
    }

    private void setCode(String code) {
        Asserts.notEmpty(code);
        this.code = code;
    }

    public void addItems(Collection<SkillDictionary> items) {
        Asserts.notNull(items);
        this.items.addAll(items);
    }

    public boolean isItemExist(SkillDictionary item) {
        return this.items.contains(item);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return super.equals(obj);
    }
}
