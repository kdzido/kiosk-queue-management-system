package pl.noname.queuing.rest

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.noname.queuing.application.ConsultantService
import pl.noname.queuing.application.QueuingService
import pl.noname.queuing.domain.model.consultant.ConsultantId
import pl.noname.queuing.rest.controller.QueueController
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import static pl.noname.queuing.DomainDataFixture.allQueues
import static pl.noname.queuing.DomainDataFixture.consultant1;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
class ListAllQueuesIntegrationTest extends Specification {

    MockMvc mockMvc
    ConsultantService consultantService
    QueuingService queuingService
    QueueController controller

    def setup() {
        queuingService = Mock()
        consultantService = Mock()
        controller = new QueueController(consultantService, queuingService)
        mockMvc = standaloneSetup(controller).build()
    }

    def "should render as json"() {
        when:
        def result = mockMvc.perform(
                get("/qms/queues")
                        .accept(MediaType.APPLICATION_JSON))
        then:
        _ * consultantService.consultantOfId(_ as ConsultantId) >> consultant1()
        1 * queuingService.listAllQueues() >> allQueues()
        result
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].queueId').value('A'))
                .andExpect(jsonPath('$[1].queueId').value('B'))
                .andExpect(jsonPath('$[2].queueId').value('C'))
    }

}
