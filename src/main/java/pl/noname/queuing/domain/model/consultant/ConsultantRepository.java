package pl.noname.queuing.domain.model.consultant;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
public interface ConsultantRepository {

    Consultant find(ConsultantId consultantId);

    void store(Consultant consultant);

    ConsultantId nextId();

}
