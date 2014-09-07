package pl.noname.queuing

import pl.noname.queuing.domain.model.consultant.Consultant
import pl.noname.queuing.domain.model.consultant.ConsultantId
import pl.noname.queuing.domain.model.queue.*

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
class DomainDataFixture {

    static List<Queue> allQueues() {
        [queueA(), queueB(), queueC()]
    }

    static Queue queueA() {
        new Queue(new QueueId("A"), "Individual clients")
    }

    static Queue queueB() {
        new Queue(new QueueId("B"), "Business clients")
    }

    static Queue queueC() {
        new Queue(new QueueId("C"), "Other issues")
    }

    static Consultant consultant1() {
        new Consultant(new ConsultantId("kdzido"), "Krzysztof Dzido")
    }
}
