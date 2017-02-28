[热替换](https://my.oschina.net/fattian/blog/125547)

# 说明
通过自定义classloader和java的反射技术，可以自行控制类的加载。多数的Web容器，例如，Tomcat中就实现了自己的类加载器，在修改编译代码之后，可以在不停服务的情况下，运行新class文件中的代码；还有，OSGi中也实现了更加复杂的类加载器，被认为是控制类加载的经典代码。


# 思想
```
while(true){
  clz = loadClass("problem1.GetInfo");  // 需要使用自定义classloader
  clz.Output();                         // 需要使用反射技术
}
```