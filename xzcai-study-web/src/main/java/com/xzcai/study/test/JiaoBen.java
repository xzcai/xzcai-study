package com.xzcai.study.test;

import com.alibaba.fastjson.JSON;
import com.ywwl.common.bean.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: created by mifang
 * @create: 2019-03-14
 **/
@Slf4j
public class JiaoBen {
    static HttpGet request = null;
    static String wechatHost = "http://test.pl.ywwl.com/ywwl-wechat-web-test/";//"http://m.jordonyu.com/ywwl-wechat-web/";//"http://localhost:8080/ywwl-wechat-web/";
    static String toolsHost = "http://172.16.5.175:8024/ywwl-tools-web-test/"; //"http://172.16.5.211:8036/ywwl-tools-web/";//"http://localhost:8009/ywwl-tools-web/";
    static String qunfaHost = "http://172.16.5.216:8038/ywwl-tools-web/"; //"http://localhost:8038//ywwl-tools-web/";
    static String createMqConsumer = "/mqCreateAndClose/mq/createMqConsumer?consumerKey=";
    static String createMqProducer = "/mqCreateAndClose/mq/createMqProducer?produecerKey=";

    static String closeMqConsumer = "/mqCreateAndClose/mq/closeMqConsumer?topic=";
    static String closeMqProducer = "/mqCreateAndClose/mq/closeMqProducer?produecerKey=";

    static String juadgeProductionOrConsumerIsClosed = "/mqCreateAndClose/mq/juadgeProductionOrConsumerIsClosed?key=";

    static String wechatToken = "mc44odk1ntuymdcxmda3ntcxxzy0ndyzndyyndeymjgzmtk=";
    static String wechatFinger = "448abdb2cf4d5f12620c01169b0b1ef6";

    public static void main(String[] args) throws IOException {
        newToOldTest();
    }

    @Test
    public static void newToOld() throws IOException {
        //region 1 wechat生产者开启
        //开启wechat生产者
        request = new HttpGet(wechatHost + createMqProducer + "PID_ywwl_wechat_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(JSON.toJSONString(get(request)));
        //校验生产者是否开启
        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "PID_ywwl_wechat_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        BaseResult baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 生产者开启成功");
        } else {
            System.out.println("============wechat 生产者开启失败");
        }

        //endregion

        //region 2 tools中所有消费者开启
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key）
        request = new HttpGet(toolsHost + createMqConsumer + "wechatCustomConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 客服消息消费者开启成功");
        } else {
            System.out.println("============tools 客服消息消费者开启失败");
        }


        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key）
        request = new HttpGet(toolsHost + createMqConsumer + "wechatTemplateConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 模板消息消费者开启成功");
        } else {
            System.out.println("============tools 模板消息消费者开启失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key）
        request = new HttpGet(toolsHost + createMqConsumer + "CID_ywwl_tools_delayMsg_rd");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 延时消息消费者开启成功");
        } else {
            System.out.println("============tools 延时消息消费者开启失败");
        }
        //endregion

        //region 3 wechat中消费者开启
        //3 wechat中消费者开启
        //key:CID_wechat_timing_msg_rd（定时任务消费者key，客服、模板、延时）
        request = new HttpGet(wechatHost + createMqConsumer + "CID_wechat_timing_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 定时消息消费者开启成功");
        } else {
            System.out.println("============wechat 定时消息消费者开启失败");
        }

        //key:CID_wechat_timing_group_msg_rd（定时群发消费者key）
        request = new HttpGet(wechatHost + createMqConsumer + "CID_wechat_timing_group_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_group_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 定时群发消息消费者开启成功");
        } else {
            System.out.println("============wechat 定时群发消息消费者开启失败");
        }
        //endregion

        //region 4 qunfa中所有消费者关闭
        //4 qunfa中所有消费者关闭
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key） topic:topic_wechat_custom_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_custom_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 客服消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 客服消息消费者关闭失败");
        }


        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key） topic:topic_wechat_template_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_template_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 模板消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 模板消息消费者关闭失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key） topic:topic_wechat_delay_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_delay_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 延时消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 模板消息消费者关闭失败");
        }

        //key:CID_wechat_timing_msg_rd（定时任务消费者key，客服、模板、延时） topic:topic_wechat_timing_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_timing_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_msg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 定时消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 定时消息消费者关闭失败");
        }

        //key:CID_wechat_timing_group_msg_rd（定时群发消费者key） topic:topic_wechat_timing_group_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_timing_group_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_group_msg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 定时群发消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 定时群发消息消费者关闭失败");
        }
        //endregion

        //region 5 qunfa定时任务关闭和生产者关闭
        //5 qunfa定时任务关闭和生产者关闭
        //key:PID_ywwl_wechat_rd
        request = new HttpGet(qunfaHost + closeMqProducer + "PID_ywwl_wechat_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "PID_ywwl_wechat_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 生产者关闭成功");
        } else {
            System.out.println("============qunfa 生产者关闭失败");
        }
        //endregion

        //region 6 wechat定时任务开启
        //6 wechat定时任务开启
        //endregion
    }

    @Test
    public static void newToOldTest() throws IOException {
        //region 1 wechat生产者开启
        //开启wechat生产者
        request = new HttpGet(wechatHost + createMqProducer + "PID_ywwl_wechat_qa");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(JSON.toJSONString(get(request)));
        //校验生产者是否开启
        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "PID_ywwl_wechat_qa");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        BaseResult baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 生产者开启成功");
        } else {
            System.out.println("============wechat 生产者开启失败");
        }

        //endregion

        //region 2 tools中所有消费者开启
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key）
        request = new HttpGet(toolsHost + createMqConsumer + "wechatCustomConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 客服消息消费者开启成功");
        } else {
            System.out.println("============tools 客服消息消费者开启失败");
        }


        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key）
        request = new HttpGet(toolsHost + createMqConsumer + "wechatTemplateConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 模板消息消费者开启成功");
        } else {
            System.out.println("============tools 模板消息消费者开启失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key）
        request = new HttpGet(toolsHost + createMqConsumer + "CID_ywwl_tools_delayMsg_qa");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_qa");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 延时消息消费者开启成功");
        } else {
            System.out.println("============tools 延时消息消费者开启失败");
        }
        //endregion

        //region 3 wechat中消费者开启
        //3 wechat中消费者开启
        //key:CID_wechat_timing_msg_rd（定时任务消费者key，客服、模板、延时）
        request = new HttpGet(wechatHost + createMqConsumer + "CID_wechat_timing_msg_qa");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_msg_qa");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 定时消息消费者开启成功");
        } else {
            System.out.println("============wechat 定时消息消费者开启失败");
        }

        //key:CID_wechat_timing_group_msg_rd（定时群发消费者key）
        request = new HttpGet(wechatHost + createMqConsumer + "CID_wechat_timing_group_msg_qa");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_group_msg_qa");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 定时群发消息消费者开启成功");
        } else {
            System.out.println("============wechat 定时群发消息消费者开启失败");
        }
        //endregion

        //region 4 qunfa中所有消费者关闭
        //4 qunfa中所有消费者关闭
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key） topic:topic_wechat_custom_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_custom_msg_qa");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 客服消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 客服消息消费者关闭失败");
        }


        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key） topic:topic_wechat_template_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_template_msg_qa");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 模板消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 模板消息消费者关闭失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key） topic:topic_wechat_delay_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_delay_msg_qa");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_qa");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 延时消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 模板消息消费者关闭失败");
        }

        //key:CID_wechat_timing_msg_rd（定时任务消费者key，客服、模板、延时） topic:topic_wechat_timing_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_timing_msg_qa");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_msg_qa");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 定时消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 定时消息消费者关闭失败");
        }

        //key:CID_wechat_timing_group_msg_rd（定时群发消费者key） topic:topic_wechat_timing_group_msg_rd
        request = new HttpGet(qunfaHost + closeMqConsumer + "topic_wechat_timing_group_msg_qa");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_group_msg_qa");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 定时群发消息消费者关闭成功");
        } else {
            System.out.println("============qunfa 定时群发消息消费者关闭失败");
        }
        //endregion

        //region 5 qunfa定时任务关闭和生产者关闭
        //5 qunfa定时任务关闭和生产者关闭
        //key:PID_ywwl_wechat_rd
        request = new HttpGet(qunfaHost + closeMqProducer + "PID_ywwl_wechat_qa");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "PID_ywwl_wechat_qa");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------qunfa 生产者关闭成功");
        } else {
            System.out.println("============qunfa 生产者关闭失败");
        }
        //endregion

        //region 6 wechat定时任务开启
        //6 wechat定时任务开启
        //endregion
    }


    @Test
    public static void oldToNew() throws IOException {
        //region 1 qunfa生产者创建
        //key:PID_ywwl_wechat_rd
        request = new HttpGet(qunfaHost + createMqProducer + "PID_ywwl_wechat_rd");
        System.out.println(get(request));

        //校验生产者是否开启
        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "PID_ywwl_wechat_rd");
        BaseResult baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------wechat 生产者开启成功");
        } else {
            System.out.println("============wechat 生产者开启失败");
        }
        //endregion

        //region 2 qunfa消费者全部开启（按照顺序开启）
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key）
        //topic:topic_wechat_custom_msg_rd
        request = new HttpGet(qunfaHost + createMqConsumer + "wechatCustomConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------qunfa 客服消息消费者开启成功");
        } else {
            System.out.println("============qunfa 客服消息消费者开启失败");
        }

        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key）
        //topic:topic_wechat_template_msg_rd
        request = new HttpGet(qunfaHost + createMqConsumer + "wechatTemplateConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------qunfa 模板消息消费者开启成功");
        } else {
            System.out.println("============qunfa 模板消息消费者开启失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key）
        //topic:topic_wechat_delay_msg_rd
        request = new HttpGet(qunfaHost + createMqConsumer + "CID_ywwl_tools_delayMsg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------qunfa 延时消息消费者开启成功");
        } else {
            System.out.println("============qunfa 延时消息消费者开启失败");
        }
        //key:CID_wechat_timing_msg_rd（定时任务消费者key，客服、模板、延时）
        //topic:topic_wechat_timing_msg_rd
        request = new HttpGet(qunfaHost + createMqConsumer + "CID_wechat_timing_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_msg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------qunfa 定时消息消费者开启成功");
        } else {
            System.out.println("============qunfa 定时消息消费者开启失败");
        }

        //key:CID_wechat_timing_group_msg_rd（定时群发消费者key）
        //topic:topic_wechat_timing_group_msg_rd
        request = new HttpGet(qunfaHost + createMqConsumer + "CID_wechat_timing_group_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(qunfaHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_group_msg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------qunfa 定时群发消息消费者开启成功");
        } else {
            System.out.println("============qunfa 定时群发消息消费者开启失败");
        }
        //endregion

        //region 3 tools中消费者关闭
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key）
        //topic:topic_wechat_custom_msg_rd
        request = new HttpGet(toolsHost + closeMqConsumer + "topic_wechat_custom_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------tools 客服消息消费者关闭成功");
        } else {
            System.out.println("============tools 客服消息消费者关闭失败");
        }

        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key）
        //topic:topic_wechat_template_msg_rd
        request = new HttpGet(toolsHost + closeMqConsumer + "topic_wechat_template_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------tools 模板消息消费者关闭成功");
        } else {
            System.out.println("============tools 模板消息消费者关闭失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key）
        //topic:topic_wechat_delay_msg_rd
        request = new HttpGet(toolsHost + closeMqConsumer + "topic_wechat_delay_msg_rd");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------tools 延时消息消费者关闭成功");
        } else {
            System.out.println("============tools 延时消息消费者关闭失败");
        }

        //endregion

        //region 4 wechat消费者关闭
        //key:CID_wechat_timing_msg_rd（定时任务消费者key，客服、模板、延时）
        //topic:topic_wechat_timing_msg_rd
        request = new HttpGet(wechatHost + closeMqConsumer + "topic_wechat_timing_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------wechat 定时消息消费者关闭成功");
        } else {
            System.out.println("============wechat 定时消息消费者关闭失败");
        }

        //key:CID_wechat_timing_group_msg_rd（定时群发消费者key）
        //topic:topic_wechat_timing_group_msg_rd
        request = new HttpGet(wechatHost + closeMqConsumer + "topic_wechat_timing_group_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "CID_wechat_timing_group_msg_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------wechat 定时群发消息消费者关闭成功");
        } else {
            System.out.println("============wechat 定时群发消息消费者关闭失败");
        }

        //endregion

        //region 5 wechat定时任务和生产者关闭
        //key:PID_ywwl_wechat_rd
        request = new HttpGet(wechatHost + closeMqProducer + "PID_ywwl_wechat_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        System.out.println(get(request));

        request = new HttpGet(wechatHost + juadgeProductionOrConsumerIsClosed + "PID_ywwl_wechat_rd");
        request.setHeader("X-TOKEN", wechatToken);
        request.setHeader("X-FINGER", wechatFinger);
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.SUCCESS) {
            System.out.println("------------wechat 生产者关闭成功");
        } else {
            System.out.println("============wechat 生产者关闭失败");
        }
        //endregion

        //region 6 qunfa定时任务开启
        //
        //endregion
    }

    @Test
    public static void openTools() throws IOException {
        //region 2 tools中所有消费者开启
        //key:wechatCustomConsumer_consumerkey（客服消息消费者key）
        request = new HttpGet(toolsHost + createMqConsumer + "wechatCustomConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatCustomConsumer_consumerkey");
        BaseResult baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 客服消息消费者开启成功");
        } else {
            System.out.println("============tools 客服消息消费者开启失败");
        }


        //key:wechatTemplateConsumer_consumerkey（模板消息消费者key）
        request = new HttpGet(toolsHost + createMqConsumer + "wechatTemplateConsumer_consumerkey");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "wechatTemplateConsumer_consumerkey");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 模板消息消费者开启成功");
        } else {
            System.out.println("============tools 模板消息消费者开启失败");
        }

        //key:CID_ywwl_tools_delayMsg_rd（延时消息消费key）
        request = new HttpGet(toolsHost + createMqConsumer + "CID_ywwl_tools_delayMsg_rd");
        System.out.println(get(request));

        request = new HttpGet(toolsHost + juadgeProductionOrConsumerIsClosed + "CID_ywwl_tools_delayMsg_rd");
        baseResult = get(request);
        if (baseResult.getCode() == BaseResult.FAILED) {
            System.out.println("------------tools 延时消息消费者开启成功");
        } else {
            System.out.println("============tools 延时消息消费者开启失败");
        }
        //endregion
    }

    @Test
    public static void init() {

    }


    public static BaseResult get(HttpGet request) throws IOException {

        CloseableHttpClient client = HttpClients.custom().build();

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(45 * 1000).setConnectTimeout(45 * 1000).build();
        request.setConfig(requestConfig);


        CloseableHttpResponse response = client.execute(request);

        String var7;
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (200 != statusCode) {
                log.error("HttpUtil.handleGet请求返回数据有误,statusCode=" + statusCode + ":" + responseStr);
                var7 = null;
                JSON.parseObject(var7, BaseResult.class);
            }

            log.debug("HttpUtil.handleGet请求返回数据:" + responseStr);
            var7 = responseStr;
        } finally {
            if (response != null) {
                response.close();
            }

        }

        return JSON.parseObject(var7, BaseResult.class);

    }
}
