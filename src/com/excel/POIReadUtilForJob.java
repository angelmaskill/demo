package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/1/22 16:45
 * @Modified By:
 */
public class POIReadUtilForJob {
    // 保存两位
    private DecimalFormat df_2wei = new DecimalFormat("0.00");
    // 保存四位
    private DecimalFormat df_4wei = new DecimalFormat("0.0000");
    // 保存整数
    private DecimalFormat df_int = new DecimalFormat("#");

    /**
     * 总行数
     */
    private int totalRows = 0;
    /**
     * 总列数
     */
    private int totalCells = 0;
    /**
     * 错误信息
     */
    private String errorInfo;

    /**
     * 构造方法
     */
    public POIReadUtilForJob() {
    }

    /**
     * @描述：得到总行数
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:27:15
     * @参数：@return
     * @返回值：int
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * @描述：得到总列数
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:27:15
     * @参数：@return
     * @返回值：int
     */
    public int getTotalCells() {
        return totalCells;
    }

    /**
     * @描述：得到错误信息
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:27:15
     * @参数：@return
     * @返回值：String
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * @描述：验证excel文件
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:27:15
     * @参数：@param filePath　文件完整路径
     * @参数：@return
     * @返回值：boolean
     */
    public boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null || !(
            WDWUtil2.isExcel2003(filePath) || WDWUtil2.isExcel2007(filePath))) {
            errorInfo = "文件名不是excel格式";
            return false;
        }
        /** 检查文件是否存在 */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            errorInfo = "文件不存在";
            return false;
        }
        return true;
    }

    public static Date getDate(String dateString) throws ParseException {
        if (null == dateString || "".equals(dateString)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(dateString);
        return date;
    }

    private static Long changY2f(String yuan) {
        return new Money(yuan).getCent();
    }

    private static String formattedDecimalToPercentage(double decimal) {
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(3);
        return nt.format(decimal);
    }

    /**
     * @描述：根据文件名读取excel文件
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:27:15
     * @参数：@param filePath 文件完整路径
     * @参数：@return
     * @返回值：List
     */
    public List<List<String>> read(String filePath) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        InputStream is = null;
        try {
            /** 验证文件是否合法 */
            if (!validateExcel(filePath)) {
                System.out.println(errorInfo);
                return null;
            }
            /** 判断文件的类型，是2003还是2007 */
            boolean isExcel2003 = true;
            if (WDWUtil2.isExcel2007(filePath)) {
                isExcel2003 = false;
            }
            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, isExcel2003);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        /** 返回最后读取的结果 */
        return dataLst;
    }

    /**
     * @描述：根据流读取Excel文件
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:40:15
     * @参数：@param inputStream
     * @参数：@param isExcel2003
     * @参数：@return
     * @返回值：List
     */
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataLst = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = read(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataLst;
    }

    /**
     * @描述：读取数据
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:50:15
     * @参数：@param Workbook
     * @参数：@return
     * @返回值：List<List<String>>
     */
    private List<List<String>> read(Workbook wb) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(0);
        /** 得到Excel的行数 */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行 */
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                            if (c == 1 || c == 2 || c == 11) {
                                cellValue = df_int.format(cell.getNumericCellValue());
                            } else if (c == 5 || c == 6) {
                                //cellValue = df_4wei.format(cell.getNumericCellValue());
                                cellValue = formattedDecimalToPercentage(
                                    cell.getNumericCellValue());
                            } else {
                                cellValue = df_2wei.format(cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
                rowLst.add(cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
        return dataLst;
    }

    /**
     * @描述：main测试方法
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午17:12:15
     * @参数：@param args
     * @参数：@throws Exception
     * @返回值：void
     */
    public static void main(String[] args) throws Exception {
        POIReadUtilForJob poi = new POIReadUtilForJob();
        List<List<String>> list = poi.read(
            "C:\\Users\\yanlu.myl\\Desktop\\temp\\2018年第二批ka结算(3).xlsx");
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                System.out.print("第" + (i) + "行\t\t");
                List<String> cellList = list.get(i);
                YearSettleResultDTO dto = new YearSettleResultDTO();
                dto.setSettleYear("2017");
                for (int j = 0; j < cellList.size(); j++) {
                    /* 打印读取表格中的数据 */
                    //System.out.println("    第" + (j + 1) + "列值：" + cellList.get(j));
                    if ((j + 1) == 1) {
                    } else if ((j + 1) == 2) {
                        dto.setBailOrderId(Long.valueOf(cellList.get(j)));
                    } else if ((j + 1) == 3) {
                        dto.setUserId(Long.valueOf(cellList.get(j)));
                    } else if ((j + 1) == 4) {
                    } else if ((j + 1) == 5) {
                    } else if ((j + 1) == 6) {
                    } else if ((j + 1) == 7) {
                    } else if ((j + 1) == 8) {
                        dto.setPrePaySoftWareFee(changY2f(cellList.get(j)));//总预缴
                    } else if ((j + 1) == 9) {
                    } else if ((j + 1) == 10) {
                        dto.setBaseFee(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 11) {
                        dto.setCunTaoBaseFee(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 12) {
                        boolean isfinish = Long
                            .valueOf(cellList.get(j)) == 0L ? false : true;
                        dto.setFinishBaseFee(isfinish);
                    } else if ((j + 1) == 13) {
                        dto.setRealTimeTecFee(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 14) {
                        dto.setCunTaoRealTimeSoftWareFee(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 15) {
                        dto.setReceiveSoftWareFee(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 16) {
                        dto.setRefundAmount(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 17) {
                        dto.setHasRefundAmountTechFee(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 18) {
                        dto.setTechFeeRefundTime(getDate(cellList.get(j)));
                    } else if ((j + 1) == 19) {
                        dto.setHasRefundAmountCommission(changY2f(cellList.get(j)));
                    } else if ((j + 1) == 20) {
                        dto.setCommissionRefundTime(getDate(cellList.get(j)));
                    } else if ((j + 1) == 21) {
                        dto.setBackAmount(changY2f(cellList.get(j)));
                    }
                }
                dto.setRealTimeSoftWareFee(
                    dto.getCunTaoRealTimeSoftWareFee() + dto.getRealTimeTecFee());

                /* 打印序列化之后的对象字符串 */
                //System.out.println(JSON.toJSONString(dto));

                /* 测试反序列化是否能成功 */
                //YearSettleResultDTO yearSettleResultDTO = JSON.parseObject(JSON.toJSONString(dto),
                //    YearSettleResultDTO.class);
                //System.out.println(yearSettleResultDTO.toString());

                /* 输出sql语句 */
                StringBuffer sb = new StringBuffer();
                sb.append(
                    "insert into hj_bail_ext(gmt_create,  gmt_modified,  bail_order_id,  user_id,"
                        + "  name,  code,  value,  version,  status) values(now(),now(),"
                        + dto.getBailOrderId() + "," + dto.getUserId()
                        + ",'年结结果','yearsettleresult','" + JSON
                        .toJSONString(dto) + "',0,1);");
                System.out.println(sb);
            }
        }
    }
}

/**
 * @描述：工具类
 * @作者：yanlu.myl
 * @时间：2018/1/22 下午16:30:40
 */
class WDWUtil2 {
    /**
     * @描述：是否是2003的excel，返回true是2003
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:29:11
     * @参数：@param filePath　文件完整路径
     * @参数：@return
     * @返回值：boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @描述：是否是2007的excel，返回true是2007
     * @作者：yanlu.myl
     * @时间：2018/1/22 下午16:28:20
     * @参数：@param filePath　文件完整路径
     * @参数：@return
     * @返回值：boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

class Money implements Serializable, Comparable {
    private static final long serialVersionUID = 3761410806910104373L;

    /**
     * 缺省的币种代码，为CNY（人民币）。
     */
    public static final String DEFAULT_CURRENCY_CODE = "CNY";

    /**
     * 缺省的取整模式，为<code>BigDecimal.ROUND_HALF_EVEN
     * （四舍五入，当小数为0.5时，则取最近的偶数）。
     */
    public static final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    /**
     * 一组可能的元/分换算比例。
     *
     * <p>
     * 此处，“分”是指货币的最小单位，“元”是货币的最常用单位，
     * 不同的币种有不同的元/分换算比例，如人民币是100，而日元为1。
     */
    private static final int[] centFactors = new int[] {1, 10, 100, 1000};

    /**
     * 默認的地區
     */
    private static final String DEFAULT_LOCALE = "zh_CN";
    /**
     * 保存货币单位的map，用来显示货币单位，如元，美元等
     */
    protected static Map<String, Map<String, String>> CURRENCY_DISPLAY_UNIT_MAP
        = new HashMap<String, Map<String, String>>();

    //进行初始化工作
    static {
        //簡體中文的對應錶
        Map<String, String> zhCNMap = new HashMap<String, String>();
        zhCNMap.put("CNY", "元");
        zhCNMap.put("POINT", "积分");
        CURRENCY_DISPLAY_UNIT_MAP.put("zh_CN", zhCNMap);

        //繁體的對應錶
        Map<String, String> zhHKMap = new HashMap<String, String>();
        zhHKMap.put("CNY", "元");
        zhHKMap.put("POINT", "積分");
        CURRENCY_DISPLAY_UNIT_MAP.put("zh_HK", zhHKMap);
    }

    /**
     * 必须定义在后面(依赖前面的静态变量)
     */
    public static final Money ZERO = new Money(0);

    /**
     * 金额，以分为单位。
     */
    private long cent;

    /**
     * 币种。
     */
    private Currency currency;

    // 构造器 ====================================================

    /**
     * 缺省构造器。
     *
     * <p>
     * 创建一个具有缺省金额（0）和缺省币种的货币对象。
     */
    public Money() {
        this(0l);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>yuan</code>元<code>cent</cent>分和缺省币种的货币对象。
     *
     * @param yuan 金额元数。
     * @param cent 金额分数。
     */
    public Money(long yuan, int cent) {
        this(yuan, cent, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>yuan</code>元<code>cent</cent>分和缺省币种的货币对象。
     *
     * @param cent 金额分数。
     */
    public Money(long cent) {
        this.currency = Currency.getInstance(DEFAULT_CURRENCY_CODE);
        this.cent = cent;
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>yuan</code>元<code>cent</code>分和指定币种的货币对象。
     *
     * @param yuan     金额元数。
     * @param cent     金额分数。
     * @param currency
     */
    public Money(long yuan, int cent, Currency currency) {
        this.currency = currency;

        this.cent = (yuan * getCentFactor()) + (cent % getCentFactor());
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>元和缺省币种的货币对象。
     *
     * @param amount 金额，以元为单位。
     */
    public Money(String amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>元和指定币种<code>currency</code>的货币对象。
     *
     * @param amount   金额，以元为单位。
     * @param currency 币种。
     */
    public Money(String amount, Currency currency) {
        this(new BigDecimal(amount), currency);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>元和指定币种<code>currency</code>的货币对象。
     * 如果金额不能转换为整数分，则使用指定的取整模式<code>roundingMode</code>取整。
     *
     * @param amount       金额，以元为单位。
     * @param currency     币种。
     * @param roundingMode 取整模式。
     */
    public Money(String amount, Currency currency, int roundingMode) {
        this(new BigDecimal(amount), currency, roundingMode);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有参数<code>amount</code>指定金额和缺省币种的货币对象。
     * 如果金额不能转换为整数分，则使用四舍五入方式取整。
     *
     * <p>
     * 注意：由于double类型运算中存在误差，使用四舍五入方式取整的
     * 结果并不确定，因此，应尽量避免使用double类型创建货币类型。
     * 例：
     * <code>
     * assertEquals(999, Math.round(9.995 * 100));
     * assertEquals(1000, Math.round(999.5));
     * money = new Money((9.995));
     * assertEquals(999, money.getCent());
     * money = new Money(10.005);
     * assertEquals(1001, money.getCent());
     * </code>
     *
     * @param amount 金额，以元为单位。
     */
    public Money(double amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和指定币种的货币对象。
     * 如果金额不能转换为整数分，则使用四舍五入方式取整。
     *
     * <p>
     * 注意：由于double类型运算中存在误差，使用四舍五入方式取整的
     * 结果并不确定，因此，应尽量避免使用double类型创建货币类型。
     * 例：
     * <code>
     * assertEquals(999, Math.round(9.995 * 100));
     * assertEquals(1000, Math.round(999.5));
     * money = new Money((9.995));
     * assertEquals(999, money.getCent());
     * money = new Money(10.005);
     * assertEquals(1001, money.getCent());
     * </code>
     *
     * @param amount   金额，以元为单位。
     * @param currency 币种。
     */
    public Money(double amount, Currency currency) {
        this.currency = currency;
        this.cent = Math.round(amount * getCentFactor());
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和缺省币种的货币对象。
     * 如果金额不能转换为整数分，则使用缺省取整模式<code>DEFAULT_ROUNDING_MODE</code>取整。
     *
     * @param amount 金额，以元为单位。
     */
    public Money(BigDecimal amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有参数<code>amount</code>指定金额和缺省币种的货币对象。
     * 如果金额不能转换为整数分，则使用指定的取整模式<code>roundingMode</code>取整。
     *
     * @param amount       金额，以元为单位。
     * @param roundingMode 取整模式
     */
    public Money(BigDecimal amount, int roundingMode) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE), roundingMode);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和指定币种的货币对象。
     * 如果金额不能转换为整数分，则使用缺省的取整模式<code>DEFAULT_ROUNDING_MODE</code>进行取整。
     *
     * @param amount   金额，以元为单位。
     * @param currency 币种
     */
    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和指定币种的货币对象。
     * 如果金额不能转换为整数分，则使用指定的取整模式<code>roundingMode</code>取整。
     *
     * @param amount       金额，以元为单位。
     * @param currency     币种。
     * @param roundingMode 取整模式。
     */
    public Money(BigDecimal amount, Currency currency, int roundingMode) {
        this.currency = currency;
        this.cent = rounding(amount.movePointRight(currency.getDefaultFractionDigits()),
            roundingMode);
    }

    // Bean方法 ====================================================

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以元为单位。
     */
    public BigDecimal getAmount() {
        return BigDecimal.valueOf(this.cent, this.currency.getDefaultFractionDigits());
    }

    /**
     * 设置本货币对象代表的金额数。
     *
     * @param amount 金额数，以元为单位。
     */
    public void setAmount(BigDecimal amount) {
        if (amount != null) {
            this.cent = rounding(amount.movePointRight(2), BigDecimal.ROUND_HALF_EVEN);
        }
    }

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以分为单位。
     */
    public long getCent() {
        return this.cent;
    }

    /**
     * 获取本货币对象代表的币种。
     *
     * @return 本货币对象所代表的币种。
     * @deprecated use getCurrencyCode()
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * 获取本货币对象代表的币种代码
     *
     * @return 币种代码
     */
    public String getCurrencyCode() {
        return this.currency.getCurrencyCode();
    }

    /**
     * 获取本货币币种的元/分换算比率。
     *
     * @return 本货币币种的元/分换算比率。
     */
    public int getCentFactor() {
        return centFactors[this.currency.getDefaultFractionDigits()];
    }

    // 基本对象方法 ===================================================

    /**
     * 判断本货币对象与另一对象是否相等。
     *
     * <p>
     * 本货币对象与另一对象相等的充分必要条件是：<br>
     * <ul>
     * <li>另一对象也属货币对象类。
     * <li>金额相同。
     * <li>币种相同。
     * </ul>
     *
     * @param other 待比较的另一对象。
     * @return <code>true</code>表示相等，<code>false</code>表示不相等。
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        return (other instanceof Money) && equals((Money)other);
    }

    /**
     * 判断本货币对象与另一货币对象是否相等。
     *
     * <p>
     * 本货币对象与另一货币对象相等的充分必要条件是：<br>
     * <ul>
     * <li>金额相同。
     * <li>币种相同。
     * </ul>
     *
     * @param other 待比较的另一货币对象。
     * @return <code>true</code>表示相等，<code>false</code>表示不相等。
     */
    public boolean equals(Money other) {
        return this.currency.equals(other.currency) && (this.cent == other.cent);
    }

    /**
     * 计算本货币对象的杂凑值。
     *
     * @return 本货币对象的杂凑值。
     * @see Object#hashCode()
     */
    public int hashCode() {
        return (int)(this.cent ^ (this.cent >>> 32));
    }

    // Comparable接口 ========================================

    /**
     * 对象比较。
     *
     * <p>
     * 比较本对象与另一对象的大小。
     * 如果待比较的对象的类型不是<code>Money</code>，则抛出<code>java.lang.ClassCastException</code>。
     * 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额少于待比较货币对象，则返回-1。
     * 如果本货币对象的金额等于待比较货币对象，则返回0。
     * 如果本货币对象的金额大于待比较货币对象，则返回1。
     *
     * @param other 另一对象。
     * @return -1表示小于，0表示等于，1表示大于。
     * @throws ClassCastException 待比较货币对象不是<code>Money</code>。
     * IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Object other) {
        return compareTo((Money)other);
    }

    /**
     * 货币比较。
     *
     * <p>
     * 比较本货币对象与另一货币对象的大小。
     * 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额少于待比较货币对象，则返回-1。
     * 如果本货币对象的金额等于待比较货币对象，则返回0。
     * 如果本货币对象的金额大于待比较货币对象，则返回1。
     *
     * @param other 另一对象。
     * @return -1表示小于，0表示等于，1表示大于。
     * @throws IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     */
    public int compareTo(Money other) {
        assertSameCurrencyAs(other);

        if (this.cent < other.cent) {
            return -1;
        } else if (this.cent == other.cent) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 货币比较。
     *
     * <p>
     * 判断本货币对象是否大于另一货币对象。
     * 如果待比较的两个货币对象的币种不同，则抛出<code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额大于待比较货币对象，则返回true，否则返回false。
     *
     * @param other 另一对象。
     * @return true表示大于，false表示不大于（小于等于）。
     * @throws IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     */
    public boolean greaterThan(Money other) {
        return compareTo(other) > 0;
    }

    // 货币算术 ==========================================

    /**
     * 货币加法。
     *
     * <p>
     * 如果两货币币种相同，则返回一个新的相同币种的货币对象，其金额为
     * 两货币对象金额之和，本货币对象的值不变。
     * 如果两货币对象币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为加数的货币对象。
     * @return 相加后的结果。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money add(Money other) {
        assertSameCurrencyAs(other);

        return newMoneyWithSameCurrency(this.cent + other.cent);
    }

    /**
     * 货币累加。
     *
     * <p>
     * 如果两货币币种相同，则本货币对象的金额等于两货币对象金额之和，并返回本货币对象的引用。
     * 如果两货币对象币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为加数的货币对象。
     * @return 累加后的本货币对象。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money addTo(Money other) {
        assertSameCurrencyAs(other);

        this.cent += other.cent;

        return this;
    }

    /**
     * 货币减法。
     *
     * <p>
     * 如果两货币币种相同，则返回一个新的相同币种的货币对象，其金额为
     * 本货币对象的金额减去参数货币对象的金额。本货币对象的值不变。
     * 如果两货币币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为减数的货币对象。
     * @return 相减后的结果。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money subtract(Money other) {
        assertSameCurrencyAs(other);

        return newMoneyWithSameCurrency(this.cent - other.cent);
    }

    /**
     * 货币累减。
     *
     * <p>
     * 如果两货币币种相同，则本货币对象的金额等于两货币对象金额之差，并返回本货币对象的引用。
     * 如果两货币币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为减数的货币对象。
     * @return 累减后的本货币对象。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money subtractFrom(Money other) {
        assertSameCurrencyAs(other);

        this.cent -= other.cent;

        return this;
    }

    /**
     * 货币乘法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。
     *
     * @param val 乘数
     * @return 乘法后的结果。
     */
    public Money multiply(long val) {
        return newMoneyWithSameCurrency(this.cent * val);
    }

    /**
     * 货币累乘。
     *
     * <p>
     * 本货币对象金额乘以乘数，并返回本货币对象。
     *
     * @param val 乘数
     * @return 累乘后的本货币对象。
     */
    public Money multiplyBy(long val) {
        this.cent *= val;

        return this;
    }

    /**
     * 货币乘法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。如果相乘后的金额不能转换为整数分，则四舍五入。
     *
     * @param val 乘数
     * @return 相乘后的结果。
     */
    public Money multiply(double val) {
        return newMoneyWithSameCurrency(Math.round(this.cent * val));
    }

    /**
     * 货币累乘。
     *
     * <p>
     * 本货币对象金额乘以乘数，并返回本货币对象。
     * 如果相乘后的金额不能转换为整数分，则使用四舍五入。
     *
     * @param val 乘数
     * @return 累乘后的本货币对象。
     */
    public Money multiplyBy(double val) {
        this.cent = Math.round(this.cent * val);

        return this;
    }

    /**
     * 货币乘法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。如果相乘后的金额不能转换为整数分，使用缺省的取整模式
     * <code>DEFUALT_ROUNDING_MODE</code>进行取整。
     *
     * @param val 乘数
     * @return 相乘后的结果。
     */
    public Money multiply(BigDecimal val) {
        return multiply(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币累乘。
     *
     * <p>
     * 本货币对象金额乘以乘数，并返回本货币对象。
     * 如果相乘后的金额不能转换为整数分，使用缺省的取整方式
     * <code>DEFUALT_ROUNDING_MODE</code>进行取整。
     *
     * @param val 乘数
     * @return 累乘后的结果。
     */
    public Money multiplyBy(BigDecimal val) {
        return multiplyBy(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币乘法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。如果相乘后的金额不能转换为整数分，使用指定的取整方式
     * <code>roundingMode</code>进行取整。
     *
     * @param val          乘数
     * @param roundingMode 取整方式
     * @return 相乘后的结果。
     */
    public Money multiply(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).multiply(val);

        return newMoneyWithSameCurrency(rounding(newCent, roundingMode));
    }

    /**
     * 货币累乘。
     *
     * <p>
     * 本货币对象金额乘以乘数，并返回本货币对象。
     * 如果相乘后的金额不能转换为整数分，使用指定的取整方式
     * <code>roundingMode</code>进行取整。
     *
     * @param val          乘数
     * @param roundingMode 取整方式
     * @return 累乘后的结果。
     */
    public Money multiplyBy(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).multiply(val);

        this.cent = rounding(newCent, roundingMode);

        return this;
    }

    /**
     * 货币除法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额除以除数。
     * 本货币对象的值不变。如果相除后的金额不能转换为整数分，使用四舍五入方式取整。
     *
     * @param val 除数
     * @return 相除后的结果。
     */
    public Money divide(double val) {
        return newMoneyWithSameCurrency(Math.round(this.cent / val));
    }

    /**
     * 货币累除。
     *
     * <p>
     * 本货币对象金额除以除数，并返回本货币对象。
     * 如果相除后的金额不能转换为整数分，使用四舍五入方式取整。
     *
     * @param val 除数
     * @return 累除后的结果。
     */
    public Money divideBy(double val) {
        this.cent = Math.round(this.cent / val);

        return this;
    }

    /**
     * 货币除法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额除以除数。
     * 本货币对象的值不变。如果相除后的金额不能转换为整数分，使用缺省的取整模式
     * <code>DEFAULT_ROUNDING_MODE</code>进行取整。
     *
     * @param val 除数
     * @return 相除后的结果。
     */
    public Money divide(BigDecimal val) {
        return divide(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币除法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额除以除数。
     * 本货币对象的值不变。如果相除后的金额不能转换为整数分，使用指定的取整模式
     * <code>roundingMode</code>进行取整。
     *
     * @param val          除数
     * @param roundingMode 取整
     * @return 相除后的结果。
     */
    public Money divide(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, roundingMode);

        return newMoneyWithSameCurrency(newCent.longValue());
    }

    /**
     * 货币累除。
     *
     * <p>
     * 本货币对象金额除以除数，并返回本货币对象。
     * 如果相除后的金额不能转换为整数分，使用缺省的取整模式
     * <code>DEFAULT_ROUNDING_MODE</code>进行取整。
     *
     * @param val 除数
     * @return 累除后的结果。
     */
    public Money divideBy(BigDecimal val) {
        return divideBy(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币累除。
     *
     * <p>
     * 本货币对象金额除以除数，并返回本货币对象。
     * 如果相除后的金额不能转换为整数分，使用指定的取整模式
     * <code>roundingMode</code>进行取整。
     *
     * @param val          除数
     * @param roundingMode
     * @return 累除后的结果。
     */
    public Money divideBy(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, roundingMode);

        this.cent = newCent.longValue();

        return this;
    }

    /**
     * 货币分配。
     *
     * <p>
     * 将本货币对象尽可能平均分配成<code>targets</code>份。
     * 如果不能平均分配尽，则将零头放到开始的若干份中。分配
     * 运算能够确保不会丢失金额零头。
     *
     * @param targets 待分配的份数
     * @return 货币对象数组，数组的长度与分配份数相同，数组元素
     * 从大到小排列，所有货币对象的金额最多只相差1分。
     */
    public Money[] allocate(int targets) {
        Money[] results = new Money[targets];

        Money lowResult = newMoneyWithSameCurrency(this.cent / targets);
        Money highResult = newMoneyWithSameCurrency(lowResult.cent + 1);

        int remainder = (int)this.cent % targets;

        for (int i = 0; i < remainder; i++) {
            results[i] = highResult;
        }

        for (int i = remainder; i < targets; i++) {
            results[i] = lowResult;
        }

        return results;
    }

    /**
     * 货币分配。
     *
     * <p>
     * 将本货币对象按照规定的比例分配成若干份。分配所剩的零头
     * 从第一份开始顺序分配。分配运算确保不会丢失金额零头。
     *
     * @param ratios 分配比例数组，每一个比例是一个长整型，代表
     *               相对于总数的相对数。
     * @return 货币对象数组，数组的长度与分配比例数组的长度相同。
     */
    public Money[] allocate(long[] ratios) {
        Money[] results = new Money[ratios.length];

        long total = 0;

        for (int i = 0; i < ratios.length; i++) {
            total += ratios[i];
        }

        long remainder = this.cent;

        for (int i = 0; i < results.length; i++) {
            results[i] = newMoneyWithSameCurrency((this.cent * ratios[i]) / total);
            remainder -= results[i].cent;
        }

        for (int i = 0; i < remainder; i++) {
            results[i].cent++;
        }

        return results;
    }

    // 格式化方法 =================================================

    /**
     * 生成本对象的缺省字符串表示
     *
     * @return string
     */
    public String toString() {
        return getAmount().toString();

        //        StringBuffer sb = new StringBuffer();
        //
        //        // sb.append(currency.getSymbol());
        //
        //        // sb.append(getAmount().toString());
        //        sb.append(cent / getCentFactor());
        //
        //        if (getCentFactor() != 1) {
        //            sb.append(".");
        //
        //            String remainder = String.valueOf(getCentFactor() + Math.abs(cent %
        // getCentFactor()));
        //
        //            sb.append(remainder.substring(1));
        //        }
        //
        //        return sb.toString();
    }

    // 内部方法 ===================================================

    /**
     * 断言本货币对象与另一货币对象是否具有相同的币种。
     *
     * <p>
     * 如果本货币对象与另一货币对象具有相同的币种，则方法返回。
     * 否则抛出运行时异常<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 另一货币对象
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    protected void assertSameCurrencyAs(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Money math currency mismatch.");
        }
    }

    /**
     * 对BigDecimal型的值按指定取整方式取整。
     *
     * @param val          待取整的BigDecimal值
     * @param roundingMode 取整方式
     * @return 取整后的long型值
     */
    protected long rounding(BigDecimal val, int roundingMode) {
        return val.setScale(0, roundingMode).longValue();
    }

    /**
     * 创建一个币种相同，具有指定金额的货币对象。
     *
     * @param cent1 金额，以分为单位
     * @return 一个新建的币种相同，具有指定金额的货币对象
     */
    protected Money newMoneyWithSameCurrency(long cent1) {
        Money money = new Money(0, this.currency);

        money.cent = cent1;

        return money;
    }

    // 调试方式 ==================================================

    /**
     * 生成本对象内部变量的字符串表示，用于调试。
     *
     * @return 本对象内部变量的字符串表示。
     */
    public String dump() {
        String lineSeparator = System.getProperty("line.separator");

        StringBuffer sb = new StringBuffer();

        sb.append("cent = ").append(this.cent).append(lineSeparator);
        sb.append("currency = ").append(this.currency);

        return sb.toString();
    }

    /**
     * @param cent The cent to set.
     */
    public void setCent(long cent) {
        this.cent = cent;
    }

}

class YearSettleResultDTO implements Serializable {

    private static final long serialVersionUID = -5936469934430068682L;
    private String settleYear ;						//年结年份
    private Long bailOrderId;                       //合同id													--第2列
    private Long userId;                            //商家ID													--第3列
    private Long prePaySoftWareFee            ;		//预缴软件服务年费			--总预缴						    --第8列
    private Long baseFee                      ;		//参与保底的成交额		    --参与保底成交额（包含村淘）	    --第10列
    private Long cunTaoBaseFee                ;		//村淘参与保底的成交额	    --村淘成交额					    --第11列
    private Long realTimeSoftWareFee          ;		//实时划扣软件服务费		--实时划扣技术服务费（不包含村淘）+实时划扣村淘服务费
    private Long realTimeTecFee;                    //实时划扣技术服务费（不包含村淘）						    --第13列
    private Long cunTaoRealTimeSoftWareFee    ;		//村淘实时划扣软件服务费	--实时划扣村淘服务费			    --第14列
    private Boolean isFinishBaseFee           ;		//是否完成保底成交额		--是否达到保底				    --第12列
    private Long receiveSoftWareFee           ;		//应收软件服务费			--应收佣金						--第15列
    private Long refundAmount                 ;		//应退款金额				--应退金额						--第16列
    private Long hasRefundAmountTechFee       ;		//已退款金额（年费）		--已退年费						--第17列
    private Long hasRefundAmountCommission    ;		//已退款金额（佣金）		--已退佣金						--第19列
    private Date techFeeRefundTime;					//已退年费的退款时间										--第18列
    private Date commissionRefundTime; 				//已退佣金的退款时间										--第20列
    private Long backAmount                   ;		//应补缴金额  			amount
    private Long hasBackAmount                ;		//已补缴金额  			amount - unpay_amount
    private Long notBackAmount                ;		//未补缴金额  			unpay_amount
    private Long curOrderAmount               ;		//当前实时成交额 			baseFee
    private Long monthTotalCurOrderAmount     ;		//月汇总成交额 			baseFee

    public String getSettleYear() {
        return settleYear;
    }

    public void setSettleYear(String settleYear) {
        this.settleYear = settleYear;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBailOrderId() {
        return bailOrderId;
    }

    public void setBailOrderId(Long bailOrderId) {
        this.bailOrderId = bailOrderId;
    }

    public Long getPrePaySoftWareFee() {
        return prePaySoftWareFee;
    }

    public void setPrePaySoftWareFee(Long prePaySoftWareFee) {
        this.prePaySoftWareFee = prePaySoftWareFee;
    }

    public Long getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Long baseFee) {
        this.baseFee = baseFee;
    }

    public Long getCunTaoBaseFee() {
        return cunTaoBaseFee;
    }

    public void setCunTaoBaseFee(Long cunTaoBaseFee) {
        this.cunTaoBaseFee = cunTaoBaseFee;
    }

    public Long getRealTimeSoftWareFee() {
        return realTimeSoftWareFee;
    }

    public void setRealTimeSoftWareFee(Long realTimeSoftWareFee) {
        this.realTimeSoftWareFee = realTimeSoftWareFee;
    }

    public Long getCunTaoRealTimeSoftWareFee() {
        return cunTaoRealTimeSoftWareFee;
    }

    public void setCunTaoRealTimeSoftWareFee(Long cunTaoRealTimeSoftWareFee) {
        this.cunTaoRealTimeSoftWareFee = cunTaoRealTimeSoftWareFee;
    }

    public Boolean getFinishBaseFee() {
        return isFinishBaseFee;
    }

    public void setFinishBaseFee(Boolean finishBaseFee) {
        isFinishBaseFee = finishBaseFee;
    }

    public Long getReceiveSoftWareFee() {
        return receiveSoftWareFee;
    }

    public void setReceiveSoftWareFee(Long receiveSoftWareFee) {
        this.receiveSoftWareFee = receiveSoftWareFee;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getHasRefundAmountTechFee() {
        return hasRefundAmountTechFee;
    }

    public void setHasRefundAmountTechFee(Long hasRefundAmountTechFee) {
        this.hasRefundAmountTechFee = hasRefundAmountTechFee;
    }

    public Long getRealTimeTecFee() {
        return realTimeTecFee;
    }

    public void setRealTimeTecFee(Long realTimeTecFee) {
        this.realTimeTecFee = realTimeTecFee;
    }

    public Long getHasRefundAmountCommission() {
        return hasRefundAmountCommission;
    }

    public void setHasRefundAmountCommission(Long hasRefundAmountCommission) {
        this.hasRefundAmountCommission = hasRefundAmountCommission;
    }

    public Date getTechFeeRefundTime() {
        return techFeeRefundTime;
    }

    public void setTechFeeRefundTime(Date techFeeRefundTime) {
        this.techFeeRefundTime = techFeeRefundTime;
    }

    public Date getCommissionRefundTime() {
        return commissionRefundTime;
    }

    public void setCommissionRefundTime(Date commissionRefundTime) {
        this.commissionRefundTime = commissionRefundTime;
    }

    public Long getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(Long backAmount) {
        this.backAmount = backAmount;
    }

    public Long getHasBackAmount() {
        return hasBackAmount;
    }

    public void setHasBackAmount(Long hasBackAmount) {
        this.hasBackAmount = hasBackAmount;
    }

    public Long getNotBackAmount() {
        return notBackAmount;
    }

    public void setNotBackAmount(Long notBackAmount) {
        this.notBackAmount = notBackAmount;
    }

    public Long getCurOrderAmount() {
        return curOrderAmount;
    }

    public void setCurOrderAmount(Long curOrderAmount) {
        this.curOrderAmount = curOrderAmount;
    }

    public Long getMonthTotalCurOrderAmount() {
        return monthTotalCurOrderAmount;
    }

    public void setMonthTotalCurOrderAmount(Long monthTotalCurOrderAmount) {
        this.monthTotalCurOrderAmount = monthTotalCurOrderAmount;
    }

    @Override
    public String toString() {
        return "YearSettleResultDTO{" +
            "settleYear='" + settleYear + '\'' +
            ", bailOrderId=" + bailOrderId +
            ", userId=" + userId +
            ", prePaySoftWareFee=" + prePaySoftWareFee +
            ", baseFee=" + baseFee +
            ", cunTaoBaseFee=" + cunTaoBaseFee +
            ", realTimeSoftWareFee=" + realTimeSoftWareFee +
            ", realTimeTecFee=" + realTimeTecFee +
            ", cunTaoRealTimeSoftWareFee=" + cunTaoRealTimeSoftWareFee +
            ", isFinishBaseFee=" + isFinishBaseFee +
            ", receiveSoftWareFee=" + receiveSoftWareFee +
            ", refundAmount=" + refundAmount +
            ", hasRefundAmountTechFee=" + hasRefundAmountTechFee +
            ", hasRefundAmountCommission=" + hasRefundAmountCommission +
            ", techFeeRefundTime=" + techFeeRefundTime +
            ", commissionRefundTime=" + commissionRefundTime +
            ", backAmount=" + backAmount +
            ", hasBackAmount=" + hasBackAmount +
            ", notBackAmount=" + notBackAmount +
            ", curOrderAmount=" + curOrderAmount +
            ", monthTotalCurOrderAmount=" + monthTotalCurOrderAmount +
            '}';
    }
}
