package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("user_customer")
public class StaffCustomer {

    @TableId(type = IdType.AUTO)
    private Long id;
    //员工id
    @TableField("user_id")
    private Integer user_id;
    //客户id
    @TableField("cus_id")
    private Integer cus_id;
    //活跃数
    @TableField("interaction_num")
    private int interaction_num;
}
