package pl.noname.queuing.domain.model.consultant;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 07.09.14.
 */
public class Consultant {
    private ConsultantId consultantId;
    private String name;

    public Consultant(final ConsultantId consultantId, final String name) {
        checkArgument(consultantId != null, "Consultant ID is required");
        checkArgument(name != null && !name.trim().isEmpty(), "Name is required");

        this.consultantId = consultantId;
        this.name = name;
    }

    public ConsultantId consultantId() {
        return consultantId;
    }

    public String name() {
        return name;
    }





}
