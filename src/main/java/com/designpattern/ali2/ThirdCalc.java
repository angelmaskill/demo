package com.designpattern.ali2;

public class ThirdCalc extends Handler {
    final double compareFee = 1000000d;

    @Override
    public String handleFeeRequest(double addFee) {

        double curFee = this.getCurrentFee();
        double diffFee = (curFee + addFee) - compareFee;
        String str = "";
        if (diffFee > compareFee) {
            double less1W = (compareFee - curFee);// compareFee 以内的钱
            double more1w = curFee + addFee - compareFee;// compareFee 以外的钱
            this.setCurrentFee(curFee + 10000 * 0.05 + (1000000 - 10000) * 0.04 + more1w * 0.02);
            System.out.println("当前阶段:第三段" + ";当前金额" + this.getCurrentFee() + "=计算公式:" + curFee + "+10000 * 0.05+(1000000 -10000)*0.04 +" + more1w + " * 0.02");
        } else {
            if (getSuccessor() != null) {
                return getSuccessor().handleFeeRequest(addFee);
            }
        }
        return str;
    }

}  