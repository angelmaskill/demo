package com.designpattern.ali2;

public class Client {
	 public static void main(String[] args) {  
	        //先要组装责任链  
	        Handler h1 = new FirstCalc();  
	        Handler h2 = new SecondCalc();  
	        Handler h3 = new ThirdCalc();  
	        //定义处理步骤
	        h1.init(9000);
	        h1.setSuccessor(h2);
	        h2.setSuccessor(h3);
	        
	        //开始测试  
//	        h1.handleFeeRequest( 2000);  //当前阶段:第一段;当前金额9090.0=计算公式:9000.0+1000.0* 0.05+1000.0* 0.04
//	        h1.handleFeeRequest( 120000);  //当前阶段:第二段;当前金额13810.0=计算公式:9000.0+1000.0* 0.05+119000.0* 0.04
	        h1.handleFeeRequest( 2200000);  //当前阶段:第三段;当前金额73280.0=计算公式:9000.0+10000 * 0.05+(1000000 -10000)*0.04 +1209000.0 * 0.02
	    }  
}
