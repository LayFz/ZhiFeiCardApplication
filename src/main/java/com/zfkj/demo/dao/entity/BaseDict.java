package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.*;

/**
 * <p>
 * 基础字典信息表
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_base_dict")
public class BaseDict extends BaseEntity {

    /**
     * 父主键
     */
    private Long parentId;

    /**
     * 字典标识
     */
    private String identifying;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字典备注
     */
    private String remark;

    /**
     * 是否允许编辑 YES-是、NO否
     */
    private YesNoEnum isEdit;

    /**
     * 是否删除，YES-是、NO-否
     */
    @TableLogic(value = "'NO'", delval = "'YES'")
    @TableField(fill = FieldFill.INSERT)
    private YesNoEnum delFlag;

}
