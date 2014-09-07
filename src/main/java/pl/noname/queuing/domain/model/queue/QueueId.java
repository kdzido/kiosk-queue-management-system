package pl.noname.queuing.domain.model.queue;

import pl.noname.queuing.domain.sharedkernel.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
public final class QueueId implements ValueObject<QueueId> {

    private String id;

    public QueueId(final QueueId queueId) {
        checkArgument(id != null, "Queue ID is required");
        this.id = queueId.getId();
    }

    public QueueId(final String id) {
        checkArgument(id != null, "Queue ID is required");
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
    public boolean sameValueAs(final QueueId other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueueId queueId = (QueueId) o;

        if (id != null ? !id.equals(queueId.id) : queueId.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
