package pl.noname.queuing.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import pl.noname.queuing.domain.model.queue.Queue;
import pl.noname.queuing.domain.model.queue.QueueId;
import pl.noname.queuing.domain.model.queue.QueueRepository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
@Repository
public class InMemoryQueueRepositoryImpl implements QueueRepository {

    private char[] letters = new char[] {'A', 'B', 'C', 'D', 'E', 'F'};

    private int pointer = 0;

    private Map<QueueId, Queue> queues = new HashMap<>();


    @PostConstruct
    public void initRepo() {
        queues.put(new QueueId("A"), new Queue(new QueueId("A"), "Individual clients"));
        queues.put(new QueueId("B"), new Queue(new QueueId("B"), "Business clients"));
        queues.put(new QueueId("C"), new Queue(new QueueId("C"), "Other issues"));
        queues.put(new QueueId("D"), new Queue(new QueueId("D"), "Disabled clients"));
    }


    @Override
    public List<Queue> listAll() {
        return Collections.unmodifiableList(new ArrayList<>(queues.values()));
    }

    @Override
    public Queue find(final QueueId queueId) {
        return this.queues.get(queueId);
    }

    @Override
    public void store(final Queue queue) {
        this.queues.put(queue.queueId(), queue);
    }

    @Override
    public QueueId nextId() {
        QueueId queueId = new QueueId(String.valueOf(letters[pointer]));
        if (pointer == letters.length - 1) {
            pointer = 0;
        } else {
            pointer++;
        }
        return queueId;
    }
}
