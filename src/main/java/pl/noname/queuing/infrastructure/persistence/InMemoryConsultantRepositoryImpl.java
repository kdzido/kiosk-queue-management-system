package pl.noname.queuing.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import pl.noname.queuing.domain.model.consultant.Consultant;
import pl.noname.queuing.domain.model.consultant.ConsultantId;
import pl.noname.queuing.domain.model.consultant.ConsultantRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
@Repository
public class InMemoryConsultantRepositoryImpl implements ConsultantRepository {

    private Map<ConsultantId, Consultant> consultants = new HashMap<>();

    @Override
    public Consultant find(final ConsultantId consultantId) {
        return this.consultants.get(consultantId);
    }

    @Override
    public void store(final Consultant consultant) {
        this.consultants.put(consultant.consultantId(), consultant);
    }

    @Override
    public ConsultantId nextId() {
        return new ConsultantId(UUID.randomUUID().toString());
    }
}
