package nono.uses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Identify all use cases
 *
 * @author chusnaval
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {

	String value();

}
