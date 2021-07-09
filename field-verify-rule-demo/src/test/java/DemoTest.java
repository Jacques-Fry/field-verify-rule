import com.huihe.fvr.DemoApplication;
import com.huihe.fvr.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/6/30 18:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoTest {

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
}
