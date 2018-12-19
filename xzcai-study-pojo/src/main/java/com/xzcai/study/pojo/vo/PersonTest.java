package com.xzcai.study.pojo.vo;

import com.xzcai.study.common.dateVali.DateTime;
import lombok.Data;

/**
 * @author: created by mifang
 * @create: 2018-11-13
 **/
@Data
public class PersonTest {
    @DateTime(format = "yyyyMMdd",message = "时间格式错误")
    private String endTime;
}
