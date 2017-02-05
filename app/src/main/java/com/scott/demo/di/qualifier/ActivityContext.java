package com.scott.demo.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * author: heshantao
 * data: 2017/2/5.
 * 标记activity的Context
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ActivityContext {
}
