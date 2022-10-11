package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("cus_date")
public class CustomerDate {
    private Long id;
    @TableField("cus_id")
    private Integer cusId;
    @TableField("visit_num")
    private Integer visit_num;
    @TableField("save_card_num")
    private Integer save_card_num;
    @TableField("save_message_num")
    private Integer save_message_num;
    @TableField("forward_num")
    private Integer forward_num;
    @TableField("textboard_num")
    private Integer textboard_num;
    @TableField("card_id")
    private Integer card_id;
}
