package com.xzcai.study.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xzcai.study.pojo.vo.TestRichVo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Mr.Wang
 * @create: 2018-10-26
 **/
public class Producer {
    static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer=new DefaultMQProducer("producerGroup");
        producer.setNamesrvAddr("47.104.16.255:9876");
        producer.start();
        for(int i=0;i<1;i++){
            Message msg=new Message("TopicTest","TagA",("Hello RocketMQ qw12 " + i).getBytes());
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
