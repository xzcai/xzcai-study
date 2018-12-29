package com.xzcai.study.rocketmq;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

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


        producer.start();
        for (int i = 0; i < 20; i++) {
            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ " + i).getBytes());
//            msg.setKeys();
            //msg.setDelayTimeLevel(4);
            SendResult sendResult = producer.send(msg, new OrderMessageQueueSelector(), i);
            //SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
            if (i % 5 == 4) {
                Thread.sleep(5000);
            }
        }
        producer.shutdown();
    }

    private static class OrderMessageQueueSelector implements MessageQueueSelector {
        @Override
        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
            int id = Integer.parseInt(arg.toString());
            int size = mqs.size();
            int index = id % size;
            return mqs.get(0);
        }
    }
}
