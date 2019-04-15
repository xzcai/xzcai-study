package com.xzcai.study.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Mr.Wang
 * @create: 2018-10-25
 **/
public class AtomicTest {
    static {
        try {
            int n = 10;
            int nn = n / 0;
        } catch (Exception e) {
            throw new RuntimeException("sdf");
        }
    }

    public int count = 0;
    private static final Object lockObj = new Object();
    AtomicInteger atomicInteger = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        long arg1 = 10L;
        long arg2 = 3L;
        long arg = arg1 / arg2;

        double arg111 = Math.ceil((double) arg1 / arg2);
        double arg1111 = Math.ceil(3.3);
        boolean b = StringUtils.equals(null, null);
        boolean bb = StringUtils.equals(null, "sdf");


        boolean bbb = StringUtils.equals("sdf", "sdf");


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            //微信预留11s
            System.out.println(sf.format(c.getTime()) + " 00:00:11");
            SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sf2.parse(sf.format(c.getTime()) + " 00:00:11").getTime() / 1000);
            c.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println(sf2.parse(sf.format(c.getTime()) + " 00:00:10").getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        Calendar now = Calendar.getInstance();
        Date date = now.getTime();
        System.out.println(date.getTime() / 1000);

        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("123");
        List<String> list2 = new ArrayList<>();
        list2.add("456");

        list.addAll(list1);
        list.addAll(list2);
        JSONObject jsonObject = JSONObject.parseObject("{\"test\":::\"test123\"}");
        //{"data":[{"agencyid":"spidb9f0141753","agencyname":"上饶县巨网科技有限公司","role":2,"username":"郝刚峰","wx_id":"s18042016047"}],"result":0,"ret":0}
        JSONArray jsonArray = jsonObject.getJSONArray("data");


        final AtomicTest atomicTest = new AtomicTest();
        final String userid = "123";
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 1000; i++) {
                new Thread(() -> {
                    if (!concurrentHashMap.containsKey(userid)) {
                        concurrentHashMap.put(userid, new Object());
                    } else {
                    }
                    atomicTest.exe();
                }).start();
            }
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println(atomicTest.count);
    }

    public void exe() {
//        atomicInteger.incrementAndGet();
//        if(atomicInteger.get()==1){
//            count++;
//        }else{
        //synchronized (lockObj){
        count = count + 1;
        //}
//        }
//        atomicInteger.decrementAndGet();
    }

    @Data
    @AllArgsConstructor
    public class person {
        public String userid;

    }
}
