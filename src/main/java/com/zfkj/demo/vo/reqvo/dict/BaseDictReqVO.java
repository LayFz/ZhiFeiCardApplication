package com.zfkj.demo.vo.reqvo.dict;

import com.zfkj.demo.common.enums.YesNoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 基础字典信息表
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@Data
public class BaseDictReqVO {
    @ApiModelProperty(value = "ID,新增不传，编辑传",example = "1")
    private Long id;
    @ApiModelProperty(value = "父主键",example = "1")
    private Long parentId;
    @ApiModelProperty(value = "字典标识",example = "sex")
    private String identifying;
    @ApiModelProperty(value = "字典编码",example = "man")
    private String code;
    @ApiModelProperty(value = "字典名称",example = "男")
    private String name;
    @ApiModelProperty(value = "是否允许编辑 YES-是、NO否",example = "YES")
    private YesNoEnum isEdit;
    @ApiModelProperty(value = "排序",example = "1")
    private Integer sort;
    @ApiModelProperty(value = "字典备注",example = "remark")
    private String remark;
}
