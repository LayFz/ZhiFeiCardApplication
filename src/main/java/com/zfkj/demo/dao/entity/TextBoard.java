package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("textboard")
public class TextBoard extends BaseEntity {
    /**
     * 姓名
     */
    private String nickname;
    /**
     * 性别
     */
    private String sex;
    /**
     * 留言信息
     */
    private String content;
    /**
     * 是否公开
     */
    private String ispublic;
    /**
     * 留言回复信息
     */
    private String rescontent;
    /**
     * 属于谁的id
     */
    private Integer belong_id;
}
