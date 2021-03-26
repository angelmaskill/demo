package com.designpattern.ali2;

public abstract class Handler {
    private double currentFee;

    public Handler() {
        super();
    }

    public Handler(double currentFee) {
        super();
        this.currentFee = currentFee;
    }

    public void init(double initfee) {
        this.currentFee = initfee;
    }

    public double getCurrentFee() {
        return currentFee;
    }

    public void setCurrentFee(double currentFee) {
        this.currentFee = currentFee;
    }

    /**
     * 持有下一个处理请求的对象
     */
    protected Handler successor = null;

    /**
     * 取值方法
     */
    public Handler getSuccessor() {
        return successor;
    }

    /**
     * 设置下一个处理请求的对象
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract String handleFeeRequest(double addFee);
}
