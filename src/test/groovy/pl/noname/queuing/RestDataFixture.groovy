package pl.noname.queuing

import pl.noname.queuing.rest.resources.QueueResource

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
class RestDataFixture {

    static List<QueueResource> allQueueResources() {
        [queueResourceA(), queueResourceB(), queueResourceC()]
    }

    static QueueResource queueResourceA() {
        new QueueResource("A", "Individual clients", 5, null, "Krzysztof Dzido")
    }

    static QueueResource queueResourceB() {
        new QueueResource("B", "Business clients", 12, "B12", "Ewa Kopacz")
    }

    static QueueResource queueResourceC() {
        new QueueResource("C", "Other issues", 3, "C03", "Anna Major")
    }
}
