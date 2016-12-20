package com.BASETEST.ParameterizedType;

public class Demo4 {
    public static void main(String arsg[]) {
        Info<String> obj = new InfoImp<String>("www.weixueyuan.net");
        System.out.println("Length Of String: " + obj.getVar().length());
    }
}
//定义泛型接口
interface Info<T> {
    public T getVar();
}
//实现接口
class InfoImp<T1> implements Info<T1> {
    private T1 var;
    // 定义泛型构造方法
    public InfoImp(T1 var) {
        this.setVar(var);
    }
    public void setVar(T1 var) {
        this.var = var;
    }
    public T1 getVar() {
        return this.var;
    }
}