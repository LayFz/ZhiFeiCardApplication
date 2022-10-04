package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("card_date")
public class CardDate {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer cardId;
    private int viewNumber;
    private int cusNumber;
    private int activeNumber;
    private int messageNumber;
    private int saveNumber;
    private int mailNumber;
    private int childId;
    private Integer userId;
}
