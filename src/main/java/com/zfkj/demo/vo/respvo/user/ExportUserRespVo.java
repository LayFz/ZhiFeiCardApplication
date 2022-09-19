package com.zfkj.demo.vo.respvo.user;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.SexEnum;
import com.zfkj.demo.common.excel.converter.OpenCloseConverter;
import com.zfkj.demo.common.excel.converter.SexConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lijunlin
 * @description: 导入用户VO
 * @create: 2019-12-27 15:03
 **/
@Data
public class ExportUserRespVo {

    @ExcelProperty("姓名")
    @ColumnWidth(20)
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;

    @ExcelProperty("账号")
    @ColumnWidth(20)
    @ApiModelProperty(value = "账号", example = "admin")
    private String account;

    @ExcelProperty("电话")
    @ColumnWidth(20)
    @ApiModelProperty(value = "电话", example = "13555555555")
    private String phone;

    @ExcelProperty("年龄")
    @ColumnWidth(20)
    @ApiModelProperty(value = "年龄", example = "18")
    private Integer age;

    @ExcelProperty(value = "性别",converter = SexConverter.class)
    @ColumnWidth(20)
    @ApiModelProperty(value = "性别：MAN-男,WOMAN-女", example = "MAN")
    private SexEnum sex;

    @ExcelProperty(value = "状态",converter = OpenCloseConverter.class)
    @ColumnWidth(20)
    @ApiModelProperty(value = "状态：OPEN-启用,CLOSE-禁用", example = "OPEN")
    private OpenCloseEnum status;

    @ExcelProperty("详细地址")
    @ColumnWidth(40)
    @ApiModelProperty(value = "详细地址", example = "xxxxx")
    private String address;

    @ExcelProperty("备注")
    @ColumnWidth(40)
    @ApiModelProperty(value = "备注", example = "这个人很懒,什么都没留下")
    private String remark;

}
