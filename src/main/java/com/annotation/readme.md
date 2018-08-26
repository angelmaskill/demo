# 注解的作用
在J2EE中，注解使得开发更加便利，省去了生成XML文件的过程，在Hibernate实体声明中，可以简简单单的用几个注解就可以免去生成一个XML的文件操作。这里就主要论述一下annotation的用法和自定义注解处理器。

Java目前包括三种标准注解和四种元注解。元注解主要负责注解其他注解的。


# 场景
当在创建描述符性质的类或接口时，有大量重复性的工作时候，就可以利用注解来实现。

# 三种标准注解
@Override，表示当前的方法定义覆盖了父类中的方法。必须要有相同的方法签名即(方法名，参数类型，参数顺序，参数个数)都一样。否则在编译过程中发出错误提示。
@Deprecated,对不应该再使用的方法添加注解，当使用这个方法的时候，会在编译时候显示提示信息。
@SuppressWarnings,关闭不当的编译器报警信息


# 四种元注解
四个元注解分别是：@Target,@Retention,@Documented,@Inherited ，再次强调下元注解是java API提供，是专门用来定义注解的注解，其作用分别如下：

## @Target 
表示该注解用于什么地方，可能的值在枚举类 ElemenetType 中，包括： 

* ElemenetType.CONSTRUCTOR----------------------------构造器声明 
* ElemenetType.FIELD --------------------------------------域声明（包括 enum 实例） 
* ElemenetType.LOCAL_VARIABLE------------------------- 局部变量声明 
* ElemenetType.METHOD ----------------------------------方法声明 
* ElemenetType.PACKAGE --------------------------------- 包声明 
* ElemenetType.PARAMETER ------------------------------参数声明 
* ElemenetType.TYPE--------------------------------------- 类，接口（包括注解类型）或enum声明 
           
## @Retention 
表示在什么级别保存该注解信息。可选的参数值在枚举类型 RetentionPolicy 中，包括： 

* RetentionPolicy.SOURCE ---------------------------------注解将被编译器丢弃 
* RetentionPolicy.CLASS -----------------------------------注解在class文件中可用，但会被VM丢弃 
* RetentionPolicy.RUNTIME VM-------将在运行期也保留注释，因此可以通过反射机制读取注解的信息。 
           
## @Documented 
将此注解包含在 javadoc 中，它代表着此注解会被javadoc工具提取成文档。在doc文档中的内容会因为此注解的信息内容不同而不同。相当与@see,@param 等。
       
## @Inherited 
允许子类继承父类中的注解。

# 注解的用途
如果没有用来读取注解的方法和工作，那么注解也就不会比注释更有用处了。使用注解的过程中，很重要的一部分就是创建于使用注解处理器。Java SE5扩展了反射机制的API，以帮助程序员快速的构造自定义注解处理器。

Java使用Annotation接口来代表程序元素前面的注解，该接口是所有Annotation类型的父接口。除此之外，Java在java.lang.reflect 包下新增了AnnotatedElement接口，该接口代表程序中可以接受注解的程序元素，该接口主要有如下几个实现类：

* Class：类定义
* Constructor：构造器定义
* Field：累的成员变量定义
* Method：类的方法定义
* Package：类的包定义


java.lang.reflect 包下主要包含一些实现反射功能的工具类，实际上，java.lang.reflect 包所有提供的反射API扩充了读取运行时Annotation信息的能力。当一个Annotation类型被定义为运行时的Annotation后，该注解才能是运行时可见，当class文件被装载时被保存在class文件中的Annotation才会被虚拟机读取。
　　AnnotatedElement 接口是所有程序元素（Class、Method和Constructor）的父接口，所以程序通过反射获取了某个类的AnnotatedElement对象之后，程序就可以调用该对象的如下四个个方法来访问Annotation信息：

* 方法1：<T extends Annotation> T getAnnotation(Class<T> annotationClass): 返回改程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null。
* 方法2：Annotation[] getAnnotations():返回该程序元素上存在的所有注解。
* 方法3：boolean is AnnotationPresent(Class<?extends Annotation> annotationClass):判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false.
* 方法4：Annotation[] getDeclaredAnnotations()：返回直接存在于此元素上的所有注释。与此接口中的其他方法不同，该方法将忽略继承的注释。（如果没有注释直接存在于此元素上，则返回长度为零的一个数组。）该方法的调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响。



