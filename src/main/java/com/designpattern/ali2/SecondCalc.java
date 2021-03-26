package com.designpattern.ali2;

public class SecondCalc extends Handler {

    final double compareFeeBegin = 10000d;
    final double compareFeeEnd = 1000000d;

    @Override
    public String handleFeeRequest(double addFee) {

        String str = "";
        double curFee = this.getCurrentFee();
        double diffFee = (curFee + addFee) - compareFeeBegin;
        if (compareFeeBegin < diffFee && diffFee < compareFeeEnd) {
            double less1W = (compareFeeBegin - curFee);// 1w 以内的钱
            double more1w = curFee + addFee - compareFeeBegin;// 1w 以外的钱
            this.setCurrentFee(curFee + less1W * 0.05 + more1w * 0.04);
            System.out.println("当前阶段:第二段" + ";当前金额" + this.getCurrentFee() + "=计算公式:" + curFee + "+" + less1W
                    + "* 0.05" + "+" + more1w + "* 0.04");
        } else {
            Handler s3 = getSuccessor();
            s3.setCurrentFee(curFee);
            if (s3 != null) {
                return s3.handleFeeRequest(addFee);
            }
        }
        return str;
    }

}  