package com.renyijia.annotation;

import java.lang.annotation.*;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-07-17
 * @email : zhou_wenya@163.com
 * 忽略token
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AuthIgnore {
}
