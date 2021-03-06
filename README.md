# 自定义字段验证规则

提供四种数据类型的字段验证规则，分别是验证字符串的[@StringField](/field-verify-rule-core/src/main/java/com/huihe/fvr/core/annotation/StringField.java)、验证整数的[@IntegerField](/field-verify-rule-core/src/main/java/com/huihe/fvr/core/annotation/IntegerField.java)、验证浮点数的[@FloatField](/field-verify-rule-core/src/main/java/com/huihe/fvr/core/annotation/FloatField.java)、验证双精度浮点数的[@DoubleField](/field-verify-rule-core/src/main/java/com/huihe/fvr/core/annotation/DoubleField.java)，另外字符串验证提供额外的非空验证和自定义的正则表达式验证（长度和正则表达式互不影响会同时生效），可使用message参数来自定义错误信息。验证不通过会抛出FieldVerifyRuleException异常，请做好异常捕获。

#### 2.21.07.09更新v1.1.3.RELEASE

1、新增支持字符串枚举类型的[@StringEnumField](/field-verify-rule-core/src/main/java/com/huihe/fvr/core/annotation/StringEnumField.java)和整数枚举类型的[@IntegerEnumField](./field-verify-rule-core/src/main/java/com/huihe/fvr/core/annotation/IntegerEnumField.java)

2、新增支持单个字段验证



#### 包引用

```xml
<!--字段验证工具-->
<dependency>
    <groupId>com.huihe</groupId>
    <artifactId>field-verify-rule-core</artifactId>
    <version>1.1.4.RELEASE</version>
</dependency>
```



#### 实体类添加注解示例

> 请注意：一定要使用类型对应的的验证规则注解，否则验证可能不会生效。业务实体类需要实现FieldVerifyRule接口，仅仅只是引入即可无需做方法实现。

```java
/**
 * 用户类
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/1 9:16
 */
public class User implements Serializable, FieldVerifyRule {
    private static final long serialVersionUID = 1L;

    @IntegerField(name = "ID", min = 1, max = 10)
    private Integer id;

    @StringField(name = "账号", max = 50, notnull = true, regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String username;

    @StringField(name = "密码", max = 16, message = "密码不能超过16位")
    private String password;

    @DoubleField(name = "余额", min = -100, max = 999)
    private Double balance;

    @FloatField(name = "信用额度", min = 1, max = 999)
    private Float amount;

    @IntegerEnumField(name = "用户类型", value = {1, 2, 3},message = "用户类型只能是 1:普通 | 2:管理员 | 3:超级管理员")
    private int type;

    @StringEnumField(name = "性别", value = {"男", "女"})
    private String sex;

}
```



#### 调用验证示例

> 为确保业务环境的正常进行所以在设计上是需要手动使用业务对象.verify()调用验证函数。

```java
@Test
public void test01() {
    long startTime = System.currentTimeMillis();
    // IntStream.range(1,1000001).forEach(i->{
    User user = new User();
    user.setId(2);
    user.setUsername("11@163.com");
    user.setPassword("密码");
    user.setAmount(1f);
    user.setType(0);
    user.setSex("人妖");
    // 验证单个字段
    user.verify("username");
    user.verify("sex");
    // 验证所有字段
    user.verify();
    // });
    long endTime = System.currentTimeMillis();
    System.out.println("消耗时间: " + (endTime - startTime) + "ms");
}
```

