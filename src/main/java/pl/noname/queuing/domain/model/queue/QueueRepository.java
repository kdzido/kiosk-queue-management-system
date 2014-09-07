package pl.noname.queuing.domain.model.queue;

import java.util.List;

/**
 * The repository for the {@code Queue} root entity.
 *
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
public interface QueueRepository {

    /**
     * Lists all registered queues.
     *
     * @return
     */
    List<Queue> listAll();

    /**
     * Returns a queue of the given ID.
     *
     * @param queueId
     * @return the queue of the given ID
     */
    Queue find(QueueId queueId);

    /**
     * Persists the given {@code Queue} root entity.
     *
     * @param queue
     */
    void store(Queue queue);

    /**
     * Generates next unique queue business ID.
     * The generation can be delagated to a DB.
     *
     * @return unique queue ID
     */
    QueueId nextId();

}
