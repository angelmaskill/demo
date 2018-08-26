package com.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 参考:http://blog.csdn.net/sunglee_1992/article/details/53033644
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/1/22 17:30
 * @Modified By:
 */
public class ExcelUtil {

    private String val = null;
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); //日期格式yyyy-mm-dd
    private DecimalFormat df = new DecimalFormat("0");
    //数字格式，防止长数字成为科学计数法形式，或者int变为double形式
    List<ArrayList<String>> strLists = new ArrayList<ArrayList<String>>();  //存放Excel中的数据

    /**
     * @return List<ArrayList<String>>
     * @throws FileNotFoundException,IOException,InvalidFormatException
     * POI方式解析EXCEL表格(2007以上版本，xlsx)
     * @author: LS
     * 2016-8-27 下午7:04:26
     */
    public List<ArrayList<String>> poiReadXExcel(String file) throws FileNotFoundException,
        IOException, InvalidFormatException {
        FileInputStream input = new FileInputStream(new File(file)); //读取的文件路径
        XSSFWorkbook wb = new XSSFWorkbook(new BufferedInputStream(input));
        XSSFSheet sheet = wb.getSheetAt(0); //获取第一张表

        int rowNum = sheet.getPhysicalNumberOfRows();//得到数据的行数
        System.out.println("行数：" + rowNum);
        strLists.clear();

        //遍历行
        for (int i = 0; i < rowNum; i++) {
            List<String> strList = new ArrayList<String>();
            XSSFRow row = sheet.getRow(i);
            int colNum = row.getPhysicalNumberOfCells();//得到当前行中存在数据的列数
            //遍历列
            for (int j = 0; j < colNum; j++) {
                XSSFCell cell = row.getCell(j);
                strList.add(getXCellVal(cell));
            }
            strLists.add(i, (ArrayList<String>)strList); //存储该行
        }

        //打印
        for (ArrayList<String> stringList : strLists) {
            for (String str : stringList) {
                System.out.print(str + "  ");
            }
            System.out.println();
        }
        wb.close();
        return strLists;
    }

    /**
     * @param cell
     * @return String
     * 获取单元格中的值
     * @author: LS
     * 2016-8-25 下午7:40:05
     */
    private String getXCellVal(XSSFCell cell) {
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    val = fmt.format(cell.getDateCellValue()); //日期型
                } else {
                    val = df.format(cell.getNumericCellValue()); //数字型
                }
                break;
            case XSSFCell.CELL_TYPE_STRING: //文本类型
                val = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN: //布尔型
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK: //空白
                val = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_ERROR: //错误
                val = "错误";
                break;
            case XSSFCell.CELL_TYPE_FORMULA: //公式
                try {
                    val = String.valueOf(cell.getStringCellValue());
                } catch (IllegalStateException e) {
                    val = String.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                val = cell.getRichStringCellValue() == null ? null
                    : cell.getRichStringCellValue().toString();
        }
        return val;
    }
}