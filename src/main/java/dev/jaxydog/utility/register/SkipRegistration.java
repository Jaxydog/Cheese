package dev.jaxydog.utility.register;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SkipRegistration {

    boolean client() default true;

    boolean main() default true;

    boolean server() default true;

}
