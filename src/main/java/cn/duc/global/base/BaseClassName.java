package cn.duc.global.base;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

@Retention(RUNTIME)
@Inherited
public @interface BaseClassName {
	 String value();
}
