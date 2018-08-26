# 介绍
动态代理工具比较成熟的产品有：
JDK自带的，ASM，CGLIB(基于ASM包装)，JAVAASSIST，
使用的版本分别为：
JDK-1.6.0_18-b07, ASM-3.3, CGLIB-2.2, JAVAASSIST-3.11.0.GA


# 1.测试结果：
数据为执行三次，每次调用一千万次代理方法的结果，测试代码后面有贴出。

1. PC机测试结果：Linux 2.6.9-42.ELsmp(32bit), 2 Cores CPU(Intel Pentium4 3.06GHz)
2. 服务器测试结果：Linux 2.6.18-128.el5xen(64bit), 16 Cores CPU(Intel Xeon E5520 2.27GHz)



# 2.测试结论：
1. ASM和JAVAASSIST字节码生成方式不相上下，都很快，是CGLIB的5倍。
2. CGLIB次之，是JDK自带的两倍。
3. JDK自带的再次之，因JDK1.6对动态代理做了优化，如果用低版本JDK更慢，要注意的是JDK也是通过字节码生成来实现动态代理的，而不是反射。
4. JAVAASSIST提供者动态代理接口最慢，比JDK自带的还慢。
(这也是为什么网上有人说JAVAASSIST比JDK还慢的原因，用JAVAASSIST最好别用它提供的动态代理接口，而可以考虑用它的字节码生成方式)

# 3.差异原因：
各方案生成的字节码不一样，
像JDK和CGLIB都考虑了很多因素，以及继承或包装了自己的一些类，
所以生成的字节码非常大，而我们很多时候用不上这些，
而手工生成的字节码非常小，所以速度快，
具体的字节码对比，后面有贴出，可自行分析。

# 4.最终选型：
最终决定使用JAVAASSIST的字节码生成代理方式，
虽然ASM稍快，但并没有快一个数量级，
而JAVAASSIST的字节码生成方式比ASM方便，
JAVAASSIST只需用字符串拼接出Java源码，便可生成相应字节码，
而ASM需要手工写字节码。

# 5.JDK动态代理和CGLIB字节码生成的区别？
## 原理区别
Java动态代理是`利用反射机制生成一个实现代理接口的匿名类`，在调用具体方法前调用InvokeHandler来处理。
而cglib动态代理是利用asm开源包，对`代理对象类的class文件加载进来，通过修改其字节码生成子类`来处理。

1. 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP 
2. 如果目标对象实现了接口，可以强制使用CGLIB实现AOP 
3. 如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换

## 如何强制使用CGLIB实现AOP？
1. 添加CGLIB库，SPRING_HOME/cglib/*.jar
2. 在spring配置文件中加入`<aop:aspectj-autoproxy proxy-target-class="true"/>`
 
 
## 实现区别
1. JDK动态代理只能对实现了接口的类生成代理，而不能针对类
2. CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
   因为是继承，所以该类或方法最好不要声明成final 


   
JDK代理是不需要以来第三方的库，只要要JDK环境就可以进行代理，它有几个要求
* 实现InvocationHandler 
* 使用Proxy.newProxyInstance产生代理对象
* 被代理的对象必须要实现接口

CGLib 必须依赖于CGLib的类库，但是它需要类来实现任何接口代理的是指定的类生成一个子类，覆盖其中的方法，是一种继承但是针对接口编程的环境下推荐使用JDK的代理

## 案例
在Hibernate中的拦截器其实现考虑到不需要其他接口的条件Hibernate中的相关代理采用的是CGLib来执行。   

# 参考地址:

* [动态代理方案性能对比](http://www.lai18.com/content/3251312.html)
* [Spring的两种代理JDK和CGLIB的区别浅谈](http://blog.csdn.net/u013126379/article/details/52121096)