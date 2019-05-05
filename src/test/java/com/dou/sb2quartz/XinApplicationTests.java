package com.dou.sb2quartz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.dou.sb2quartz.module.demo.MyApplicationAware;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XinApplication.class)
@WebAppConfiguration
public class XinApplicationTests {

    @Autowired
    MyApplicationAware myApplicationAware;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void contextLoads() {
        // 1.
        // ClassPathResource resource = new ClassPathResource("spring.xml");
        // DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // reader.loadBeanDefinitions(resource);
        // MyApplicationAware applicationAware = (MyApplicationAware) factory.getBean("myApplicationAware");
        // applicationAware.display();

        // 2.
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // MyApplicationAware applicationAware = (MyApplicationAware) applicationContext.getBean("myApplicationAware");
        // applicationAware.display();

        // 3.
        myApplicationAware.display();
    }

}
