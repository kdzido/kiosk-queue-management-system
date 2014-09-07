package pl.noname.queuing.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.noname.queuing.domain.model.consultant.Consultant;
import pl.noname.queuing.domain.model.consultant.ConsultantId;
import pl.noname.queuing.domain.model.consultant.ConsultantRepository;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
@Service
public class ConsultantService {

    private ConsultantRepository consultantRepository;

    @Autowired
    public ConsultantService(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    // Commands


    // Queries

    @Transactional(readOnly = true)
    public Consultant consultantOfId(final ConsultantId consultantId) {
        return consultantRepository.find(consultantId);
    }





}
