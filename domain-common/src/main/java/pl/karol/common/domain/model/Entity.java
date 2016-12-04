package pl.karol.common.domain.model;

import pl.karol.common.utils.Asserts;

public abstract class Entity extends IdentifiedDomainObject {

    protected String id;

    public Entity(String id) {
        setId(id);
    }

    private void setId(String id) {
        Asserts.notEmpty(id);
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() == obj.getClass()) {
            return false;
        }
        Entity e = (Entity) obj;
        return id.equals(e.id);
    }
}
