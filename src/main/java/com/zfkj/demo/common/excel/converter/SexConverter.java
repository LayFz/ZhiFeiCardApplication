package com.zfkj.demo.common.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.zfkj.demo.common.enums.SexEnum;

/**
 * @Author lijunlin
 * @Description excel导入性别枚举转换
 * @Date 2022/7/7 16:26
 **/
public class SexConverter implements Converter<SexEnum> {

    @Override
    public Class<?> supportJavaTypeKey() {
        //对象属性类型
        return SexEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //CellData属性类型
        return CellDataTypeEnum.STRING;
    }

    @Override
    public SexEnum convertToJavaData(ReadConverterContext<?> context) {
        //CellData转对象属性
        String cellStr = context.getReadCellData().getStringValue();
        if (cellStr.equals(SexEnum.MAN.getName())) {
            return SexEnum.MAN;
        } else {
            return SexEnum.WOMAN;
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<SexEnum> context) {
        //对象属性转CellData
        SexEnum cellValue = context.getValue();
        if (cellValue == null) {
            return new WriteCellData<>("");
        }
        if (SexEnum.MAN.equals(cellValue)) {
            return new WriteCellData<>(SexEnum.MAN.getName());
        } else {
            return new WriteCellData<>(SexEnum.WOMAN.getName());
        }
    }

}
