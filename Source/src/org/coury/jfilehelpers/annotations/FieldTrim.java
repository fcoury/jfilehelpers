package org.coury.jfilehelpers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.coury.jfilehelpers.enums.TrimMode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldTrim {
	TrimMode trimMode() default TrimMode.None;
	char[] trimChars() default ' ';
}
