# 引子
* Launcher$ExtClassLoader的实现是遵循的双亲委派模型，它重写的是findClass方法,
* Launcher$AppClassLoader的实现是没有遵循双亲委派模型的，它重写的是loadClass方法,


# 重写findClass,先父后子,遵双亲规则
* 重写findClass方法的自定义类，首先会通过父类加载器进行加载，如果所有父类加载器都无法加载，再通过用户自定义的findClass方法进行加载。如果父类加载器可以加载这个类或者当前类已经存在于某个父类的容器中了，这个类是不会再次被加载的，此时用户自定义的findClass方法就不会被执行了。
* 重写findClass方法是符合双亲委派模式的，它保证了相同全限定名的类是不会被重复加载到JVM中


# 重写loadClass,先子后父,违双亲规则
* 如果要想在JVM的不同类加载器中保留具有相同全限定名的类，那就要通过重写loadClass来实现
* 此时首先是通过用户自定义的类加载器来判断该类是否可加载，如果可以加载就由自定义的类加载器进行加载，如果不能够加载才交给父类加载器去加载。
* 这种情况下，就有可能有大量相同的类，被不同的自定义类加载器加载到JVM中，并且这种实现方式是不符合双亲委派模型。

# 不同类加载器的命名空间关系
* 同一个命名空间内的类是相互可见的。
* 子加载器的命名空间包含所有父加载器的命名空间。因此子加载器加载的类能看见父加载器加载的类。例如系统类加载器加载的类能看见根类加载器加载的类。
* 由父加载器加载的类不能看见子加载器加载的类。
* 如果两个加载器之间没有直接或间接的父子关系，那么它们各自加载的类相互不可见。
* 当两个不同命名空间内的类相互不可见时，可以采用Java的反射机制来访问实例的属性和方法。


# 参考链接
* [深入探讨 Java 类加载器](https://www.ibm.com/developerworks/cn/java/j-lo-classloader/)
* [Java虚拟机笔记 – JVM 自定义的类加载器的实现和使用](http://www.itzhai.com/java-virtual-machine-notes-custom-class-loader-implementation-and-use-of.html#read-more)
* [实现自己的类加载时，重写方法loadClass与findClass的区别](http://blog.csdn.net/fenglibing/article/details/17471659)
