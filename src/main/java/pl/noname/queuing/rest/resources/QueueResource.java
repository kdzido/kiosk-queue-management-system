package pl.noname.queuing.rest.resources;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
public class QueueResource {

    private String queueId;
    private String category;
    private Integer awaitingNo;
    private String lastTicketId;
    private String consultantName;

    public QueueResource() {
    }

    public QueueResource(String queueId, String category, Integer awaitingNo, String lastTicketId, String consultantName) {
        this.queueId = queueId;
        this.category = category;
        this.awaitingNo = awaitingNo;
        this.lastTicketId = lastTicketId;
        this.consultantName = consultantName;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAwaitingNo() {
        return awaitingNo;
    }

    public void setAwaitingNo(Integer awaitingNo) {
        this.awaitingNo = awaitingNo;
    }

    public String getLastTicketId() {
        return lastTicketId;
    }

    public void setLastTicketId(String lastTicketId) {
        this.lastTicketId = lastTicketId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }
}
