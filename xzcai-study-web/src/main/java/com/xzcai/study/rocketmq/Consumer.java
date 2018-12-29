package com.xzcai.study.rocketmq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.ProcessQueue;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Mr.Wang
 * @create: 2018-10-26
 **/
public class Consumer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 将最高位保存在最低位
        for(int i=0;i<1000;i++){
            byte b = (byte)(i & 0xff);
            byte bb = ((Integer)(i & 0xff)).byteValue();
            if(b!=bb){
                System.out.println("-------------------------------------");
            }else{
                System.out.println(b+"   "+bb);
            }
        }
        int nu2 = 1212 & 0xff;// 将最高位保存在最低位

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup");
        consumer.setNamesrvAddr("47.104.16.255:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicTest", "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.ma


//        consumer.registerMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
//                for (MessageExt ext : msgs) {
//                    try {
//                        System.out.println("什么鬼:" + new Date() + new String(ext.getBody(), "UTF-8"));
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//        });

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            AtomicLong consumeTimes = new AtomicLong(0);
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                for (MessageExt ext : msgs) {
                    try {
                        System.out.println("什么鬼:" + new Date() + new String(ext.getBody(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer started");
    }
}
