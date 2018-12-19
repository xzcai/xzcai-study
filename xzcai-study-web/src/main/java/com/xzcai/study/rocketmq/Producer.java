package com.xzcai.study.rocketmq;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author: Mr.Wang
 * @create: 2018-10-26
 **/
public class Producer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("test");
        producer.setNamesrvAddr("47.104.16.255:9876");
        //producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        //producer.setVipChannelEnabled(false);
        //producer.setInstanceName("producer-instance");

        //TransactionMQProducer

        producer.start();
        Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ ").getBytes());
        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);
        producer.shutdown();
    }
}
