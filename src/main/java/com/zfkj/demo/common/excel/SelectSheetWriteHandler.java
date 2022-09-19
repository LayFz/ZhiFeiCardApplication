package com.zfkj.demo.common.excel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

/**
 * @Author lijunlin
 * @Description excel导出下拉框类型处理器
 * @Date 2022/7/7 15:16
 **/
public class SelectSheetWriteHandler implements SheetWriteHandler {

    private Map<Integer, String[]> selectMap;

    public SelectSheetWriteHandler(Map<Integer, String[]> selectMap) {
        this.selectMap = selectMap;
    }

    /**
     * 多少行有下拉
     */
    private final static Integer rowSize = 65533;

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        if (selectMap == null || selectMap.size() == 0) {
            return;
        }
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();

        selectMap.forEach((celIndex, strings) -> {
            // 区间设置
            CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, rowSize, celIndex, celIndex);
            // 下拉内容
            DataValidationConstraint constraint = helper.createExplicitListConstraint(strings);
            DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
            sheet.addValidationData(dataValidation);
        });
    }

}
