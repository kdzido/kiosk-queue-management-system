package pl.noname.queuing.rest.resources;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
public class TicketResource {

    private String queueId;
    private String ticketId;

    public TicketResource() {
    }

    public TicketResource(String queueId, String ticketId) {
        this.queueId = queueId;
        this.ticketId = ticketId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
