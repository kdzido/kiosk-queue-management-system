package pl.noname.queuing.rest

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.noname.queuing.application.ConsultantService
import pl.noname.queuing.application.QueuingService
import pl.noname.queuing.domain.model.ticket.TicketId
import pl.noname.queuing.domain.model.queue.QueueId
import pl.noname.queuing.rest.controller.TicketController
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
class ViewQueueTailIntegrationTest extends Specification {

    MockMvc mockMvc
    ConsultantService consultantService
    QueuingService queuingService
    TicketController controller

    def setup() {
        queuingService = Mock()
        controller = new TicketController(queuingService)
        mockMvc = standaloneSetup(controller).build()
    }

    def "should return HTTP Not found"() {
        when:
        def result = mockMvc.perform(
                get("/qms/queues/{id}/tail", "A")
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.queueTail(_ as QueueId) >> null
        result
                .andDo(print())
                .andExpect(status().isNotFound())
    }

    def "should return HTTP Ok"() {
        when:
        def result = mockMvc.perform(
                get("/qms/queues/{id}/tail", "A")
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.queueTail(_ as QueueId) >> new TicketId('A00')
        result
                .andDo(print())
                .andExpect(status().isOk())
    }

    def "should render as json"() {
        when:
        def result = mockMvc.perform(
                get("/qms/queues/{id}/tail", "A")
                        .accept(MediaType.APPLICATION_JSON))
        then:
        1 * queuingService.queueTail(_ as QueueId) >> new TicketId('A00')
        result
                .andDo(print())
                .andExpect(jsonPath('$queueId').value('A'))
                .andExpect(jsonPath('$ticketId').value('A00'))
    }

}
