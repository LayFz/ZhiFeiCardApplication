package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件信息表
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseAttachment extends BaseEntity {

    /**
     * 上传后文件名
     */
    private String name;

    /**
     * 文件真实名称
     */
    private String realName;

    /**
     * 文件绝对路径
     */
    private String path;

    /**
     * url相对路径
     */
    private String url;
    /**
     * 文件大小 B
     */
    private Long size;
    /**
     * 后缀
     */
    private String suff;

    /**
     * 是否删除
     */
    @TableField(fill = FieldFill.INSERT)
    private YesNoEnum delFlag;
}
