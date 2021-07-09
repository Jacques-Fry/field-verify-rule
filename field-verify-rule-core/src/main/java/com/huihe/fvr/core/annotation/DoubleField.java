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
public @interface DoubleField {
    /**
     * 名称
     */
    String name();

    /**
     * 最小值
     */
    double min() default 0d;

    /**
     * 最大值
     */
    double max() default 99999999999d;

    /**
     * 验证错误信息
     */
    String message() default "";

}
