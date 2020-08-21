package com.zkk.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.Cell;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/18 13:54
 */
@Component
public class CellValueUtils {

    public static String getStringCellValue(Cell cell){
        if (Objects.isNull(cell)) {
            return "";
        }

        String value = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                //如果为时间格式的内容
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }
}
