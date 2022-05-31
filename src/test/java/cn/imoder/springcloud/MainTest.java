package cn.imoder.springcloud;

import cn.imoder.springcloud.dao.PaymentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Payment8001AppBoot.class)
public class MainTest {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    DataSource dataSource;

    @Test
    public void connTest() {
        System.out.println(paymentDao.getPaymentById(1L));
    }

    @Test
    public void test() throws Exception {
        System.out.println(dataSource.getConnection());
    }


}
