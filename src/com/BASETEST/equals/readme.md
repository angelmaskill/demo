何时需要重写equals()
	当一个类有自己特有的“逻辑相等”概念（不同于对象身份的概念）。
	根据一个类的equals方法（改写后），两个截然不同的实例有可能在逻辑上是相等的，但是，根据Object.hashCode方法，它们仅仅是两个对象。因此，违反了“相等的对象必须具有相等的散列码”。

什么时候 hashCode() 与 equals() 应该同时覆写！

* 当某个类的对象作为HashMap的key时，必须同时覆写equals()和hashCode方法
* 更加一般意义的是：只要某各类结合基于散列的集合一起使用，那么就必须同时覆写equals()和HashCode方法,否则就会被Object类 的hashCode方法所干扰！
* 要放到有序容器中，还要重写compareTo()方法。


设计equals()
[1]使用instanceof操作符检查“实参是否为正确的类型”。 
[2]对于类中的每一个“关键域”，检查实参中的域与当前对象中对应的域值。 
[2.1]对于非float和double类型的原语类型域，使用==比较； 
[2.2]对于对象引用域，递归调用equals方法； 
[2.3]对于float域，使用Float.floatToIntBits(afloat)转换为int，再使用==比较； 
[2.4]对于double域，使用Double.doubleToLongBits(adouble) 转换为int，再使用==比较； 
[2.5]对于数组域，调用Arrays.equals方法。



hashCode()和equals()定义在Object类中，这个类是所有java类的基类，所以所有的java类都继承这两个方法。


使用hashCode()和equals()

* hashCode()方法被用来获取给定对象的唯一整数。这个整数被用来确定对象被存储在HashTable类似的结构中的位置。默认的，Object类的hashCode()方法返回这个对象存储的内存地址的编号。
* 如果你不重写这两个方法，将几乎不遇到任何问题，但是有的时候程序要求我们必须改变一些对象的默认实现。



 
 
在java的集合中，判断两个对象是否相等的规则是： 

首先，判断两个对象的hashCode是否相等 
如果不相等，认为两个对象也不相等 
如果相等，则判断两个对象用equals运算是否相等 
如果不相等，认为两个对象也不相等 
如果相等，认为两个对象相等 



https://www.oschina.net/question/82993_75533
http://www.itdadao.com/articles/c15a857853p0.html
http://www.programgo.com/article/21231243597/
