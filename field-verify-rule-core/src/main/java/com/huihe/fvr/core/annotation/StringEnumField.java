package com.huihe.fvr.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 字符串枚举验证
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/7/9 11:16
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface StringEnumField {
    /**
     * 名称
     */
    String name();

    /**
     * 枚举值
     */
    String[] value();

    /**
     * 验证错误信息
     */
    String message() default "";

}
