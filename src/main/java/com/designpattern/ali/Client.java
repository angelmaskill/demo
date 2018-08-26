package com.designpattern.ali;

import com.designpattern.ali.branch.InterPhone;
import com.designpattern.ali.branch.Iphone;
import com.designpattern.ali.branch.Meizu;
import com.designpattern.ali.calcPric.Calculator;
import com.designpattern.ali.charge.Liantong;
import com.designpattern.ali.os.Ios;
import com.designpattern.ali.os.Yunos;

public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
//		client.testPructPhone();
//		client.testCalcIphone();
		client.testMeizu();
	}

	private void testPructPhone() {
		// TODO Auto-generated method stub
		InterPhone iphone = new Iphone();
		// 生成 iPhone 手机
		Ios ios = new Ios();
		ios.loados(iphone);
		ios.display();
	}

	private void testCalcIphone() {
		Calculator calc = new Calculator();

		// 测试苹果手机
		InterPhone iphone = new Iphone();
		// 生成 iPhone 手机
		Ios ios = new Ios(6000);
		ios.loados(iphone);
		ios.display();
		calc.calc(ios);
		System.out.println("----------最终价格" + ios.getPrice());

	}

	private void testMeizu() {
		InterPhone meizu = new Meizu();
		// 生成 meizu 手机
		Yunos meizuWithYunos = new Yunos(1000);
		meizuWithYunos.loados(meizu);
		meizuWithYunos.display();

		// 测试魅族加载yunos 手机
		Calculator calc = new Calculator();
		System.out.println("----------最终价格" + meizuWithYunos.getPrice());

		// 使用中国联通充值
		calc.setCharge(new Liantong());
		calc.calc(meizuWithYunos);
		System.out.println("----------最终价格" + meizuWithYunos.getPrice());
	}
}
