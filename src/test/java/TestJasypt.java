

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.App;


@RunWith(SpringJUnit4ClassRunner.class) // 让junit与spring环境进行整合
@SpringBootTest(classes = { App.class }) // 自动加载spring相关的配置文件
public class TestJasypt {
	@Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        String password = encryptor.encrypt("BJ333YCFXzyzHY");
        System.out.println(password+"----------------");
    }
}
