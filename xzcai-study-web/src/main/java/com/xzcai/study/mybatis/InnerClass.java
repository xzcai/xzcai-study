package com.xzcai.study.mybatis;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.*;

/**
 * @author: created by mifang
 * @create: 2018-11-08
 **/
@Data
public class InnerClass implements Serializable {
    private String name;
    private Integer age;

    public String getStr() throws IOException, ClassNotFoundException {
        Student student = new Student();
        student.setTearcherName("asfd");
        student.setStudNum(123123);

        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/Person.txt")));
        oo.writeObject(student);
        System.out.println("Person对象序列化成功！");
        oo.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("E:/Person.txt")));
        Student person = (Student) ois.readObject();
        System.out.println("Person对象反序列化成功！");

        return JSONObject.toJSONString(student);
    }

    @Data
    public static class Student implements Serializable {
        private Integer studNum;
        private String tearcherName;
    }
}
