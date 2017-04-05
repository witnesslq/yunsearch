import com.github.gin.yunsearch.Application;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.repository.YunUserRepository;
import com.github.gin.yunsearch.service.YunUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author GinPonson
 * @since 2017/4/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class YunUserTest {

    @Autowired
    YunUserRepository userRepository;
    @Autowired
    YunUserService userService;

    @Test
    @Transactional
    public void test(){
        YunUser user = new YunUser();
        user.setUpdateTime(new Date());
        user.setAvatarUrl("hhh");
        user.setFollowCrawled(true);
        user.setUk(1233123l);
        user.setUsername("pyj");
        userService.saveIgnore(user);
    }

}
