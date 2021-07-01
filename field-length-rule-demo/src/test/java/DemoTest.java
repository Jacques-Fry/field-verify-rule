import com.huihe.flr.DemoApplication;
import com.huihe.flr.pojo.User;
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
        User user = new User();
        user.setUsername("dd");
        user.setPassword("密码");
        user.verify();
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间: " + (endTime - startTime)+"ms");
    }
}
