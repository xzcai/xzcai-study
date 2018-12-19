package com.xzcai.study.controller;

import com.xzcai.study.biz.service.UserFeedbackService;
import com.xzcai.study.pojo.vo.PersonTest;
import com.xzcai.study.pojo.vo.UserFeedbackSaveVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author: Mr.Wang
 * @create: 2018-09-29
 **/
@RestController
@RequestMapping("/user/feedback")
public class UserFeedbackController {
    private static final Logger logger = LoggerFactory.getLogger(UserFeedbackController.class);

    @Autowired
    UserFeedbackService userFeedbackService;

    @PostMapping("/save")
    public String save(@RequestBody UserFeedbackSaveVO userFeedbackSaveVO){
        logger.info("test");
        userFeedbackService.save(userFeedbackSaveVO);
        return null;
    }

    @GetMapping("/get/{ids}")
    public String get(@PathVariable int[] ids){
        return String.valueOf(ids[0]+ids[1]);
    }

    @GetMapping("/test")
    public String get(){
        logger.info("test");
        return "tets";
    }

    @PostMapping("/testVali")
    public String testVali(@Validated @RequestBody PersonTest user, BindingResult br){
//        if (br.hasErrors()) {
//            return br.getFieldError().getDefaultMessage();
//        } else {
//            return "chengg";
//        }
        return "sdfasdf";
    }
}
