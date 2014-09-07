package pl.noname.queuing.domain.sharedkernel;

/**
 * Represents an immutable Value Object [Evans].
 * Applies only to a domain model.
 *
 * Created by k.dzido <krzysztof.dzido@gmail.com> on 06.09.14.
 */
public interface ValueObject<T> {

    boolean sameValueAs(T other);

}
