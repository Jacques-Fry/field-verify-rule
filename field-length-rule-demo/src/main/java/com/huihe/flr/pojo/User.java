package com.huihe.flr.pojo;


import com.huihe.flr.annotation.DoubleField;
import com.huihe.flr.annotation.FloatField;
import com.huihe.flr.annotation.IntegerField;
import com.huihe.flr.annotation.StringField;
import com.huihe.flr.core.FieldLengthRule;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 9:16
 */
@Getter
@Setter
public class User implements Serializable, FieldLengthRule {
    private static final long serialVersionUID = 1L;

    @IntegerField(name = "ID",min = 1,max = 10)
    private Integer id;

    @StringField(name = "账号", max = 50, notnull = true)
    private String username;

    @StringField(name = "密码", max = 16, message = "密码不能超过16位")
    private String password;

    @DoubleField(name = "余额", min = -100, max = 999)
    private Double balance;

    @FloatField(name = "信用额度",min = 1, max = 999)
    private Float amount;

}
