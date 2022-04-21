package ru.itis.pashin.website.common.model.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface FieldDescription {

    /**
     * (Required) The description of the field.
     */
    String description();


    /**
     * (Optional) The name of the field.
     */
    String name() default "";


    /**
     * (Optional) The type of the field.
     */
    String type() default "";
}
