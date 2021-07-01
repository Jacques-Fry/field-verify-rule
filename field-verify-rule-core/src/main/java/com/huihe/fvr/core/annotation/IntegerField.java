package com.huihe.fvr.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 整数字段
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 16:39
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface IntegerField {
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
    int max() default 999999999;

    /**
     * 验证错误信息
     */
    String message() default "";
}
