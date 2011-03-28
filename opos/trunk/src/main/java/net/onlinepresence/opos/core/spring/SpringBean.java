package net.onlinepresence.opos.core.spring;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target( { FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface SpringBean {
	String value();
}
