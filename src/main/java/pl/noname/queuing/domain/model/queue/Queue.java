package pl.noname.queuing.domain.model.queue;

import pl.noname.queuing.domain.model.consultant.ConsultantId;
import pl.noname.queuing.domain.model.ticket.TicketId;
import pl.noname.queuing.rest.resources.QueueResource;

import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
public class Queue /*implements Entity<Queue, QueueId>*/ {

    private QueueId queueId;
    private String category;    // unique

    private ConsultantId consultantId;

    private LinkedList<TicketId> tickets = new LinkedList<TicketId>();

    private Integer lastTicketNo = 0;

    public Queue(final QueueId queueId, final String category) {
        checkArgument(queueId != null, "Queue ID is required");
        checkArgument(category != null, "Category is required");

        this.queueId = queueId;
        this.category = category;
    }

    public QueueId queueId() {
        return queueId;
    }


    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    public TicketId requestNewTicket() {
        TicketId newId = new TicketId(queueId.getId() + String.format("%02d", lastTicketNo++));
        tickets.add(newId);
        return newId;
    }

//    public void add(final TicketId ticketId) {
//        this.tickets.add(ticketId);
//    }

    public int awaitingNo() {
        return tickets.size();
    }

    public TicketId poll() {
        return tickets.poll();
    }

    public String category() {
        return category;
    }

    public TicketId head() {
        return tickets.size() > 0 ? tickets.getFirst() : null;
    }
    public TicketId tail() {
        return tickets.size() > 0 ? tickets.getLast() : null;
    }


    public boolean isConsultantAssigned() {
        return consultantId != null;
    }

    public ConsultantId consultantId() {
        return consultantId;
    }
}
