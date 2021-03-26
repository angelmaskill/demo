package com.excel;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/1/22 16:45
 * @Modified By:
 */
public class POIReadUtilForJob {
    // ������λ
    private DecimalFormat df_2wei = new DecimalFormat("0.00");
    // ������λ
    private DecimalFormat df_4wei = new DecimalFormat("0.0000");
    // ��������
    private DecimalFormat df_int = new DecimalFormat("#");

    /**
     * ������
     */
    private int totalRows = 0;
    /**
     * ������
     */
    private int totalCells = 0;
    /**
     * ������Ϣ
     */
    private String errorInfo;

    /**
     * ���췽��
     */
    public POIReadUtilForJob() {
    }

    /**
     * @�������õ�������
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:27:15
     * @������@return
     * @����ֵ��int
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * @�������õ�������
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:27:15
     * @������@return
     * @����ֵ��int
     */
    public int getTotalCells() {
        return totalCells;
    }

    /**
     * @�������õ�������Ϣ
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:27:15
     * @������@return
     * @����ֵ��String
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * @��������֤excel�ļ�
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:27:15
     * @������@param filePath���ļ�����·��
     * @������@return
     * @����ֵ��boolean
     */
    public boolean validateExcel(String filePath) {
        /** ����ļ����Ƿ�Ϊ�ջ����Ƿ���Excel��ʽ���ļ� */
        if (filePath == null || !(
                WDWUtil2.isExcel2003(filePath) || WDWUtil2.isExcel2007(filePath))) {
            errorInfo = "�ļ�������excel��ʽ";
            return false;
        }
        /** ����ļ��Ƿ���� */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            errorInfo = "�ļ�������";
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
        //��ȡ��ʽ������
        NumberFormat nt = NumberFormat.getPercentInstance();
        //���ðٷ�����ȷ��2��������λС��
        nt.setMinimumFractionDigits(3);
        return nt.format(decimal);
    }

    /**
     * @�����������ļ�����ȡexcel�ļ�
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:27:15
     * @������@param filePath �ļ�����·��
     * @������@return
     * @����ֵ��List
     */
    public List<List<String>> read(String filePath) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        InputStream is = null;
        try {
            /** ��֤�ļ��Ƿ�Ϸ� */
            if (!validateExcel(filePath)) {
                System.out.println(errorInfo);
                return null;
            }
            /** �ж��ļ������ͣ���2003����2007 */
            boolean isExcel2003 = true;
            if (WDWUtil2.isExcel2007(filePath)) {
                isExcel2003 = false;
            }
            /** ���ñ����ṩ�ĸ�������ȡ�ķ��� */
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
        /** ��������ȡ�Ľ�� */
        return dataLst;
    }

    /**
     * @��������������ȡExcel�ļ�
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:40:15
     * @������@param inputStream
     * @������@param isExcel2003
     * @������@return
     * @����ֵ��List
     */
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataLst = null;
        try {
            /** ���ݰ汾ѡ�񴴽�Workbook�ķ�ʽ */
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
     * @��������ȡ����
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:50:15
     * @������@param Workbook
     * @������@return
     * @����ֵ��List<List<String>>
     */
    private List<List<String>> read(Workbook wb) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** �õ���һ��shell */
        Sheet sheet = wb.getSheetAt(0);
        /** �õ�Excel������ */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** �õ�Excel������ */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        /** ѭ��Excel���� */
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            /** ѭ��Excel���� */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // �������ж����ݵ�����
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // ����
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
                        case HSSFCell.CELL_TYPE_STRING: // �ַ���
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
                            cellValue = cell.getCellFormula() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR: // ����
                            cellValue = "�Ƿ��ַ�";
                            break;
                        default:
                            cellValue = "δ֪����";
                            break;
                    }
                }
                rowLst.add(cellValue);
            }
            /** �����r�еĵ�c�� */
            dataLst.add(rowLst);
        }
        return dataLst;
    }

    /**
     * @������main���Է���
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����17:12:15
     * @������@param args
     * @������@throws Exception
     * @����ֵ��void
     */
    public static void main(String[] args) throws Exception {
        POIReadUtilForJob poi = new POIReadUtilForJob();
        List<List<String>> list = poi.read(
                //"C:\\Users\\yanlu.myl\\Desktop\\temp\\2018��ڶ���ka����(3).xlsx");
                "C:\\Users\\yanlu.myl\\Desktop\\temp\\2018��ڶ���ka����(check).xlsx");
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                //System.out.print("��" + (i) + "��\t\t");
                List<String> cellList = list.get(i);
                YearSettleResultDTO dto = new YearSettleResultDTO();
                dto.setSettleYear("2017");
                for (int j = 0; j < cellList.size(); j++) {
                    /* ��ӡ��ȡ����е����� */
                    //System.out.println("    ��" + (j + 1) + "��ֵ��" + cellList.get(j));
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
                        dto.setPrePaySoftWareFee(changY2f(cellList.get(j)));//��Ԥ��
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

                /* ��ӡ���л�֮��Ķ����ַ��� */
                //System.out.println(JSON.toJSONString(dto));

                /* ���Է����л��Ƿ��ܳɹ� */
                //YearSettleResultDTO yearSettleResultDTO = JSON.parseObject(JSON.toJSONString(dto),
                //    YearSettleResultDTO.class);
                //System.out.println(yearSettleResultDTO.toString());

                /* ���sql��� */
                StringBuffer sb = new StringBuffer();
                /*sb.append(
                    "insert into hj_bail_ext(gmt_create,  gmt_modified,  bail_order_id,  user_id,"
                        + "  name,  code,  value,  version,  status) values(now(),now(),"
                        + dto.getBailOrderId() + "," + dto.getUserId()
                        + ",'�����','yearsettleresult','" + JSON
                        .toJSONString(dto) + "',0,1);");*/
                sb.append("update hj_bail_ext set gmt_modified = now(), version=1 , value='" + JSON
                        .toJSONString(dto) + "' where bail_order_id=" + dto.getBailOrderId()
                        + " and code='yearsettleresult';");
                System.out.println(sb);
            }
        }
    }
}

/**
 * @������������
 * @���ߣ�yanlu.myl
 * @ʱ�䣺2018/1/22 ����16:30:40
 */
class WDWUtil2 {
    /**
     * @�������Ƿ���2003��excel������true��2003
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:29:11
     * @������@param filePath���ļ�����·��
     * @������@return
     * @����ֵ��boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @�������Ƿ���2007��excel������true��2007
     * @���ߣ�yanlu.myl
     * @ʱ�䣺2018/1/22 ����16:28:20
     * @������@param filePath���ļ�����·��
     * @������@return
     * @����ֵ��boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

class Money implements Serializable, Comparable {
    private static final long serialVersionUID = 3761410806910104373L;

    /**
     * ȱʡ�ı��ִ��룬ΪCNY������ң���
     */
    public static final String DEFAULT_CURRENCY_CODE = "CNY";

    /**
     * ȱʡ��ȡ��ģʽ��Ϊ<code>BigDecimal.ROUND_HALF_EVEN
     * ���������룬��С��Ϊ0.5ʱ����ȡ�����ż������
     */
    public static final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    /**
     * һ����ܵ�Ԫ/�ֻ��������
     *
     * <p>
     * �˴������֡���ָ���ҵ���С��λ����Ԫ���ǻ��ҵ���õ�λ��
     * ��ͬ�ı����в�ͬ��Ԫ/�ֻ�����������������100������ԪΪ1��
     */
    private static final int[] centFactors = new int[]{1, 10, 100, 1000};

    /**
     * Ĭ�J�ĵ؅^
     */
    private static final String DEFAULT_LOCALE = "zh_CN";
    /**
     * ������ҵ�λ��map��������ʾ���ҵ�λ����Ԫ����Ԫ��
     */
    protected static Map<String, Map<String, String>> CURRENCY_DISPLAY_UNIT_MAP
            = new HashMap<String, Map<String, String>>();

    //���г�ʼ������
    static {
        //���w���ĵČ����l
        Map<String, String> zhCNMap = new HashMap<String, String>();
        zhCNMap.put("CNY", "Ԫ");
        zhCNMap.put("POINT", "����");
        CURRENCY_DISPLAY_UNIT_MAP.put("zh_CN", zhCNMap);

        //���w�Č����l
        Map<String, String> zhHKMap = new HashMap<String, String>();
        zhHKMap.put("CNY", "Ԫ");
        zhHKMap.put("POINT", "�e��");
        CURRENCY_DISPLAY_UNIT_MAP.put("zh_HK", zhHKMap);
    }

    /**
     * ���붨���ں���(����ǰ��ľ�̬����)
     */
    public static final Money ZERO = new Money(0);

    /**
     * ���Է�Ϊ��λ��
     */
    private long cent;

    /**
     * ���֡�
     */
    private Currency currency;

    // ������ ====================================================

    /**
     * ȱʡ��������
     *
     * <p>
     * ����һ������ȱʡ��0����ȱʡ���ֵĻ��Ҷ���
     */
    public Money() {
        this(0l);
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>yuan</code>Ԫ<code>cent</cent>�ֺ�ȱʡ���ֵĻ��Ҷ���
     *
     * @param yuan ���Ԫ����
     * @param cent ��������
     */
    public Money(long yuan, int cent) {
        this(yuan, cent, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>yuan</code>Ԫ<code>cent</cent>�ֺ�ȱʡ���ֵĻ��Ҷ���
     *
     * @param cent ��������
     */
    public Money(long cent) {
        this.currency = Currency.getInstance(DEFAULT_CURRENCY_CODE);
        this.cent = cent;
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>yuan</code>Ԫ<code>cent</code>�ֺ�ָ�����ֵĻ��Ҷ���
     *
     * @param yuan     ���Ԫ����
     * @param cent     ��������
     * @param currency
     */
    public Money(long yuan, int cent, Currency currency) {
        this.currency = currency;

        this.cent = (yuan * getCentFactor()) + (cent % getCentFactor());
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>Ԫ��ȱʡ���ֵĻ��Ҷ���
     *
     * @param amount ����ԪΪ��λ��
     */
    public Money(String amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>Ԫ��ָ������<code>currency</code>�Ļ��Ҷ���
     *
     * @param amount   ����ԪΪ��λ��
     * @param currency ���֡�
     */
    public Money(String amount, Currency currency) {
        this(new BigDecimal(amount), currency);
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>Ԫ��ָ������<code>currency</code>�Ļ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ��ָ����ȡ��ģʽ<code>roundingMode</code>ȡ����
     *
     * @param amount       ����ԪΪ��λ��
     * @param currency     ���֡�
     * @param roundingMode ȡ��ģʽ��
     */
    public Money(String amount, Currency currency, int roundingMode) {
        this(new BigDecimal(amount), currency, roundingMode);
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����в���<code>amount</code>ָ������ȱʡ���ֵĻ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ���������뷽ʽȡ����
     *
     * <p>
     * ע�⣺����double���������д�����ʹ���������뷽ʽȡ����
     * �������ȷ������ˣ�Ӧ��������ʹ��double���ʹ����������͡�
     * ����
     * <code>
     * assertEquals(999, Math.round(9.995 * 100));
     * assertEquals(1000, Math.round(999.5));
     * money = new Money((9.995));
     * assertEquals(999, money.getCent());
     * money = new Money(10.005);
     * assertEquals(1001, money.getCent());
     * </code>
     *
     * @param amount ����ԪΪ��λ��
     */
    public Money(double amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>��ָ�����ֵĻ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ���������뷽ʽȡ����
     *
     * <p>
     * ע�⣺����double���������д�����ʹ���������뷽ʽȡ����
     * �������ȷ������ˣ�Ӧ��������ʹ��double���ʹ����������͡�
     * ����
     * <code>
     * assertEquals(999, Math.round(9.995 * 100));
     * assertEquals(1000, Math.round(999.5));
     * money = new Money((9.995));
     * assertEquals(999, money.getCent());
     * money = new Money(10.005);
     * assertEquals(1001, money.getCent());
     * </code>
     *
     * @param amount   ����ԪΪ��λ��
     * @param currency ���֡�
     */
    public Money(double amount, Currency currency) {
        this.currency = currency;
        this.cent = Math.round(amount * getCentFactor());
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>��ȱʡ���ֵĻ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ��ȱʡȡ��ģʽ<code>DEFAULT_ROUNDING_MODE</code>ȡ����
     *
     * @param amount ����ԪΪ��λ��
     */
    public Money(BigDecimal amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����в���<code>amount</code>ָ������ȱʡ���ֵĻ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ��ָ����ȡ��ģʽ<code>roundingMode</code>ȡ����
     *
     * @param amount       ����ԪΪ��λ��
     * @param roundingMode ȡ��ģʽ
     */
    public Money(BigDecimal amount, int roundingMode) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE), roundingMode);
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>��ָ�����ֵĻ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ��ȱʡ��ȡ��ģʽ<code>DEFAULT_ROUNDING_MODE</code>����ȡ����
     *
     * @param amount   ����ԪΪ��λ��
     * @param currency ����
     */
    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING_MODE);
    }

    /**
     * ��������
     *
     * <p>
     * ����һ�����н��<code>amount</code>��ָ�����ֵĻ��Ҷ���
     * �������ת��Ϊ�����֣���ʹ��ָ����ȡ��ģʽ<code>roundingMode</code>ȡ����
     *
     * @param amount       ����ԪΪ��λ��
     * @param currency     ���֡�
     * @param roundingMode ȡ��ģʽ��
     */
    public Money(BigDecimal amount, Currency currency, int roundingMode) {
        this.currency = currency;
        this.cent = rounding(amount.movePointRight(currency.getDefaultFractionDigits()),
                roundingMode);
    }

    // Bean���� ====================================================

    /**
     * ��ȡ�����Ҷ������Ľ������
     *
     * @return ���������ԪΪ��λ��
     */
    public BigDecimal getAmount() {
        return BigDecimal.valueOf(this.cent, this.currency.getDefaultFractionDigits());
    }

    /**
     * ���ñ����Ҷ������Ľ������
     *
     * @param amount ���������ԪΪ��λ��
     */
    public void setAmount(BigDecimal amount) {
        if (amount != null) {
            this.cent = rounding(amount.movePointRight(2), BigDecimal.ROUND_HALF_EVEN);
        }
    }

    /**
     * ��ȡ�����Ҷ������Ľ������
     *
     * @return ��������Է�Ϊ��λ��
     */
    public long getCent() {
        return this.cent;
    }

    /**
     * ��ȡ�����Ҷ������ı��֡�
     *
     * @return �����Ҷ���������ı��֡�
     * @deprecated use getCurrencyCode()
     */
    public Currency getCurrency() {
        return this.currency;
    }

    /**
     * ��ȡ�����Ҷ������ı��ִ���
     *
     * @return ���ִ���
     */
    public String getCurrencyCode() {
        return this.currency.getCurrencyCode();
    }

    /**
     * ��ȡ�����ұ��ֵ�Ԫ/�ֻ�����ʡ�
     *
     * @return �����ұ��ֵ�Ԫ/�ֻ�����ʡ�
     */
    public int getCentFactor() {
        return centFactors[this.currency.getDefaultFractionDigits()];
    }

    // �������󷽷� ===================================================

    /**
     * �жϱ����Ҷ�������һ�����Ƿ���ȡ�
     *
     * <p>
     * �����Ҷ�������һ������ȵĳ�ֱ�Ҫ�����ǣ�<br>
     * <ul>
     * <li>��һ����Ҳ�����Ҷ����ࡣ
     * <li>�����ͬ��
     * <li>������ͬ��
     * </ul>
     *
     * @param other ���Ƚϵ���һ����
     * @return <code>true</code>��ʾ��ȣ�<code>false</code>��ʾ����ȡ�
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        return (other instanceof Money) && equals((Money) other);
    }

    /**
     * �жϱ����Ҷ�������һ���Ҷ����Ƿ���ȡ�
     *
     * <p>
     * �����Ҷ�������һ���Ҷ�����ȵĳ�ֱ�Ҫ�����ǣ�<br>
     * <ul>
     * <li>�����ͬ��
     * <li>������ͬ��
     * </ul>
     *
     * @param other ���Ƚϵ���һ���Ҷ���
     * @return <code>true</code>��ʾ��ȣ�<code>false</code>��ʾ����ȡ�
     */
    public boolean equals(Money other) {
        return this.currency.equals(other.currency) && (this.cent == other.cent);
    }

    /**
     * ���㱾���Ҷ�����Ӵ�ֵ��
     *
     * @return �����Ҷ�����Ӵ�ֵ��
     * @see Object#hashCode()
     */
    public int hashCode() {
        return (int) (this.cent ^ (this.cent >>> 32));
    }

    // Comparable�ӿ� ========================================

    /**
     * ����Ƚϡ�
     *
     * <p>
     * �Ƚϱ���������һ����Ĵ�С��
     * ������ȽϵĶ�������Ͳ���<code>Money</code>�����׳�<code>java.lang.ClassCastException</code>��
     * ������Ƚϵ��������Ҷ���ı��ֲ�ͬ�����׳�<code>java.lang.IllegalArgumentException</code>��
     * ��������Ҷ���Ľ�����ڴ��Ƚϻ��Ҷ����򷵻�-1��
     * ��������Ҷ���Ľ����ڴ��Ƚϻ��Ҷ����򷵻�0��
     * ��������Ҷ���Ľ����ڴ��Ƚϻ��Ҷ����򷵻�1��
     *
     * @param other ��һ����
     * @return -1��ʾС�ڣ�0��ʾ���ڣ�1��ʾ���ڡ�
     * @throws ClassCastException ���Ƚϻ��Ҷ�����<code>Money</code>��
     *                            IllegalArgumentException ���Ƚϻ��Ҷ����뱾���Ҷ���ı��ֲ�ͬ��
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Object other) {
        return compareTo((Money) other);
    }

    /**
     * ���ұȽϡ�
     *
     * <p>
     * �Ƚϱ����Ҷ�������һ���Ҷ���Ĵ�С��
     * ������Ƚϵ��������Ҷ���ı��ֲ�ͬ�����׳�<code>java.lang.IllegalArgumentException</code>��
     * ��������Ҷ���Ľ�����ڴ��Ƚϻ��Ҷ����򷵻�-1��
     * ��������Ҷ���Ľ����ڴ��Ƚϻ��Ҷ����򷵻�0��
     * ��������Ҷ���Ľ����ڴ��Ƚϻ��Ҷ����򷵻�1��
     *
     * @param other ��һ����
     * @return -1��ʾС�ڣ�0��ʾ���ڣ�1��ʾ���ڡ�
     * @throws IllegalArgumentException ���Ƚϻ��Ҷ����뱾���Ҷ���ı��ֲ�ͬ��
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
     * ���ұȽϡ�
     *
     * <p>
     * �жϱ����Ҷ����Ƿ������һ���Ҷ���
     * ������Ƚϵ��������Ҷ���ı��ֲ�ͬ�����׳�<code>java.lang.IllegalArgumentException</code>��
     * ��������Ҷ���Ľ����ڴ��Ƚϻ��Ҷ����򷵻�true�����򷵻�false��
     *
     * @param other ��һ����
     * @return true��ʾ���ڣ�false��ʾ�����ڣ�С�ڵ��ڣ���
     * @throws IllegalArgumentException ���Ƚϻ��Ҷ����뱾���Ҷ���ı��ֲ�ͬ��
     */
    public boolean greaterThan(Money other) {
        return compareTo(other) > 0;
    }

    // �������� ==========================================

    /**
     * ���Ҽӷ���
     *
     * <p>
     * ��������ұ�����ͬ���򷵻�һ���µ���ͬ���ֵĻ��Ҷ�������Ϊ
     * �����Ҷ�����֮�ͣ������Ҷ����ֵ���䡣
     * ��������Ҷ�����ֲ�ͬ���׳�<code>java.lang.IllegalArgumentException</code>��
     *
     * @param other ��Ϊ�����Ļ��Ҷ���
     * @return ��Ӻ�Ľ����
     * @throws IllegalArgumentException ��������Ҷ�������һ���Ҷ�����ֲ�ͬ��
     */
    public Money add(Money other) {
        assertSameCurrencyAs(other);

        return newMoneyWithSameCurrency(this.cent + other.cent);
    }

    /**
     * �����ۼӡ�
     *
     * <p>
     * ��������ұ�����ͬ���򱾻��Ҷ���Ľ����������Ҷ�����֮�ͣ������ر����Ҷ�������á�
     * ��������Ҷ�����ֲ�ͬ���׳�<code>java.lang.IllegalArgumentException</code>��
     *
     * @param other ��Ϊ�����Ļ��Ҷ���
     * @return �ۼӺ�ı����Ҷ���
     * @throws IllegalArgumentException ��������Ҷ�������һ���Ҷ�����ֲ�ͬ��
     */
    public Money addTo(Money other) {
        assertSameCurrencyAs(other);

        this.cent += other.cent;

        return this;
    }

    /**
     * ���Ҽ�����
     *
     * <p>
     * ��������ұ�����ͬ���򷵻�һ���µ���ͬ���ֵĻ��Ҷ�������Ϊ
     * �����Ҷ���Ľ���ȥ�������Ҷ���Ľ������Ҷ����ֵ���䡣
     * ��������ұ��ֲ�ͬ���׳�<code>java.lang.IllegalArgumentException</code>��
     *
     * @param other ��Ϊ�����Ļ��Ҷ���
     * @return �����Ľ����
     * @throws IllegalArgumentException ��������Ҷ�������һ���Ҷ�����ֲ�ͬ��
     */
    public Money subtract(Money other) {
        assertSameCurrencyAs(other);

        return newMoneyWithSameCurrency(this.cent - other.cent);
    }

    /**
     * �����ۼ���
     *
     * <p>
     * ��������ұ�����ͬ���򱾻��Ҷ���Ľ����������Ҷ�����֮������ر����Ҷ�������á�
     * ��������ұ��ֲ�ͬ���׳�<code>java.lang.IllegalArgumentException</code>��
     *
     * @param other ��Ϊ�����Ļ��Ҷ���
     * @return �ۼ���ı����Ҷ���
     * @throws IllegalArgumentException ��������Ҷ�������һ���Ҷ�����ֲ�ͬ��
     */
    public Money subtractFrom(Money other) {
        assertSameCurrencyAs(other);

        this.cent -= other.cent;

        return this;
    }

    /**
     * ���ҳ˷���
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣
     *
     * @param val ����
     * @return �˷���Ľ����
     */
    public Money multiply(long val) {
        return newMoneyWithSameCurrency(this.cent * val);
    }

    /**
     * �����۳ˡ�
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     *
     * @param val ����
     * @return �۳˺�ı����Ҷ���
     */
    public Money multiplyBy(long val) {
        this.cent *= val;

        return this;
    }

    /**
     * ���ҳ˷���
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣�����˺�Ľ���ת��Ϊ�����֣����������롣
     *
     * @param val ����
     * @return ��˺�Ľ����
     */
    public Money multiply(double val) {
        return newMoneyWithSameCurrency(Math.round(this.cent * val));
    }

    /**
     * �����۳ˡ�
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     * �����˺�Ľ���ת��Ϊ�����֣���ʹ���������롣
     *
     * @param val ����
     * @return �۳˺�ı����Ҷ���
     */
    public Money multiplyBy(double val) {
        this.cent = Math.round(this.cent * val);

        return this;
    }

    /**
     * ���ҳ˷���
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣�����˺�Ľ���ת��Ϊ�����֣�ʹ��ȱʡ��ȡ��ģʽ
     * <code>DEFUALT_ROUNDING_MODE</code>����ȡ����
     *
     * @param val ����
     * @return ��˺�Ľ����
     */
    public Money multiply(BigDecimal val) {
        return multiply(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * �����۳ˡ�
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     * �����˺�Ľ���ת��Ϊ�����֣�ʹ��ȱʡ��ȡ����ʽ
     * <code>DEFUALT_ROUNDING_MODE</code>����ȡ����
     *
     * @param val ����
     * @return �۳˺�Ľ����
     */
    public Money multiplyBy(BigDecimal val) {
        return multiplyBy(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * ���ҳ˷���
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣�����˺�Ľ���ת��Ϊ�����֣�ʹ��ָ����ȡ����ʽ
     * <code>roundingMode</code>����ȡ����
     *
     * @param val          ����
     * @param roundingMode ȡ����ʽ
     * @return ��˺�Ľ����
     */
    public Money multiply(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).multiply(val);

        return newMoneyWithSameCurrency(rounding(newCent, roundingMode));
    }

    /**
     * �����۳ˡ�
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     * �����˺�Ľ���ת��Ϊ�����֣�ʹ��ָ����ȡ����ʽ
     * <code>roundingMode</code>����ȡ����
     *
     * @param val          ����
     * @param roundingMode ȡ����ʽ
     * @return �۳˺�Ľ����
     */
    public Money multiplyBy(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).multiply(val);

        this.cent = rounding(newCent, roundingMode);

        return this;
    }

    /**
     * ���ҳ�����
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣��������Ľ���ת��Ϊ�����֣�ʹ���������뷽ʽȡ����
     *
     * @param val ����
     * @return �����Ľ����
     */
    public Money divide(double val) {
        return newMoneyWithSameCurrency(Math.round(this.cent / val));
    }

    /**
     * �����۳���
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     * ��������Ľ���ת��Ϊ�����֣�ʹ���������뷽ʽȡ����
     *
     * @param val ����
     * @return �۳���Ľ����
     */
    public Money divideBy(double val) {
        this.cent = Math.round(this.cent / val);

        return this;
    }

    /**
     * ���ҳ�����
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣��������Ľ���ת��Ϊ�����֣�ʹ��ȱʡ��ȡ��ģʽ
     * <code>DEFAULT_ROUNDING_MODE</code>����ȡ����
     *
     * @param val ����
     * @return �����Ľ����
     */
    public Money divide(BigDecimal val) {
        return divide(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * ���ҳ�����
     *
     * <p>
     * ����һ���µĻ��Ҷ��󣬱����뱾���Ҷ�����ͬ�����Ϊ�����Ҷ���Ľ����Գ�����
     * �����Ҷ����ֵ���䡣��������Ľ���ת��Ϊ�����֣�ʹ��ָ����ȡ��ģʽ
     * <code>roundingMode</code>����ȡ����
     *
     * @param val          ����
     * @param roundingMode ȡ��
     * @return �����Ľ����
     */
    public Money divide(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, roundingMode);

        return newMoneyWithSameCurrency(newCent.longValue());
    }

    /**
     * �����۳���
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     * ��������Ľ���ת��Ϊ�����֣�ʹ��ȱʡ��ȡ��ģʽ
     * <code>DEFAULT_ROUNDING_MODE</code>����ȡ����
     *
     * @param val ����
     * @return �۳���Ľ����
     */
    public Money divideBy(BigDecimal val) {
        return divideBy(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * �����۳���
     *
     * <p>
     * �����Ҷ�������Գ����������ر����Ҷ���
     * ��������Ľ���ת��Ϊ�����֣�ʹ��ָ����ȡ��ģʽ
     * <code>roundingMode</code>����ȡ����
     *
     * @param val          ����
     * @param roundingMode
     * @return �۳���Ľ����
     */
    public Money divideBy(BigDecimal val, int roundingMode) {
        BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, roundingMode);

        this.cent = newCent.longValue();

        return this;
    }

    /**
     * ���ҷ��䡣
     *
     * <p>
     * �������Ҷ��󾡿���ƽ�������<code>targets</code>�ݡ�
     * �������ƽ�����価������ͷ�ŵ���ʼ�����ɷ��С�����
     * �����ܹ�ȷ�����ᶪʧ�����ͷ��
     *
     * @param targets ������ķ���
     * @return ���Ҷ������飬����ĳ�������������ͬ������Ԫ��
     * �Ӵ�С���У����л��Ҷ���Ľ�����ֻ���1�֡�
     */
    public Money[] allocate(int targets) {
        Money[] results = new Money[targets];

        Money lowResult = newMoneyWithSameCurrency(this.cent / targets);
        Money highResult = newMoneyWithSameCurrency(lowResult.cent + 1);

        int remainder = (int) this.cent % targets;

        for (int i = 0; i < remainder; i++) {
            results[i] = highResult;
        }

        for (int i = remainder; i < targets; i++) {
            results[i] = lowResult;
        }

        return results;
    }

    /**
     * ���ҷ��䡣
     *
     * <p>
     * �������Ҷ����չ涨�ı�����������ɷݡ�������ʣ����ͷ
     * �ӵ�һ�ݿ�ʼ˳����䡣��������ȷ�����ᶪʧ�����ͷ��
     *
     * @param ratios ����������飬ÿһ��������һ�������ͣ�����
     *               ������������������
     * @return ���Ҷ������飬����ĳ���������������ĳ�����ͬ��
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

    // ��ʽ������ =================================================

    /**
     * ���ɱ������ȱʡ�ַ�����ʾ
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

    // �ڲ����� ===================================================

    /**
     * ���Ա����Ҷ�������һ���Ҷ����Ƿ������ͬ�ı��֡�
     *
     * <p>
     * ��������Ҷ�������һ���Ҷ��������ͬ�ı��֣��򷽷����ء�
     * �����׳�����ʱ�쳣<code>java.lang.IllegalArgumentException</code>��
     *
     * @param other ��һ���Ҷ���
     * @throws IllegalArgumentException ��������Ҷ�������һ���Ҷ�����ֲ�ͬ��
     */
    protected void assertSameCurrencyAs(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Money math currency mismatch.");
        }
    }

    /**
     * ��BigDecimal�͵�ֵ��ָ��ȡ����ʽȡ����
     *
     * @param val          ��ȡ����BigDecimalֵ
     * @param roundingMode ȡ����ʽ
     * @return ȡ�����long��ֵ
     */
    protected long rounding(BigDecimal val, int roundingMode) {
        return val.setScale(0, roundingMode).longValue();
    }

    /**
     * ����һ��������ͬ������ָ�����Ļ��Ҷ���
     *
     * @param cent1 ���Է�Ϊ��λ
     * @return һ���½��ı�����ͬ������ָ�����Ļ��Ҷ���
     */
    protected Money newMoneyWithSameCurrency(long cent1) {
        Money money = new Money(0, this.currency);

        money.cent = cent1;

        return money;
    }

    // ���Է�ʽ ==================================================

    /**
     * ���ɱ������ڲ��������ַ�����ʾ�����ڵ��ԡ�
     *
     * @return �������ڲ��������ַ�����ʾ��
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
    private String settleYear;                        //������
    private Long bailOrderId;                       //��ͬid													--��2��
    private Long userId;                            //�̼�ID													--��3��
    private Long prePaySoftWareFee;        //Ԥ������������			--��Ԥ��						    --��8��
    private Long baseFee;        //���뱣�׵ĳɽ���		    --���뱣�׳ɽ���������ԣ�	    --��10��
    private Long cunTaoBaseFee;        //���Բ��뱣�׵ĳɽ���	    --���Գɽ���					    --��11��
    private Long realTimeSoftWareFee;        //ʵʱ������������		--ʵʱ���ۼ�������ѣ����������ԣ�+ʵʱ���۴��Է����
    private Long realTimeTecFee;                    //ʵʱ���ۼ�������ѣ����������ԣ�						    --��13��
    private Long cunTaoRealTimeSoftWareFee;        //����ʵʱ������������	--ʵʱ���۴��Է����			    --��14��
    private Boolean isFinishBaseFee;        //�Ƿ���ɱ��׳ɽ���		--�Ƿ�ﵽ����				    --��12��
    private Long receiveSoftWareFee;        //Ӧ����������			--Ӧ��Ӷ��						--��15��
    private Long refundAmount;        //Ӧ�˿���				--Ӧ�˽��						--��16��
    private Long hasRefundAmountTechFee;        //���˿����ѣ�		--�������						--��17��
    private Long hasRefundAmountCommission;        //���˿��Ӷ��		--����Ӷ��						--��19��
    private Date techFeeRefundTime;                    //������ѵ��˿�ʱ��										--��18��
    private Date commissionRefundTime;                //����Ӷ����˿�ʱ��										--��20��
    private Long backAmount;        //Ӧ���ɽ��  			amount
    private Long hasBackAmount;        //�Ѳ��ɽ��  			amount - unpay_amount
    private Long notBackAmount;        //δ���ɽ��  			unpay_amount
    private Long curOrderAmount;        //��ǰʵʱ�ɽ��� 			baseFee
    private Long monthTotalCurOrderAmount;        //�»��ܳɽ��� 			baseFee

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
