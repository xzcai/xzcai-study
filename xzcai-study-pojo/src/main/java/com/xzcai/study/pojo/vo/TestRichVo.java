package com.xzcai.study.pojo.vo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Mr.Wang
 * @create: 2018-10-07
 **/
@Data
public class TestRichVo {
    private String appId;
    private String taskName;
    private String interactType;
    private String interactTypeInfo;
    private int delayTime;
    private List<RichText> richTexts;
}
