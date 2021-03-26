package com.springpri.dynamicLoadBean.demo3;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 首先需要解释下，何谓Bean动态注册？我们知道，我们A类依赖B类，且A、B类都需要在Spring的applicationContext.xml中进行注册，A、B的依赖关系通过property的ref属性映射，
 * 还有一种实现方式就是使用注解，Spring IOC内置了很多有关bean组件的注解，比如@Component、@Resource、@Service，
 * 但是这些都是需要我们手动去一个一个的去配置，不管是配置XML还是配置注解，我都觉得很麻烦，所以最近几天我都一直在思考，能不能实现我们的javabean不用显式的在applicationContext.xml里注册并且不用添加spring bean相关的注解，
 * 在程序运行时动态的把某些bean临时的交给spring IOC 容器来管理，
 * 这就是困扰我几天的问题，幸好还是解决了，决定有必要记录一下。我们知道spring的contextLoadListener是在工程启动时去加载spring配置文件的，在运行时修改applicationContext
 * .xml必须重新加载该配置文件才能让spring重新解析xml配置，然后刷新ApplicationContext即可实现bean的动态注册。在一遍一遍翻阅了Spring的相关源码后，开始有点思路了，今晚回来就尝试写了个示例，没想到果然成功了，greate
 * !其实实现一点都不难，主要是你要熟悉spring的API，能从spring的源码中洞穿框架设计者的本意，这才是解决问题的根本所在。
 *
 * @author mayanlu
 */
public class Test {
    public static void main(String[] args) throws BeanDefinitionStoreException, ClassNotFoundException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "com/springpri/dynamicLoadBean/demo3/applicationContext.xml");
        // ConfigurableListableBeanFactory beanFactory =
        // context.getBeanFactory();

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        // TestService testService = new TestServiceImpl();
        // 这里是动态注册单例，不过实例要事先自己new,这种做法不推荐
        // beanFactory.registerSingleton("testService", testService);

        // 这里是动态注册非单例bean,把setScope("prototype")
        // 改为setScope("singleton")就是单例注册了，推荐使用这种方式动态注册
        // 有的同学可能会问，采用动态注册Bean有上面好处，好处就是：
        // 你的bean再也不需要在Spring的applicationContext.xml中注册了，也不需要在你的bean上加什么@Component、@Resource、@Service等各种bean相关的注解了
        // 从bean注册中彻底解放了，你甚至可以自己写一个监听器（比如实现一个ServletContextListener），在项目启动时，搜索项目中需要注册的bean通过现在这种方法动态注册，后来新增的bean可以额外再动态注册，是不是感觉世界又美好了很多
        beanFactory.registerBeanDefinition(
                "testDao",
                BeanDefinitionBuilder
                        .genericBeanDefinition(Class.forName("com.springpri.dynamicLoadBean.demo3.TestDaoImpl"))
                        .setScope("singleton").getRawBeanDefinition());
        beanFactory.registerBeanDefinition(
                "testService",
                BeanDefinitionBuilder
                        .genericBeanDefinition(Class.forName("com.springpri.dynamicLoadBean.demo3.TestServiceImpl"))
                        .setScope("singleton").addPropertyReference("testDao", "testDao").getRawBeanDefinition());
        TestService testService2 = (TestService) beanFactory.getBean("testService");
        TestService testService3 = (TestService) beanFactory.getBean("testService");

        // 判断是否为单例，false就表明不是单例
        // com.nicolas.service.impl.TestServiceImpl这里的包路径你其实可以通过项目某个包下的所有以某某字符串结尾的类就可以获取得到
        // 比如你搜索项目中com.yida.service.impl包下的所有以Impl结尾的类，你得到了类，那通过类就可以知道类的名称，再拼接上包路径，那么
        // 那上面写死的包路径你就可以动态获取了，这里仅仅只是为了测试
        System.out.println(testService2.equals(testService3));

        testService2.SayHello();
    }
}