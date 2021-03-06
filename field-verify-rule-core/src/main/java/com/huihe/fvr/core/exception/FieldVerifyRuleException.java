package com.huihe.fvr.core.exception;

/**
 * 字段限制异常
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 16:40
 */
public class FieldVerifyRuleException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public FieldVerifyRuleException(String message) {
        super(message);
    }
}
