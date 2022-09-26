package com.zzzj.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author zzzj
 * @create 2021-11-11 16:05
 */
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Timeout {

}
