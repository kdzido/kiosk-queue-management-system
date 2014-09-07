package pl.noname.queuing.domain.sharedkernel;

/**
 * Represents an Entity [Evans].
 *
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
public interface Entity<T, ID> {

    /**
     * @param other
     * @return true when have same business IDes
     */
    boolean sameIdentityAs(T other);

    /**
     * @return the business ID
     */
    ID identity();

}
