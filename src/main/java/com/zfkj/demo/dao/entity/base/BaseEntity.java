package com.zfkj.demo.dao.entity.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 基础实体
 *
 * @author lijunlin
 * @date 2022/1/21 13:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateId;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
