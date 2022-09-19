package com.zfkj.demo.common.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.zfkj.demo.common.enums.OpenCloseEnum;

/**
 * @Author lijunlin
 * @Description excel导入用户状态枚举转换
 * @Date 2022/7/7 16:26
 **/
public class OpenCloseConverter implements Converter<OpenCloseEnum> {

    @Override
    public Class<?> supportJavaTypeKey() {
        //对象属性类型
        return OpenCloseEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //CellData属性类型
        return CellDataTypeEnum.STRING;
    }

    @Override
    public OpenCloseEnum convertToJavaData(ReadConverterContext<?> context) {
        //CellData转对象属性
        String cellStr = context.getReadCellData().getStringValue();
        if (cellStr.equals(OpenCloseEnum.OPEN.getName())) {
            return OpenCloseEnum.OPEN;
        } else {
            return OpenCloseEnum.CLOSE;
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<OpenCloseEnum> context) {
        //对象属性转CellData
        OpenCloseEnum cellValue = context.getValue();
        if (cellValue == null) {
            return new WriteCellData<>("");
        }
        if (OpenCloseEnum.OPEN.equals(cellValue)) {
            return new WriteCellData<>(OpenCloseEnum.OPEN.getName());
        } else {
            return new WriteCellData<>(OpenCloseEnum.CLOSE.getName());
        }
    }
}
