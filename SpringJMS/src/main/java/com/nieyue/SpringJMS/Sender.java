package com.nieyue.SpringJMS;
import java.util.Date;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
 
/**
 * <b>function:</b> Spring JMSTemplate 消息发送者
 *  整合Spring实现消息发送和接收
 * @author hoojo
 * @createDate 2013-6-24 下午02:18:48
 * @file Sender.java
 * @package com.hoo.mq.spring.support
 * @project ActiveMQ-5.8
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class Sender {
	private static int  i=0;
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:config/activemq.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
 
        for (i = 0; i < 3; i++) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                	message.setString("message", "current system time: " + new Date().getTime()+"\n第"+(i+1)+"次");
                
                return message;
            }
        });
        }
    }
}