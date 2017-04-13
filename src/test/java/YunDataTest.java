import com.github.gin.yunsearch.Application;
import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.repository.YunDataRepository;
import com.github.gin.yunsearch.repository.YunUserRepository;
import com.github.gin.yunsearch.service.els.YunDataEls;
import com.github.gin.yunsearch.service.jpa.YunDataService;
import com.github.gin.yunsearch.service.jpa.YunUserService;
import com.github.gin.yunsearch.util.DuplicateRemover;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class YunDataTest {

    @Autowired
    YunDataService dataService;
    @Autowired
    YunDataEls yunDataEls;

    @Test
    public void test() throws IOException {
        List<YunData> dataList = dataService.findAll();
        yunDataEls.bulkSave(dataList);
    }

    @Autowired
    DuplicateRemover remover;
    @Test
    public void testRemoveDuplicate() throws IOException {
        remover.removeDuplicate();
    }
}
