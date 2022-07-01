package framework.factories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотацией помечается поле, которое должно отображаться, чтобы страница считалась загруженной.
 *
 * @author vmohnachev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PageLoadMark {

}
