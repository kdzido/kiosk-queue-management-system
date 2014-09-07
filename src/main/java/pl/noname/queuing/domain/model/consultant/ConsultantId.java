package pl.noname.queuing.domain.model.consultant;

import pl.noname.queuing.domain.sharedkernel.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
public final class ConsultantId implements ValueObject<ConsultantId> {

    private String id;

    public ConsultantId(final ConsultantId consultantId) {
        checkArgument(consultantId != null, "Consultant ID is required");
        this.id = consultantId.getId();
    }

    public ConsultantId(final String id) {
        checkArgument(id != null, "Consultant ID is required");
        this.id = id;
    }



    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean sameValueAs(final ConsultantId other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultantId consultantId = (ConsultantId) o;

        if (id != null ? !id.equals(consultantId.id) : consultantId.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
