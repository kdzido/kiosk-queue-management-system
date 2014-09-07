package pl.noname.queuing.rest.resources.assembler;

import pl.noname.queuing.domain.model.consultant.Consultant;
import pl.noname.queuing.domain.model.queue.Queue;
import pl.noname.queuing.rest.resources.QueueResource;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
public class QueueResourceAssembler {

    public QueueResource from(final Queue queue, final Consultant consultant) {
        final QueueResource resource = new QueueResource();

        resource.setQueueId(queue.queueId().getId());
        resource.setCategory(queue.category());
        resource.setAwaitingNo(queue.awaitingNo());
        resource.setLastTicketId(queue.tail() != null ? queue.tail().getId() : null);
        resource.setConsultantName(consultant != null ? consultant.name() : null);

        return resource;
    }

}
