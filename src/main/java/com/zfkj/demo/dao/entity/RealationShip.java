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
 * @date 2022/10/10 19:17
 */
@Data
@Builder
@TableName("user_customer")
public class RealationShip {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private int user_id;
    private int cus_id;
    private int interaction_num;
}
