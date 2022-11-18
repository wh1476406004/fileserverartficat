package com.RabbitMQ;

import cn.hutool.core.util.RandomUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/5/17 10:35
 */
public class TestDriectCustomer {
    public final static String EXCHANGE_NAME="fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        String name  = "consumer~"+ RandomUtil.randomString(5);
        RabbitMQUtil.checkServer();

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        //交换机声明（参数为：交换机名称；交换机类型）
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //获取一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        //队列与交换机绑定（参数为：队列名称；交换机名称；routingKey忽略）
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        System.out.println(name +" 等待接受消息");

        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(name + " 接收到消息 '" + message + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(queueName, true, consumer);

    }
}
