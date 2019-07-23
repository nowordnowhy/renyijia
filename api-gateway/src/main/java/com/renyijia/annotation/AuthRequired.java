package com.renyijia.annotation;

import java.lang.annotation.*;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-07-17
 * @email : zhou_wenya@163.com
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthRequired {
}
