package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

/**
 * @author tank
 * @create 2019/01/18 14:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式  direct
     */
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("itcast","直接模式测试");
    }

    /**
     * 分裂模式  fanout
     */
    @Test
    public void sendMsg2() {
        rabbitTemplate.convertAndSend("chuanzhi", "", "分裂模式测试");
    }

    /**
     * 主题模式  fanout
     */
    @Test
    public void sendMsg3() {
        rabbitTemplate.convertAndSend("topic84", "good.abc.log", "主题模式测试");
    }



}
