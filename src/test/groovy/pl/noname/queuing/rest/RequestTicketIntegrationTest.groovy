package pl.noname.queuing.rest

import org.hamcrest.Matchers
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.noname.queuing.application.ConsultantService
import pl.noname.queuing.application.QueuingService
import pl.noname.queuing.domain.model.ticket.TicketId
import pl.noname.queuing.domain.model.queue.QueueId
import pl.noname.queuing.rest.controller.TicketController
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
class RequestTicketIntegrationTest extends Specification {

    MockMvc mockMvc
    ConsultantService consultantService
    QueuingService queuingService
    TicketController controller

    def setup() {
        consultantService = Mock()
        queuingService = Mock()
        controller = new TicketController(queuingService)
        mockMvc = standaloneSetup(controller).build()
    }

    def "should return HTTP Bad request"() {
        given:
        def queueId = new QueueId('A');
        when:
        def result = mockMvc.perform(
                post("/qms/queues/{id}/requestTicket", queueId.getId())
                        .content("""{ "queueId":"${queueId.id}" }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.requestTicket(_ as QueueId) >> null
        result
                .andDo(print())
                .andExpect(status().isBadRequest())
    }


    def "should return HTTP Created"() {
        given:
        def queueId = new QueueId('A');
        when:
        def result = mockMvc.perform(
                post("/qms/queues/{id}/requestTicket", queueId.getId())
                        .content("""{ "queueId":"${queueId.id}" }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.requestTicket(queueId) >> new TicketId('A00')
        result
                .andDo(print())
                .andExpect(status().isCreated())
    }


    def "should render as json"() {
        given:
        def queueId = new QueueId('A');
        when:
        def result = mockMvc.perform(
                post("/qms/queues/{id}/requestTicket", queueId.getId())
                        .content("""{ "queueId":"${queueId.id}" }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.requestTicket(queueId) >> new TicketId('A00')
        result
                .andDo(print())
                .andExpect(jsonPath('$.queueId').value('A'))
                .andExpect(jsonPath('$.ticketId').value('A00'))
    }


    def "should return Location header"() {
        given:
        def queueId = new QueueId('A');
        when:
        def result = mockMvc.perform(
                post("/qms/queues/{id}/requestTicket", queueId.getId())
                        .content("""{ "queueId":"${queueId.id}" }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.requestTicket(queueId) >> new TicketId('A00')
        result
                .andDo(print())
                .andExpect(header().string("Location", Matchers.endsWith("/qms/queues/${queueId.id}/tickets/A00")))
    }

}
