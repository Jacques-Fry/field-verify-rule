package com.huihe.fvr.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 整数枚举验证
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/7/9 11:16
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface IntegerEnumField {
    /**
     * 名称
     */
    String name();

    /**
     * 枚举值
     */
    int[] value();

    /**
     * 验证错误信息
     */
    String message() default "";

}
