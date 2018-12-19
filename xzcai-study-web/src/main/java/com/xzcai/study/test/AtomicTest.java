package com.xzcai.study.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.plaf.ViewportUI;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Mr.Wang
 * @create: 2018-10-25
 **/
public class AtomicTest {
    public int count = 0;
    private static final Object lockObj = new Object();
    AtomicInteger atomicInteger = new AtomicInteger();

    private static String calculateIntervalTime(int beginHour, int endHour) {
        String[] e = new String[48];
        beginHour = 2 * beginHour;
        endHour = 2 * endHour;
        for (int n = 0; 48 > n; ++n) e[n] = beginHour > n || n >= endHour ? "0" : "1";
        String str = String.join("", e);
        return str + str + str + str + str + str + str;
    }

    public static void main(String[] args) throws InterruptedException {
        retryTest();

        System.out.println("end...");
    }

    public static Boolean retryTest() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.equalTo(false)) // 返回false时重试
                .retryIfExceptionOfType(IOException.class) // 抛出IOException时重试
                .withWaitStrategy(WaitStrategies.fixedWait(2000, TimeUnit.MILLISECONDS)) // 200ms后重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) // 重试3次后停止
                .build();
        try {
            return retryer.call(new Callable<Boolean>() {

                @Override
                public Boolean call() throws Exception {
                    return doSomething();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean doSomething() {
        return false;
    }
}
