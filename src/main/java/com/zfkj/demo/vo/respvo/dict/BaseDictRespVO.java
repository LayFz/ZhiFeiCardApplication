package com.zfkj.demo.vo.respvo.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.vo.basevo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 基础字典信息表
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@Data
public class BaseDictRespVO {
    @ApiModelProperty(value = "id",example = "1")
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
    @ApiModelProperty(value = "创建人",example = "张三")
    private String createUser;
    @ApiModelProperty(value = "创建时间",example = "2019-01-01 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "修改人",example = "张三")
    private String updateUser;
    @ApiModelProperty(value = "修改时间",example = "2019-01-01 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
