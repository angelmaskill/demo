# Bean的完整生命周期
Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：

1.Bean自身的方法：
这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法

2.Bean级生命周期接口方法：
这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法

3.容器级生命周期接口方法：
这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。

4.工厂后处理器接口方法：
这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。


# 实现*Aware接口 在Bean中使用Spring框架的一些对象

有些时候我们需要在Bean的初始化中使用Spring框架自身的一些对象来执行一些操作，比如获取ServletContext的一些参数，获取ApplicaitionContext中的BeanDefinition的名字，获取Bean在容器中的名字等等。为了让Bean可以获取到框架自身的一些对象，Spring提供了一组名为*Aware的接口。
这些接口均继承于org.springframework.beans.factory.Aware标记接口，并提供一个将由Bean实现的set*方法,Spring通过基于setter的依赖注入方式使相应的对象可以被Bean使用。
网上说，这些接口是利用观察者模式实现的，类似于servlet listeners，目前还不明白，不过这也不在本文的讨论范围内。
介绍一些重要的Aware接口：

ApplicationContextAware: 获得ApplicationContext对象,可以用来获取所有Bean definition的名字。
BeanFactoryAware:获得BeanFactory对象，可以用来检测Bean的作用域。
BeanNameAware:获得Bean在配置文件中定义的名字。
ResourceLoaderAware:获得ResourceLoader对象，可以获得classpath中某个文件。
ServletContextAware:在一个MVC应用中可以获取ServletContext对象，可以读取context中的参数。
ServletConfigAware在一个MVC应用中可以获取ServletConfig对象，可以读取config中的参数。

# BeanPostProcessor

上面的*Aware接口是针对某个实现这些接口的Bean定制初始化的过程，
Spring同样可以针对容器中的所有Bean，或者某些Bean定制初始化过程，只需提供一个实现BeanPostProcessor接口的类即可。 该接口中包含两个方法，postProcessBeforeInitialization和postProcessAfterInitialization。 postProcessBeforeInitialization方法会在容器中的Bean初始化之前执行， postProcessAfterInitialization方法在容器中的Bean初始化之后执行。


# 总结
结合第一节控制台输出的内容，Spring Bean的生命周期是这样纸的：

Bean容器找到配置文件中Spring Bean的定义。
Bean容器利用Java Reflection API创建一个Bean的实例。
如果涉及到一些属性值 利用set方法设置一些属性值。
如果Bean实现了BeanNameAware接口，调用setBeanName()方法，传入Bean的名字。
如果Bean实现了BeanClassLoaderAware接口，调用setBeanClassLoader()方法，传入ClassLoader对象的实例。
如果Bean实现了BeanFactoryAware接口，调用setBeanClassLoader()方法，传入ClassLoader对象的实例。
与上面的类似，如果实现了其他*Aware接口，就调用相应的方法。
如果有和加载这个Bean的Spring容器相关的BeanPostProcessor对象，执行postProcessBeforeInitialization()方法
如果Bean实现了InitializingBean接口，执行afterPropertiesSet()方法。
如果Bean在配置文件中的定义包含init-method属性，执行指定的方法。
如果有和加载这个Bean的Spring容器相关的BeanPostProcessor对象，执行postProcessAfterInitialization()方法
当要销毁Bean的时候，如果Bean实现了DisposableBean接口，执行destroy()方法。
当要销毁Bean的时候，如果Bean在配置文件中的定义包含destroy-method属性，执行指定的方法。

# link
[Spring Bean的生命周期](http://www.cnblogs.com/zrtqsk/p/3735273.html)

[Spring Bean的生命周期](http://yemengying.com/2016/07/14/spring-bean-life-cycle/)
