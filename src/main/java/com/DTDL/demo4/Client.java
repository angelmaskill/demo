package com.DTDL.demo4;

/**
 * jdk 动态代理
 *
 * @author mayanlu
 */
public class Client {

    public static void main(String[] args) {
        //设置为true,会在工程根目录生成$Proxy0.class代理类（com.sun.proxy.$Proxy0.class）
        System.getProperties().put(
            "sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        String saveGeneratedFiles = System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFiles");
        System.out.println(saveGeneratedFiles);

        // 客户类调用此工厂方法获得代理对象。对客户类来说，其并不知道返回的是代理类对象还是委托类对象。
        Subject proxy = DynProxyFactory.getInstance();
        proxy.dealTask("DBQueryTask");
    }

}