package com.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: 消息生成者
 * @author: wanghai
 * @time: 2021/5/17 10:10
 */
public class TestProducer {
    public final static String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitMQUtil.checkServer();
        //创建连接工厂
        ConnectionFactory Factory = new ConnectionFactory();
        //设置相关信息
        Factory.setHost("localhost");
        //创建一个新连接
        Connection connection = Factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message;
        for (int i = 0; i < 100; i++) {
            message = "direct 消息 "+i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println("发送消息： " + message);
        }
        channel.close();
        connection.close();

    }

}
