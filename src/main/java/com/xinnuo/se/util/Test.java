/**
 * @(#)Test.java Copyright Oristand.All rights reserved. This software is the XXX system.
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: demo
 */
/*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2013-11-28     Administrator    Created
 **********************************************
 */

package com.xinnuo.se.util;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2013-11-28
 */
public class Test extends TestCase {

    /**
     * @param args
     */
    protected Log logger = LogFactory.getLog(this.getClass());

    public static void main(String[] args) {
        /*String frccode="401122";
        Calendar calendar = Calendar.getInstance();
        
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String ldate = df.format(calendar.getTime());
        
        df = new SimpleDateFormat("yyyyMM");
        String ym = df.format(calendar.getTime());
        
        df = new SimpleDateFormat("dd");
        String dd = df.format(calendar.getTime());
        String basePath="";
        String os_name = System.getProperties().getProperty("os.name");
        if (os_name.indexOf("Linux") >= 0) {
            basePath = "/home/ncmmp/log/";
        } else if (os_name.indexOf("Windows") >= 0) {
            basePath = "D:/jsjbInterface/";
        }
        
        
        basePath = basePath+ym+"/"+dd+"/"+frccode;
        System.out.println(basePath);*/
        /*String personCode = "xyh41012211613201";
        System.out.println(personCode.indexOf("4"));*/
        /*if(personCode.indexOf("xyh")==0){
            personCode =personCode.substring(3, personCode.length());
        }
        System.out.println(personCode);*/
//        double a =0d;
//        System.out.println(a==0);

     
        /*//获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
*/


    }

    //jdbc批量插入：
    /*public void exec3(Connection conn) {
        try {
            conn.setAutoCommit(false);
            Long beginTime = System.currentTimeMillis();
            PreparedStatement pst = conn.prepareStatement("insert into t1(id) values (?)");

            for (int i = 1; i <= 10000; i++) {
                pst.setInt(1, i);
                pst.addBatch();// 加入批处理，进行打包
                if (i % 1000 == 0) {// 可以设置不同的大小；如50，100，500，1000等等
                    pst.executeBatch();
                    conn.commit();
                    pst.clearBatch();
                }
            }
            pst.executeBatch();
            Long endTime = System.currentTimeMillis();
            System.out.println("pst+batch用时：" + (endTime - beginTime) + "毫秒");
            pst.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/


    //spring ibatis批量插入
    /*public void insertTreeCateBatch(final List<TreeCate> TreeCateList) throws DataAccessException {
        this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (TreeCate TreeCate : TreeCateList) {
                    // 调用获取sequence的方法。如果没有的话就去掉这行代码。
                    TreeCate.setTreeCateId(getNextId());
                    // 参数1为：ibatis中需要执行的语句的id
                    executor.insert("TreeCate_insertTreeCate", TreeCate);
                    batch++;
                    // 每500条批量提交一次。
                    if (batch == 500) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }*/
    
    /*public class LocalDaoImpl extends SqlMapClientDaoSupport implements LocalDao {

        public void insertBuNaTaxBatLst(final PaginatedList list) {
            getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
                public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                    executor.startBatch();
                    // do some iBatis operations here
                    for (int i = 0, count = list.size(); i < count; i++) {
                        executor.insert("insertBuNaTaxBatLst", list.get(i));
                        if (i % 50 == 0) {
                            System.out.println("----" + i);//没有意义只为测试
                        }
                    }
                    executor.executeBatch();
                    return null;
                }
            });
        }

    }*/
    
   /* public String dealWithGash(String oxml) {
        oxml = oxml.substring(oxml.indexOf("&lt;"));

        oxml = oxml.substring(0, oxml.indexOf("</return>"));

        oxml = oxml.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");

     
        return oxml;
    }*/

    /*
     * 如果端口号、ip写错，能识别；
     */
   /* public void testGetWebServiceAddress() 
        {   
            Boolean flag=true;
            String urlStr="http://172.16.70.69:8003/nccminterface_haoyou_cs/service/HospitalService?wsdl";
            URL url;
            HttpURLConnection url_con = null;
            String response_content;
            long begin =0;
            long end =0;
            int connectionInt=500;
            try {
                 begin = System.currentTimeMillis();
                // 打开远程连接
                url = new URL(urlStr);
                url_con = (HttpURLConnection) url.openConnection();
                
                 * 如果不设置以下两个时间，可能会导致程序僵死而不继续往下执行
                 
                url_con.setConnectTimeout(5000);
                url_con.setReadTimeout(5000);
//                url_con.connect();//此方法不好用。
                int size = url_con.getHeaderFields().size();
                if (size > 0) {
                    connectionInt = url_con.getResponseCode();
                }
                System.out.println("" + connectionInt);
                url_con.disconnect();
            } catch (Exception e) {
                System.out.println("HTTP网站无效或无法访问!");
            } finally {
                if (url_con != null){
                    url_con.disconnect();
                }
                end= System.currentTimeMillis();
                System.out.println("验证时间==="+(end-begin));
            }
            
        }*/

    /*
     * 项目名、端口号、ip写错了，都识别出来了。500、404
     */
        /*public void testGetWSBySocket() {
            int connectionInt = 500;
            String urlString = "http://172.23.0.145:5001/nccminterface_haoyou_xinxiang/service/HospitalService?wsdl";
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                int size = conn.getHeaderFields().size();
                if (size > 0) {
                    connectionInt = conn.getResponseCode();
                }
            } catch (IOException x) {
                x.printStackTrace();
            }
            System.out.println(connectionInt);
        }*/

    /*
     * 验证https网站是否能否访问。
     */
        /*public void testGetUrlsConnection() {
        String urlString = "https://www.alipay.com";
        int connectionInt = 500;
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            // conn.connect();

            int size = conn.getHeaderFields().size();
            if (size > 0) {
                connectionInt = conn.getResponseCode();
            }
            System.out.println("" + connectionInt);
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("HTTPS网站无效或无法访问!");
            System.out.println(e);
        }
    }*/
    
    /*public void testIPPort() {
        TestLog4j t = new TestLog4j();
        Boolean statutJK = false;
        long status = 0L;
        try {
            Socket clientSocket = new Socket("172.23.01.145", 5001);
            clientSocket.close();
            statutJK = true;
            status = 1L;
        } catch (Exception e) {
            statutJK = false;
            status = 2L;
            logger.error("连接不通");
            e.printStackTrace();
        }
    }*/
    
   /* public void testDB(){
        Boolean statutJK =false;
        long keStatus=0L;
        try{
             Class.forName("oracle.jdbc.driver.OracleDriver");
             String url="jdbc:oracle:thin:@"+ip地址+":"+端口     +":"+数据库名            System.out.println("select count(*) from "+表名);
             Connection conn = DriverManager.getConnection(url,用户名,密码);
             PreparedStatement ps =conn.prepareStatement("select count(*) from "+表名);
             ResultSet rs = ps.executeQuery();
             rs.next();
             System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+rs.getInt(1));
             rs.close();
             conn.close();
             statutJK = true;
             keStatus=1L;
            }catch(Exception e){
              //不可用
            }
    }*/
    
   /* public void testDouble(){
        Double a=0.0d;
        Double b=0.0d;
        Double c=0.0d;
        BigDecimal a=new BigDecimal(0);
        BigDecimal b=new BigDecimal(0);
        BigDecimal c=new BigDecimal(0);
        a=new BigDecimal((1-0.8)*31.14*2);
        b=new BigDecimal(0.2*31.14*2);
        c=new BigDecimal(1-0.8);
        a = (1 - 0.8) * 31.14 * 2;
        b = (0.2 * 31.14 * 2);
        c = 1 - 0.8;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        
        
        BigDecimal a1=new BigDecimal(1);
        BigDecimal b1=new BigDecimal(0.8);
        BigDecimal c1=new BigDecimal(31.14);
        BigDecimal d1=new BigDecimal(2);
        
        System.out.println(a1.subtract(b1).multiply(c1).multiply(d1).floatValue());
        
    }*/
    
    /*public void testFloat(){
//        Float a=0.0f;
//        Float b=0.0f;
//        Float c=0.0f;
        BigDecimal a=new BigDecimal("0");
        BigDecimal b=new BigDecimal("0");
        BigDecimal c=new BigDecimal("0");
        a=new BigDecimal(String.valueOf((1-0.8)*31.14*2));
        b=new BigDecimal(String.valueOf(0.2*31.14*2));
        c=new BigDecimal(String.valueOf(1-0.8));
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        
        a=new BigDecimal("1").subtract(new BigDecimal("0.8")).multiply(new BigDecimal("31.14")).multiply(new BigDecimal("2"));
        b=new BigDecimal("0.2").multiply(new BigDecimal("31.14")).multiply(new BigDecimal("2"));
        c=new BigDecimal("1").subtract(new BigDecimal("0.8"));
//        a = Float.valueOf(String.valueOf((1 - 0.800) * 31.14 * 2));
//        b = Float.valueOf(String.valueOf((0.200 * 31.14 * 2)));
//        c = Float.valueOf(String.valueOf((1 - 0.8)));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        
        

        
    }*/
   /* public void testMap(){
        HashMap map =new HashMap();
        Object a = map.get("a")==null?"":map.get("a").toString();
        System.out.println(a);
    }*/
    
   /* public void testSSWR(){
        //测试四舍五入
        //有问题
        BigDecimal bg = new BigDecimal(2995.705);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
        
       //有问题
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(2995.705));
        
        //有问题
        System.out.println(String.format("%.2f", 2222995));//但是没有小数尾的时候有问题
        System.out.println(String.format("%.2f", 2222995.705));
        System.out.println(String.format("%.2f", 2222995.706));
        System.out.println(String.format("%.2f", 2222995.719));
        
        //有问题
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(2995.705));
        
        double myNum2 = 111231.5580578;  
        double myNum2 = 2995.705;  
        
        java.math.BigDecimal b = new java.math.BigDecimal(myNum2);  
        double myNum3 = b.setScale(4, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();  
        System.out.println(myNum3);  
        
        //没问题
        double b =0.00d;
        b= (double)Math.round(2995.709*100)/100;
        System.out.println(b);
        
        //没问题
        double result = 0.00d;
        double source = Double.valueOf(2995.709);
        int res = (int) Math.pow(10, 2);
        result = (double) Math.round(source * res) / res;
        System.out.println(result);
        BigDecimal tmpPrice1 = new BigDecimal("0");
        BigDecimal tmpPrice2 = new BigDecimal("0");
        BigDecimal tmpPrice3 = new BigDecimal("0");
        BigDecimal zfje = new BigDecimal("10");
        BigDecimal tmpAmount = new BigDecimal("6");
        tmpPrice1 = zfje.divide(tmpAmount,4,RoundingMode.HALF_DOWN);
        tmpPrice2 = zfje.divide(tmpAmount,4,RoundingMode.HALF_EVEN);
        tmpPrice3 = zfje.divide(tmpAmount,4,RoundingMode.HALF_UP);
        System.out.println(tmpPrice1);
        System.out.println(tmpPrice2);
        System.out.println(tmpPrice3);
    }*/
    
    
    /*public void testFloat(){
        float a =14.448f;
        double zfje =14.448d;
//        boolean zfcxjflag = zfje > a ? true : false;
//        System.out.println(zfcxjflag);
        System.out.println(Math.abs(zfje - a));
        if (Math.abs(zfje - a) < 0.0001){
            System.out.println(zfje);
            System.out.println(a);
            System.out.println("等于！");
        }
    }*/

    public void testIBATISString() {
        String sourceString = "UPDATE N701  SET        FINHOSPID=?,     N701_01 = ?,     N701_02 = ?,     N701_03 = ?,     N701_04 = ?,     N701_05" +
                " = ?,     N701_06 = ?,     N701_07 = ?,     N701_08 = ?,     N701_09 = ?,     N701_10 = ?,     N701_11 = ?,     N701_12 = ?,     " +
                "N701_13 = ?,     N701_14 = ?,     N701_15 = ?,     N701_16 = ?,     N701_17 = ?,     N701_18 = ?,     N701_19 = ?,     N701_20 = " +
                "?,     N701_21 = ?,     N701_22 = ?,     N701_23 = ?,     N701_24 = ?,     N701_25 = TO_DATE(?,'YYYY-MM-DD'),     N701_26 = " +
                "TO_DATE(?,'YYYY-MM-DD'),     N701_27 = TO_DATE(?,'YYYY-MM-DD'),     N701_28 = ?,     N701_29 = ?,     N701_30 = ?,     N701_31 = " +
                "?,     N701_32 = ?,     N701_33 = ?,     N701_34 = ?,     N701_35 = ?,     N701_36 = ?,     N701_37 = ?,     N701_38 = ?,     " +
                "N701_39 = ?,     N701_40 = ?,     N701_41 = ?,     N701_42 = ?,     N701_43 = ?,     N701_44 = ?,     N701_45 = ?,     N701_46 = " +
                "?,     N701_48 = ?,     N701_49 = ?   WHERE N701_01 = ? and N701_02 = ?  ";
        String paraString = "[19, 000, 485000307, 安徽省立医院, 1, 省, 000004, 赵单, 郑首, 1, 男, 410104198910202003, 59, 明珠花园, 410000, 河南省, 411500, 向阳县, " +
                "411525, 向阳县, linkman, 18999999999, 3, 体格检查, 赵晓, 2015-01-12, 2015-02-18, 2015-02-18, 001, 620104198910202003, NOO234, 终末期肾病, " +
                "NOO222, , NOO235, 动静脉内瘘手术, 已出院, , , 02, 全科医疗科, 1, 危, 3, 一般, NOO234, 心肌梗塞, , 3557.0, 000, 485000307]";

        String[] sourceArr = sourceString.trim().split("\\?");
        String[] paraArr = paraString.replace("[", "").replace("]", "").split(",");
        StringBuffer sb = new StringBuffer();
        int sourceArrL = sourceArr.length;
        int paraArrL = paraArr.length;
        for (int i = 0; i < paraArrL; i++) {
            paraArr[i] = paraArr[i].trim();
            paraArr[i] = (paraArr[i].equals("null") ? "" : paraArr[i]);
            sb.append(sourceArr[i] + "\'" + paraArr[i] + "\'");
        }
        if (sourceArrL > paraArrL) {
            sb.append(");");
        }
        System.out.println(sb.toString());
    }
    
    /*public void testGetWebServiceAddress() 
    {   
        Boolean flag=true;
        String urlStr="http://125.46.57.77:8000/xyhnccmp2/HospitalServicePort?wsdl";
        URL url;
        HttpURLConnection url_con = null;
        String response_content;
        long begin =0;
        long end =0;
        int connectionInt=500;
        try {
             begin = System.currentTimeMillis();
            // 打开远程连接
            url = new URL(urlStr);
            url_con = (HttpURLConnection) url.openConnection();
            
             * 如果不设置以下两个时间，可能会导致程序僵死而不继续往下执行
             
            url_con.setConnectTimeout(8000);
            url_con.setReadTimeout(8000);
//            url_con.connect();//此方法不好用。
            int size = url_con.getHeaderFields().size();
            if (size > 0) {
                connectionInt = url_con.getResponseCode();
            }
            System.out.println("" + connectionInt);
            url_con.disconnect();
        } catch (Exception e) {
            System.out.println("HTTP网站无效或无法访问!");
        } finally {
            if (url_con != null){
                url_con.disconnect();
            }
            end= System.currentTimeMillis();
            System.out.println("验证时间==="+(end-begin));
        }
        System.out.println(connectionInt);
        
    }*/
    
    /*public void testString(){
        String s ="&lt;";
        System.out.println(s.indexOf("&lt;"));
        if(s.indexOf("0")==0&&s.length()>1){
            System.out.println(s.substring(1));
        }
        
    }*/
    
   /* public void testDateCompare() throws ParseException{
        java.util.Date nowdate=new java.util.Date(); 
        String myString1 = "20080908101010";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        String myString2 = "20080909101010";
        Date d1 = sdf.parse(myString1);
        Date d2 = sdf.parse(myString2);

        boolean flag = d1.equals(d2);
        if(d1.equals(d2))
            System.out.print("相等");
        else if(d1.before(d2))
            System.out.print("d1早");
        else 
            System.out.print("d2早");
    }*/
    /*public void testStringRep(){
        String source="this is a good day!";
        System.out.println(source.replace("good", "bad"));
        
    }*/

    /*
     * 多对齐，左补0
     */
    
    /*public void testStringPad(){
        int number1 = 5;     
        int number2 = 0;     
        int number3 = -12;     
        System.out.println("\n------------------  方法 1  ------------\n");     
        java.text.DecimalFormat format = new java.text.DecimalFormat("0000");     
        System.out.println(format.format(number1));     
        System.out.println(format.format(number2));     
        System.out.println(format.format(number3));     
        System.out.println("\n------------------  方法 2  ------------\n");     
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型     
         String str1 = String.format("%04d", number1);     
         String str2 = String.format("%04d", number2);     
         String str3 = String.format("%04d", number3);     
         System.out.println(str1);     
         System.out.println(str2);     
         System.out.println(str3); 
    }
    */




    /* 左对齐，右补空格
     *  c 要填充的字符
     *  length 填充后字符串的总长度
     *  content 要格式化的字符串
     *  格式化字符串，左对齐
     * */
     /*public void testFlushLeft() {
        char c = ' ';
        long length = 10;
        String content = "x";
        String str = "";
        long cl = 0;
        String cs = "";
        if (content.length() > length) {
            str = content;
        } else {
            for (int i = 0; i < length - content.length(); i++) {
                cs = cs + c;
            }
        }
        str = content + cs;
        System.out.println("左对齐，右补空格" + str);
    } */
    
    
   /* public void  testMapToSocketString() throws Exception {
        LinkedHashMap paraMap=new LinkedHashMap();
        paraMap.put("a", "bank|24");
        paraMap.put("b", "10|28|n");
        StringBuffer sb = new StringBuffer();
        String value = "";
        int length = 0;
        String type = "";
        for (Iterator it = paraMap.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            String valueString = paraMap.get(key).toString();
            System.out.println("值串=="+valueString);
            String[] sourceArr = valueString.split("\\|");
            if (sourceArr.length > 1) {
                value = sourceArr[0];// 值
                length = Integer.valueOf(sourceArr[1]);// 长度
                if (sourceArr.length > 2) {
                    type = sourceArr[2];// 类型
                }
                
            } else {
                throw new Exception("Map参数格式异常，请指定参数" + key + "长度");
            }
            if ("s".equalsIgnoreCase(type) || "".equals(type)) {// 如果为空或者是s，表示是字符串：左对齐,右面用空格补齐。
                sb.append(ToolUtils.rightPad(value, length, "x"));
            } else if ("n".equalsIgnoreCase(type)) {// 如果是数值，右对齐，左补0
                sb.append(ToolUtils.leftPad(value, length, "0"));
            }
        }
        System.out.println("===="+sb.toString());
    }*/
    
    /*public void testStringToSocketMap() throws Exception {
        String func_targetString = "bankxxxxxxxxxxxxxxxxxxxx0000000000000000000000000010what";
        String func_mapname = "a,b,c";
        String func_maplength = "24,28,4";
        String[] func_mapnamearr = func_mapname.split(",");
        String[] func_maplengtharr = func_maplength.split(",");
        if (func_mapnamearr.length != func_maplengtharr.length) {
            throw new Exception("【配置文件中：字段名称】和【配置文件中：字段长度参数】不匹配！");
        }
        int sum = 0;
        int start = 0;
        int end = 0;
        String mapvalue = "";
        LinkedHashMap paraMap = new LinkedHashMap();
        for (int i = 0; i < func_maplengtharr.length; i++) {// 长度字符串
            int startTemp = (i - 1 < 0 ? 0 : Integer.valueOf(func_maplengtharr[i - 1]));
            start += startTemp;
            sum += Integer.valueOf(func_maplengtharr[i]);
            end = sum;
            try {
                mapvalue = func_targetString.substring(start, end);
            }catch(Exception e){
                throw new Exception("【配置文件中：字段长度参数】和【实际字符串长度】不匹配！");
            }
            paraMap.put(func_mapnamearr[i], mapvalue);
        }
        Set<String> set = new HashSet<String>();
        set= paraMap.keySet();
        for (String key : set) {
            System.out.println(key+"="+paraMap.get(key));
        }
    }*/
    
    /*public void testMap(){
        System.out.println("----------区分大小写的map----------------");
        Map a =new HashMap();
        a.put("a", "a");
        a.put("A", "A");
        System.out.println(a.get("a"));
        System.out.println(a.get("A"));
        System.out.println("----------不区分大小写的map----------------");
        Map<String, Object> b = new CaseInsensitiveMap(); 
        b.put("a", "a");
        b.put("A", "ccc");
        System.out.println(b.get("a"));
        System.out.println(b.get("A"));
    }*/
    
    /*public void testGetDate() throws Exception{
        String datePatten="yyyyMMddHHmmssSSS";
        String datePatten1="yyyyMMddHHmmss";
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(datePatten1);
        String ldate = df.format(calendar.getTime());
        System.out.println("yyyyMMddHHmmss========="+ldate);
        
        
        
        df = new SimpleDateFormat("yyyyMM");
        String ym = df.format(calendar.getTime());
        System.out.println("yyyyMM========="+ym);
        df = new SimpleDateFormat("dd");
        String dd = df.format(calendar.getTime());
        System.out.println("dd========="+dd);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//设置日期格式
  
        String nyr = sdf.format(new Date());
        System.out.println("yyyyMMdd========="+nyr);
        
        java.util.Date nowdate=new java.util.Date(); 
        String myString1 = "20080908";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        String myString2 = "20080908";
        Date d1 = sdf.parse(myString1);
        Date d2 = sdf.parse(myString2);
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String dateStr = sdf2.format(d1);
        System.out.println("=="+dateStr);
        
        
    }*/
    
   /* public void testStringLengthCN() throws UnsupportedEncodingException{
        String str ="02报文头与报文体内容不符
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     00                                        ";
        System.out.println(str.length());
        str = new String(str.getBytes("GBK"),"ISO8859_1");
        System.out.println(str.length());
    }*/
    /*public void testSubStringCN() throws UnsupportedEncodingException{
        String str ="02报文头与报文体内容不符
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     00                                        ";
        str = new String(str.getBytes("GBK"),"ISO8859_1");
        str =str.substring(0, 3);
        System.out.println(str);
    }*/
   
  /*public void testSplit(){
       //分割字符串 转义字符
       
     //String a="4114241510010002&1&187229&河南省第三&247"; 
    String a="4114241510010002|0"; 
     String [] b =a.split("\\|"); 
     System.out.println(b[0]);
    //String a ="每个";
    //String b ="48589387-001";
    //System.out.println(a.getBytes().length);
    //System.out.println(a.substring(0,5));
   }*/
   
   /*public void testIndexof(){
       String targetstr="就诊类型：普通住院 (2206.49-1500.0)*0.5+中医项目优惠:32.82";
       int index_zyxm = targetstr.indexOf("中医项目优惠");
       String targetstr_sub = targetstr.substring(index_zyxm,targetstr.length());
       int index_zyxm_bl_end = targetstr_sub.indexOf(" ");
       int index_zyxm_bl_beg = targetstr_sub.indexOf(":");
       System.out.println("index_zyxm_bl_end=="+index_zyxm_bl_end);
       if( index_zyxm_bl_end ==-1) {//查不到就是-1
           index_zyxm_bl_end=targetstr_sub.length();
       }
       else {
           index_zyxm_bl_end=index_zyxm_bl_end;
       }
       String fina = targetstr_sub.substring(index_zyxm_bl_beg+1, index_zyxm_bl_end);
       System.out.println(fina);
   }*/
   
   /*public void testBase641(){
       String target="PD94bWwgdmVyc2lvbj0nMS4wJyBlbmNvZGluZz0nR0JLJz8+PGdldFVuQXBwbHlSZWRlZW1EYXRhPg==PHJlc3VsdD4=PGZsYWc+MDwvZmxhZz4
       =PHRvdGFsUGVyc29uPjY8L3RvdGFsUGVyc29uPg==PG1lc3NhZ2U+stnX97PJuaY8L21lc3NhZ2U+PC9yZXN1bHQ+PHBlcnNvbmJhc2VpbmZvPg
       ==PGJvb2tubz4zNDEyODEwNzA4MDMwNzg4PC9ib29rbm8+PG1lbWJlck5vPjAyNzAwODYxMzAzPC9tZW1iZXJObz4=PG5hbWU+wO7Kv7rPPC9uYW1lPg==PHNleE5hbWU
       +xNA8L3NleE5hbWU+PGJpcnRoZGF5PjE5NDYtMTEtMDU8L2JpcnRoZGF5Pg==PG1hc3Rlck5hbWU+wO7Kv7rPPC9tYXN0ZXJOYW1lPg
       ==PHJlbGF0aW9uTmFtZT4wPC9yZWxhdGlvbk5hbWU+PGlkZW50aXR5TmFtZT5udWxsPC9pZGVudGl0eU5hbWU+PGlkQ2FyZD4zNDEyODExOTQ2MTEwNTYwNzg8L2lkQ2FyZD4
       =PGFkZHJlc3M+bnVsbDwvYWRkcmVzcz4=PGRvb3JQcm9wTmFtZT61zbGju6c8L2Rvb3JQcm9wTmFtZT4=PC9wZXJzb25iYXNlaW5mbz4=PHJlZGVlbURhdGFzPg==PHJlZGVlbURhdGE
       +PGlucGF0aWVudFNuPjYyMTg2MDwvaW5wYXRpZW50U24+PGhvc09yZ25vPjM0MTYwMjQ4NTg5MTA3LTQ8L2hvc09yZ25vPg==PGhvc09yZ05hbWU
       +2fHW3crQu6rZotbQ0r3UujwvaG9zT3JnTmFtZT4=PHRvdGFsQ29zdHM+MTcyMi41MTwvdG90YWxDb3N0cz4=PEVuYWJsZU1vbmV5PjE1NjUuMDE8L0VuYWJsZU1vbmV5Pg
       ==PHN0YXJ0TW9uZXk+NTIwLjAwPC9zdGFydE1vbmV5Pg==PHJlZGVlbU1vbmV5Pjk0My45MDwvcmVkZWVtTW9uZXk+PHBlcnNvbmFsUGF5TW9uZXk
       +Nzc4LjYxPC9wZXJzb25hbFBheU1vbmV5Pg==PGljZG5hbWU+SzM3IDAxPC9pY2RuYW1lPg==PG9mZmljZURhdGU+MjAxNC0wNi0xMCAwMDowMDowMDwvb2ZmaWNlRGF0ZT4
       =PGxlYXZlRGF0ZT4yMDE0LTA2LTEyIDAwOjAwOjAwPC9sZWF2ZURhdGU+PFJlZGVlbVR5cGVDb2RlPjI8L1JlZGVlbVR5cGVDb2RlPgE0LTA5LTIxIDAwOjAwOjAwPC9sZWF2ZURhdGU
       +PFJlZGVlbVR5cGVDb2RlPjMyPC9SZWRlZW1UeXBlQ29kZT4=PHJlZGVlbURhdGU+MjAxNC0wOS0yMSAxMTo0NToyNzwvcmVkZWVtRGF0ZT4=PC9yZWRlZW1EYXRhPg
       ==PHJlZGVlbURhdGE+PGlucGF0aWVudFNuPjcwMjIzMzwvaW5wYXRpZW50U24+PGhvc09yZ25vPjM0MTYwMjQ4NTAwMDk5LTk8L2hvc09yZ25vPg==PGhvc09yZ05hbWU
       +sLK71cqh1tfB9tK91LqjqMqhwaLSvdS6zvfH+KOpPC9ob3NPcmdOYW1lPg==PHRvdGFsQ29zdHM+NTc1OS40MTwvdG90YWxDb3N0cz4
       =PEVuYWJsZU1vbmV5PjcwMy44NTwvRW5hYmxlTW9uZXk+PHN0YXJ0TW9uZXk+MC4wMDwvc3RhcnRNb25leT4=PHJlZGVlbU1vbmV5PjIzMDMuODA8L3JlZGVlbU1vbmV5Pg
       ==PHBlcnNvbmFsUGF5TW9uZXk+MzQ1NS42MTwvcGVyc29uYWxQYXlNb25leT4=PGljZG5hbWU+Wjk4LjgwMTwvaWNkbmFtZT4=PG9mZmljZURhdGU
       +MjAxNC0xMC0zMCAwMDowMDowMDwvb2ZmaWNlRGF0ZT4=PGxlYXZlRGF0ZT4yMDE0LTExLTAzIDAwOjAwOjAwPC9sZWF2ZURhdGU
       +PFJlZGVlbVR5cGVDb2RlPjMyPC9SZWRlZW1UeXBlQ29kZT4=PHJlZGVlbURhdGU+MjAxNC0xMS0wMyAxMDo1OTowMjwvcmVkZWVtRGF0ZT4=PC9yZWRlZW1EYXRhPg
       ==PHJlZGVlbURhdGE+PGlucGF0aWVudFNuPjcxNzM3NzwvaW5wYXRpZW50U24+PGhvc09yZ25vPjM0MTYwMjQ4NTAwMDk5LTk8L2hvc09yZ25vPg==PGhvc09yZ05hbWU
       +sLK71cqh1tfB9tK91LqjqMqhwaLSvdS6zvfH+KOpPC9ob3NPcmdOYW1lPg==PHRvdGFsQ29zdHM+NTk0NS44MDwvdG90YWxDb3N0cz4
       =PEVuYWJsZU1vbmV5PjczOS40ODwvRW5hYmxlTW9uZXk+PHN0YXJ0TW9uZXk+MC4wMDwvc3RhcnRNb25leT4=PHJlZGVlbU1vbmV5PjIzNzguMzA8L3JlZGVlbU1vbmV5Pg
       ==PHBlcnNvbmFsUGF5TW9uZXk+MzU2Ny41MDwvcGVyc29uYWxQYXlNb25leT4=PGljZG5hbWU+Wjk4LjgwMTwvaWNkbmFtZT4=PG9mZmljZURhdGU
       +MjAxNC0xMS0yNCAwMDowMDowMDwvb2ZmaWNlRGF0ZT4=PGxlYXZlRGF0ZT4yMDE0LTExLTI3IDAwOjAwOjAwPC9sZWF2ZURhdGU
       +PFJlZGVlbVR5cGVDb2RlPjMyPC9SZWRlZW1UeXBlQ29kZT4=PHJlZGVlbURhdGU+MjAxNC0xMS0yNyAxMTo1NjowOTwvcmVkZWVtRGF0ZT4=PC9yZWRlZW1EYXRhPg
       ==PC9yZWRlZW1EYXRhcz4=PC9nZXRVbkFwcGx5UmVkZWVtRGF0YT4=";
       String nextStr="";
       String lastStr="";
       String firstStr="";
       do{
           int index_zyxm = -1;
           index_zyxm =   target.indexOf("=");
           if((target.length()-1)!=index_zyxm){
               nextStr = target.substring(index_zyxm+1,index_zyxm+2);//等号下一个字符
           }else{
               nextStr="";
           }
           if("=".equals(nextStr)){
               firstStr=target.substring(0, index_zyxm+2);//等号之前的字符串
               lastStr =target.substring(index_zyxm+2);//等号余下发字符串
           }else{
               firstStr=target.substring(0, index_zyxm+1);//等号之前的字符串
               lastStr =target.substring(index_zyxm+1);//等号余下发字符串
           }
           target = lastStr;
           System.out.println("firstStr"+firstStr);
       }while(lastStr.length()>0);
   }*/
   
   
   /*public void testBase64tow() throws UnsupportedEncodingException{
       //String a ="2";
       String a ="啊";
       //String a ="22啊";
       
       byte[] byte_rtn0 = new byte[0];
       byte[] byte_rtn1 = new byte[0];
       byte[] byte_rtn2 = new byte[0];
       byte[] byte_rtn3= new byte[0];
       System.out.println(Charset.defaultCharset());
       byte_rtn0=a.getBytes();
       byte_rtn1=a.getBytes("UTF-8");
       byte_rtn2=a.getBytes("GBK");
       byte_rtn3=a.getBytes("ISO8859-1");
       System.out.println(a);
   }*/
    
    /*public void testDouble(){
        double a =Double.POSITIVE_INFINITY;//初始化为无限大
        double c;
        int b =1;
        if(b>a){
            System.out.println("b大");
        }else{
            System.out.println("b不大");
        }
    }*/
    
   /* public void testString(){
        String a="a";
        StringBuffer sb=new StringBuffer();
        sb.append("sb");
        
        int inta  =-2147483648  ;
        int intb=Integer.MAX_VALUE;
        int b =Math.abs(inta);//溢出
        System.out.println(intb);
        
        
        int []a = new int[2];
        a[1]=1;//声明的时候仅仅指明长度，再一个一个赋值
        int []b={1,2};//声明的时候马上赋值。
    }*/
    /*public void testAssert(){
        //断言1结果为true，则继续往下执行
        int b =1;
        assert  b==1:"断定b不等1";
        System.out.println("断言1没有问题，Go！");
        System.out.println("\n-----------------\n");
 
        //断言2结果为false,程序终止
        assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
        System.out.println("断言2没有问题，Go！");
    }*/
    
    /*public void testE(){
        String sjiachun = "12345E-10";
        BigDecimal db = new BigDecimal(sjiachun);
        String ii = db.toPlainString();
        System.out.println(db);
        System.out.println(ii);

    }*/
    
   
   /*public  void testString() {
       String b ="";
       String a ="1"+b;
       System.out.println(a);
       b="999";
       System.out.println(a);
       
       String c="1233$456";
       System.out.println(c.replace("$", "&"));
       System.out.println(c);
   } */

    public void testGetWebServiceAddress() {
        Boolean flag = true;
        System.out.println("-------");
        String urlStr = "http://172.23.0.145:6001/nccminterface_haoyou_xinxiang/service/HospitalService?wsdl";
        URL url;
        HttpURLConnection url_con = null;
        String response_content;
        long begin = 0;
        long end = 0;
        int connectionInt = 500;
        try {
            begin = System.currentTimeMillis();
            // 打开远程连接
            url = new URL(urlStr);
            url_con = (HttpURLConnection) url.openConnection();
            /*
             * 如果不设置以下两个时间，可能会导致程序僵死而不继续往下执行
             */
            url_con.setConnectTimeout(8000);
            url_con.setReadTimeout(8000);
            // url_con.connect();//此方法不好用。
            int size = url_con.getHeaderFields().size();
            if (size > 0) {
                connectionInt = url_con.getResponseCode();
            } else {
                throw new RuntimeException("新农合专网连接超时，请核查接口设置或网络设置！");
            }
            if (connectionInt == 500) {
                throw new RuntimeException("无法建立新农合专网连接，请核查接口设置或网络设置！" + connectionInt);
            }
            url_con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
            end = System.currentTimeMillis();
        }
    }
}
    
