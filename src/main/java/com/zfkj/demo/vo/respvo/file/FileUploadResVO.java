package com.zfkj.demo.vo.respvo.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zfkj.demo.dao.entity.BaseAttachment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件上传返回实体
 *
 * @author lijunlin
 * @date 2022/1/27 17:16
 * @see BaseAttachment
 */
@Data
public class FileUploadResVO {
    @ApiModelProperty(value = "id",example = "1")
    private Long id;
    @ApiModelProperty(value = "上传后文件名",example = "上传后文件名")
    @JsonIgnore
    private String name;
    @ApiModelProperty(value = "文件真实名称",example = "文件真实名称")
    private String realName;
    @ApiModelProperty(value = "文件绝对路径",example = "文件绝对路径")
    @JsonIgnore
    private String path;
    @ApiModelProperty(value = "url相对路径",example = "url相对路径")
    private String url;
    @ApiModelProperty(value = "文件大小 B",example = "文件大小 B")
    private Long size;
    @ApiModelProperty(value = "后缀",example = "后缀")
    private String suff;
}
