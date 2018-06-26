import cn.hxw.study.Application;
import cn.hxw.study.ClassPathXmlApplicationContext;
import cn.hxw.study.entity.Master;

import java.io.File;

/**
 * 参考：http://www.cnblogs.com/1016882435AIDA/p/5980770.html
 * @author huangxiaowei
 * @date 2018/6/26 10:09
 * @description
 */
public class TestAOP {

    public static void main(String[] args) {
        Application application = new ClassPathXmlApplicationContext("applicationContent.xml");
        Master master = (Master) application.getBean("humanProxy");

        master.shopping();
        master.walkDog();

    }
}
