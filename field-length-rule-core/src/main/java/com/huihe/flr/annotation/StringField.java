package com.huihe.flr.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 字符串字段
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 16:39
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface StringField {
    /**
     * 名称
     */
    String name();

    /**
     * 最小长度
     */
    int min() default 0;

    /**
     * 最大长度
     */
    int max() default 255;

    /**
     * 验证错误信息
     */
    String message() default "";

    /**
     * 是否非空
     */
    boolean notnull() default false;
}
