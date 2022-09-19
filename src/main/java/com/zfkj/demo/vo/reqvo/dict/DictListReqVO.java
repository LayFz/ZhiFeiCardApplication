package com.zfkj.demo.vo.reqvo.dict;

import com.zfkj.demo.vo.basevo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 基础字典信息表
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@Data
@ToString(callSuper = true)
public class DictListReqVO extends PageVO {

    @ApiModelProperty(value = "父id",example = "1")
    private Long parentId;

    @ApiModelProperty(value = "字典名称",example = "性别")
    private String name;

}
