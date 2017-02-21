
动态代理工具比较成熟的产品有：
JDK自带的，ASM，CGLIB(基于ASM包装)，JAVAASSIST，
使用的版本分别为：
JDK-1.6.0_18-b07, ASM-3.3, CGLIB-2.2, JAVAASSIST-3.11.0.GA

(一) 测试结果：
数据为执行三次，每次调用一千万次代理方法的结果，测试代码后面有贴出。
(1) PC机测试结果：Linux 2.6.9-42.ELsmp(32bit), 2 Cores CPU(Intel Pentium4 3.06GHz)
(2) 服务器测试结果：Linux 2.6.18-128.el5xen(64bit), 16 Cores CPU(Intel Xeon E5520 2.27GHz)



(二) 测试结论：
1. ASM和JAVAASSIST字节码生成方式不相上下，都很快，是CGLIB的5倍。
2. CGLIB次之，是JDK自带的两倍。
3. JDK自带的再次之，因JDK1.6对动态代理做了优化，如果用低版本JDK更慢，要注意的是JDK也是通过字节码生成来实现动态代理的，而不是反射。
4. JAVAASSIST提供者动态代理接口最慢，比JDK自带的还慢。
(这也是为什么网上有人说JAVAASSIST比JDK还慢的原因，用JAVAASSIST最好别用它提供的动态代理接口，而可以考虑用它的字节码生成方式)

(三) 差异原因：
各方案生成的字节码不一样，
像JDK和CGLIB都考虑了很多因素，以及继承或包装了自己的一些类，
所以生成的字节码非常大，而我们很多时候用不上这些，
而手工生成的字节码非常小，所以速度快，
具体的字节码对比，后面有贴出，可自行分析。

(四) 最终选型：
最终决定使用JAVAASSIST的字节码生成代理方式，
虽然ASM稍快，但并没有快一个数量级，
而JAVAASSIST的字节码生成方式比ASM方便，
JAVAASSIST只需用字符串拼接出Java源码，便可生成相应字节码，
而ASM需要手工写字节码。


参考地址:

[动态代理方案性能对比](http://www.lai18.com/content/3251312.html)