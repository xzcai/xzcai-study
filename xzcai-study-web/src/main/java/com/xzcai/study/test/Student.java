package com.xzcai.study.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: created by mifang
 * @create: 2019-02-26
 **/
@Data
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }
}
