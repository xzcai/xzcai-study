package com.xzcai.study.pojo.po;
import lombok.Data;

import java.util.Date;

/**
 * @author: Mr.Wang
 * @create: 2018-09-30
 **/
@Data
public class UserFeedbackPO {
    /**
     * 唯一di
     */
    private String pkId;

    /**
     * 问题类型
     */
    private String type;

    /**
     * 问题截图
     */
    private String imgs;

    /**
     * 问题描述
     */
    private String content;

    /**
     * 反馈者-号码
     */
    private String phone;

    /**
     * 处理人id
     */
    private String auditUserId;

    /**
     * 反馈-状态
     */
    private String status;

    /**
     *
     */
    private String handleContent;


    /**
     * 反馈-状态
     */
    private String createdUser;

    /**
     * 修改者
     */
    private String modifiedUser;

    /**
     * 是否已删除, 0:未删除, 1
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
