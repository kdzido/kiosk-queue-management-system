package pl.noname.queuing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.noname.queuing.application.ConsultantService;
import pl.noname.queuing.application.QueuingService;
import pl.noname.queuing.domain.model.consultant.Consultant;
import pl.noname.queuing.domain.model.queue.Queue;
import pl.noname.queuing.rest.resources.QueueResource;
import pl.noname.queuing.rest.resources.assembler.QueueResourceAssembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
@RestController
@RequestMapping("/qms/queues")
public class QueueController {

    private ConsultantService consultantService;
    private QueuingService queuingService;

    @Autowired
    public QueueController(ConsultantService consultantService, QueuingService queuingService) {
        this.consultantService = consultantService;
        this.queuingService = queuingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<QueueResource> allQueues() {
        List<QueueResource> result = new ArrayList<QueueResource>();

        QueueResourceAssembler assembler = new QueueResourceAssembler();

        for (Queue item : queuingService.listAllQueues()) {
            Consultant consultant = consultantService.consultantOfId(item.consultantId());

            result.add(assembler.from(item, consultant));
        }

        return result;
    }



}
