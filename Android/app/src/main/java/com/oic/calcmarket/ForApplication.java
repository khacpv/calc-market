package com.oic.calcmarket;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by khacpham on 1/31/16.
 */
@Qualifier @Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
