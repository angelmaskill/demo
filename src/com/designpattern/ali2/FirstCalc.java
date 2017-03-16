package com.designpattern.ali2;

public class FirstCalc extends Handler {

	final double compareFee = 10000d;

	@Override
	public String handleFeeRequest(double addFee) {

		double curFee = this.getCurrentFee();
		double diffFee = (curFee + addFee) - compareFee;
		String str = "";
		if (0 < diffFee && diffFee < compareFee) {
			double less1W = (compareFee - curFee);// 1w 以内的钱
			double more1w = curFee + addFee - compareFee;// 1w 以外的钱
			this.setCurrentFee(curFee + less1W * 0.05 + more1w * 0.04);
			System.out.println("当前阶段:第一段"  + ";当前金额" + this.getCurrentFee() + "=计算公式:" + curFee + "+" + less1W
					+ "* 0.05" + "+" + more1w + "* 0.04");
		} else {
			Handler s2 = getSuccessor();
			s2.setCurrentFee(curFee);
			if (s2 != null) {
				return s2.handleFeeRequest(addFee);
			}
		}
		return str;
	}
}
