package com.xzcai.study.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.xzcai.study.config.Test;
import com.xzcai.study.dal.dao.UserFeedbackMapper;
import com.xzcai.study.pojo.po.UserFeedbackPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author: created by mifang
 * @create: 2018-11
 * -07
 **/
public class MainTest {
    public static void main(String[] args) throws Exception {
        TreeMap<Test,String> treeMap=new TreeMap();
        treeMap.put(new Test(),"123");

        EnumTest[] enumTests = EnumTest.class.getEnumConstants();
        enumTests[0].getCode();
        enumTests[0].name();
        enumTests[0].ordinal();
        JSONObject campaignInfoJsonObject = new JSONObject();
        BigDecimal decimal =  campaignInfoJsonObject.getBigDecimal("sdf");

        Boolean bool = StringUtils.isBlank("");

        String resource = "mybatis-test/mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserFeedbackMapper userFeedbackMapper = sqlSession.getMapper(UserFeedbackMapper.class);
        UserFeedbackPO userFeedbackPO = new UserFeedbackPO();
        userFeedbackMapper.save(userFeedbackPO);
    }

    private static void test(Object[] args) {
        System.out.println(args.length);
    }

    private static Thread test(){
         Thread thread = new Thread(test3());
         thread.start();
         return thread;
    }

    private static Callable<Boolean> test2(){
        return ()->{
          System.out.println("123");
          return true;
        };
    }

    private static Runnable test3(){
        return ()->{
            while (true){
                System.out.println("test");
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
