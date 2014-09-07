package pl.noname.queuing.infrastructure.persistence

import pl.noname.queuing.domain.model.queue.QueueId
import spock.lang.Specification

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
class InMemoryQueueRepositoryImplTest extends Specification {

    InMemoryQueueRepositoryImpl repository = new InMemoryQueueRepositoryImpl()


    def "should generate subsequent letters"() {
        expect:
        repository.nextId() == new QueueId('A')
        repository.nextId() == new QueueId('B')
        repository.nextId() == new QueueId('C')
        repository.nextId() == new QueueId('D')
        repository.nextId() == new QueueId('E')
        repository.nextId() == new QueueId('F')
        repository.nextId() == new QueueId('A')
    }
}
