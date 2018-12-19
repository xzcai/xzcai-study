package com.xzcai.study.biz.service.impl;

import com.xzcai.study.biz.service.UserFeedbackService;
import com.xzcai.study.pojo.vo.UserFeedbackSaveVO;
import com.ywwl.common.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringRunner.class)
public class UserFeedbackServiceImplTest {

    @Mock
    JedisUtil jedisUtil;

    @InjectMocks
    UserFeedbackServiceImpl userFeedbackService;

    @Test
    public void save() {
        UserFeedbackSaveVO userFeedbackSaveVO=new UserFeedbackSaveVO();
        userFeedbackService.save(userFeedbackSaveVO);
    }
}