package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;

import lombok.*;


/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:10
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("textboard")

public class TextBoard extends BaseEntity {
    /**
     * 姓名
     */
    @TableField("nickname")
    private String nickname;
    /**
     * 性别
     */
    @TableField("sex")
    private String sex;
    /**
     * 留言信息
     */
    @TableField("content")
    private String content;
    /**
     * 是否公开
     */
    @TableField("ispublic")
    private String ispublic;

    /**
     * 留言回复信息
     */
    @TableField("rescontent")
    private String rescontent;


    private Integer belongId;
}
