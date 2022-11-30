package com.wolfbe.chat.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class rabbit {
    public  Connection connection;
    public rabbit() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();//MQ采用工厂模式来完成连接的创建
        //2.在工厂对象中设置连接信息(ip,port,virtualhost,username,password)
        factory.setHost("106.12.131.109");//设置MQ安装的服务器ip地址
        factory.setPort(5672);//设置端口号
        factory.setVirtualHost("/");//设置虚拟主机名称
        //MQ通过用户来管理
        factory.setUsername("guest");//设置用户名称
        factory.setPassword("jy2051914");//设置用户密码
        //3.通过工厂对象获取连接
        connection = factory.newConnection();
    }
    /**
     *  向指定消息队列发送消息
     */
    public  void sendMessage(String message,String key) throws IOException, TimeoutException {
        //mq提供Channel来将处理消息
        //创建Channel
        Channel channel = connection.createChannel();
        //basicPublish将消息发送到指定的交换机
        channel.basicPublish("", key, null, message.getBytes());
        //关闭连接
        channel.close();
        //connection.close();
    }

    /**
     * 创建新队列
     */
    public void create_queue(String name) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(name, false, false, false, null);
        channel.close();
    }

    /**
     * 关闭连接
     * @throws IOException
     */
    public void close() throws IOException {
        connection.close();
    }



}
