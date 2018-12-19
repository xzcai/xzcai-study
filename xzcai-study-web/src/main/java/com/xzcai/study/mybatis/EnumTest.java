package com.xzcai.study.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author: created by mifang
 * @create: 2018-11-14
 **/
@Getter
@AllArgsConstructor
public enum EnumTest {
    Text(1,"文办"),
    View(2,"师徒");
    private Integer code;
    private String desc;
}
