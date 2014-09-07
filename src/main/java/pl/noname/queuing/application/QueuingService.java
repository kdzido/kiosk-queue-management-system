package pl.noname.queuing.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.noname.queuing.domain.model.ticket.TicketId;
import pl.noname.queuing.domain.model.queue.Queue;
import pl.noname.queuing.domain.model.queue.QueueId;
import pl.noname.queuing.domain.model.queue.QueueRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
@Service
public class QueuingService {

    private final QueueRepository queueRepository;

    @Autowired
    public QueuingService(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    // Commands

    @Transactional(readOnly = false)
    public TicketId requestTicket(final QueueId queueId) {
        Queue queue = queueRepository.find(queueId);
        final TicketId newId = queue.requestNewTicket();

        return new TicketId(newId);
    }

    // Queries

    @Transactional(readOnly = true)
    public TicketId queueHead(final QueueId queueId) {
        return null;
    }

    @Transactional(readOnly = true)
    public TicketId queueTail(final QueueId queueId) {
        return null;
    }


    @Transactional(readOnly = true)
    public List<Queue> listAllQueues() {
//        List<Queue> result = new ArrayList<>();
//        result.add(new Queue(new QueueId("A"), "Individual clients"));
//        result.add(new Queue(new QueueId("B"), "Business clients"));
//        result.add(new Queue(new QueueId("C"), "Other issues"));
//        result.add(new Queue(new QueueId("D"), "Disabled clients"));

        return queueRepository.listAll();
    }


}
