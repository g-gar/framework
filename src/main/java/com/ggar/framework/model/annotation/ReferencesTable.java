package com.ggar.framework.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface ReferencesTable {
	String value();
}
