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
public @interface FloatField {
    /**
     * 名称
     */
    String name();

    /**
     * 最小长度
     */
    float min() default 0f;

    /**
     * 最大长度
     */
    float max() default 99999999999f;

    /**
     * 验证错误信息
     */
    String message() default "";
}
