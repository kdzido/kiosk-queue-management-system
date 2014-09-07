package pl.noname.queuing.domain.model.ticket;

import pl.noname.queuing.domain.sharedkernel.ValueObject;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
public final class TicketId implements ValueObject<TicketId> {

    private String id;

    public TicketId(final TicketId ticketId) {
        checkArgument(ticketId != null, "Ticket ID is required");
        this.id = ticketId.getId();
    }

    public TicketId(final String id) {
        checkArgument(id != null, "Ticket ID is required");
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
    public boolean sameValueAs(final TicketId other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketId ticketId = (TicketId) o;

        if (id != null ? !id.equals(ticketId.id) : ticketId.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
