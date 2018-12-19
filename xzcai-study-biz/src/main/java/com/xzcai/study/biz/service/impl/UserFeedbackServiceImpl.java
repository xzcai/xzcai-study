package com.xzcai.study.biz.service.impl;

import com.xzcai.study.biz.service.UserFeedbackService;
import com.xzcai.study.dal.dao.UserFeedbackMapper;
import com.xzcai.study.pojo.po.UserFeedbackPO;
import com.xzcai.study.pojo.vo.UserFeedbackSaveVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ywwl.common.util.JedisUtil;

/**
 * @author: Mr.Wang
 * @create: 2018-09-29
 **/
@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {
    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    @Autowired

    private JedisUtil jedisUtil;

    @Override
    public int save(UserFeedbackSaveVO userFeedbackSaveVO) {
        UserFeedbackPO userFeedbackPO=new UserFeedbackPO();
        BeanUtils.copyProperties(userFeedbackSaveVO,userFeedbackPO);
        userFeedbackMapper.save(userFeedbackPO);
        return 1;
    }
}
