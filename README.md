# 自定义字段验证规则

提供四种数据类型的字段验证规则，分别是验证字符串的@StringField、验证整数的@IntegerField、验证浮点数的@FloatField、验证双精度浮点数的@DoubleField，另外字符串验证提供额外的非空验证和自定义的正则表达式验证（长度和正则表达式互不影响会同时生效），可使用message参数来自定义错误信息。

实体类添加注解实例

```java
/**
 * 用户类
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 9:16
 */
public class User implements Serializable, FieldLengthRule {
    private static final long serialVersionUID = 1L;

    @IntegerField(name = "ID",min = 1,max = 10)
    private Integer id;

    @StringField(name = "账号", max = 50, notnull = true,regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String username;

    @StringField(name = "密码", max = 16, message = "密码不能超过16位")
    private String password;

    @DoubleField(name = "余额", min = -100, max = 999)
    private Double balance;

    @FloatField(name = "信用额度",min = 1, max = 999)
    private Float amount;

}
```

调用案例

```java
@Test
public void test01() {
    long startTime = System.currentTimeMillis();
    User user = new User();
    user.setId(2);
    user.setUsername("11@163.com");
    user.setPassword("密码");
    user.setAmount(1f);
    user.verify();
    long endTime = System.currentTimeMillis();
    System.out.println("消耗时间: " + (endTime - startTime)+"ms");
}
```

