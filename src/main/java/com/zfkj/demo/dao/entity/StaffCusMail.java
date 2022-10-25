package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("staff_customer_mail")
public class StaffCusMail {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer staffId;
    private Integer customerId;
    private LocalDateTime mailTime;
}
