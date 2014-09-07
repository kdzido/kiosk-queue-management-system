package pl.noname.queuing.domain.model.queue

import pl.noname.queuing.domain.model.ticket.TicketId
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
class QueueTest extends Specification {

    final QUEUE_ID = new QueueId('A')
    final QUEUE_CATEGORY = "Individual clients"

    def setup() {
    }


    def "should not construct in invalid state"() {
        when:
        new Queue(null, null)
        then:
        thrown(IllegalArgumentException)
    }

    def "should create an empty queue without assigned consultant"() {
        when:
        def queue = new Queue(QUEUE_ID, QUEUE_CATEGORY)
        then:
        queue.isEmpty() == true
        and:
        queue.isConsultantAssigned() == false
    }


    def "should have null head and tail when queue is empty"() {
        given:
        def queue = new Queue(QUEUE_ID, QUEUE_CATEGORY)

        expect:
        queue.isEmpty() == true
        queue.head() == null
        queue.tail() == null
    }

    def "should create subsequent ticket numbers"() {
        given:
        def queue = new Queue(QUEUE_ID, QUEUE_CATEGORY)

        expect:
        queue.requestNewTicket() == new TicketId("A00")
        queue.requestNewTicket() == new TicketId("A01")
        queue.requestNewTicket() == new TicketId("A02")
    }

    @Ignore
    def "should reject creating new ticket beyond the queue capacity"() {
        // TODO implemnet
    }

    @Ignore
    def "should generated ticket number incrementally"() {
        // TODO implemnet
    }

    def "should create and poll the ticket"() {
        given:
        def queue = new Queue(QUEUE_ID, QUEUE_CATEGORY)

        when:
        def ticketId = queue.requestNewTicket()
        then:
        queue.isEmpty() == false

        when:
        def current = queue.poll()
        then:
        current == ticketId
        and:
        queue.isEmpty()
    }

    @Ignore
    def "should poll a ticket from the queue"() {
        // TODO implemnet
    }

    @Ignore
    def "should allow to add ticket with the same ID"() {
        // TODO implemnet
    }

    def "should know awaiting ticket number"() {
        given:
        def queue = new Queue(QUEUE_ID, QUEUE_CATEGORY)

        when:
        queue.requestNewTicket()
        then:
        queue.awaitingNo() == 1

        when:
        queue.requestNewTicket()
        then:
        queue.awaitingNo() == 2

        when:
        queue.requestNewTicket()
        then:
        queue.awaitingNo() == 3
    }


}
