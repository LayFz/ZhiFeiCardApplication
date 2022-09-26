package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/26 16:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("organization")
public class Organize {
    @TableId(type = IdType.AUTO)
    private Long id;
    private int companyId;
    private int childId;
    @TableField("nickname")
    private String nickName;
}
