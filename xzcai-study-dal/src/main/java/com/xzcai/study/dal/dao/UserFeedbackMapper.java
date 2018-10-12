package com.xzcai.study.dal.dao;

import com.xzcai.study.pojo.po.UserFeedbackPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Mr.Wang
 * @create: 2018-09-29
 **/
@Mapper
public interface UserFeedbackMapper {
    int save(UserFeedbackPO userFeedbackPO);
}
