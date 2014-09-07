package pl.noname.queuing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.noname.queuing.application.QueuingService;
import pl.noname.queuing.domain.model.ticket.TicketId;
import pl.noname.queuing.domain.model.queue.QueueId;
import pl.noname.queuing.rest.resources.TicketResource;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
@RestController
@RequestMapping("/qms/queues/{queueId}")
public class TicketController {

    private QueuingService queuingService;

    @Autowired
    public TicketController(QueuingService queuingService) {
        this.queuingService = queuingService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/head")
    public ResponseEntity<TicketResource> getQueueHead(@PathVariable String queueId) {
        TicketId head = queuingService.queueHead(new QueueId(queueId));

        if (head == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new TicketResource("A", "A00"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tail")
    public ResponseEntity<TicketResource> getQueueTail(@PathVariable String queueId) {
        TicketId tail = queuingService.queueTail(new QueueId(queueId));

        if (tail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new TicketResource("A", "A00"), HttpStatus.OK);
    }




//    @RequestMapping(method = RequestMethod.DELETE, value = "/head")
//    public ResponseEntity<TicketResource> head(@PathVariable String queueId) {
//        return null;
//    }
//
    @RequestMapping(method = RequestMethod.POST, value = "/requestTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketResource> requestTicket(@PathVariable String queueId, @RequestBody TicketResource newTicket, UriComponentsBuilder builder) {
        TicketId newTicketId = queuingService.requestTicket(new QueueId(queueId));

        if (newTicketId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/qms/queues/{queueId}/tickets/{ticketId}")
                        .buildAndExpand(queueId, newTicketId.getId()).toUri()
        );

        return new ResponseEntity<>(new TicketResource(queueId, newTicketId.getId()), headers, HttpStatus.CREATED);
    }

}
