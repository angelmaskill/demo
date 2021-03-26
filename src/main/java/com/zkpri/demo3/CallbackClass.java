package com.zkpri.demo3;

public class CallbackClass implements MyCallInterface {
    public void method() {
        System.out.println("回调函数");
    }

    public static void main(String args[]) {
        Caller call = new Caller();
        call.setCallfuc(new CallbackClass());
        call.call();
    }
}