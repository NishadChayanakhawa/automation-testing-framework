package io.nishadc.automationtestingframework.testngcustomization.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
	int value() default 0;
}
