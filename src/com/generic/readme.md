# 目录
[toc]

# 原理
* Java中的泛型基本上都是在编译器这个层次来实现的。
* 在生成的Java字节代码中是不包含泛型中的类型信息的。
* 了解了类型擦除机制之后，就会明白编译器承担了全部的类型检查工作。
* 编译器禁止某些泛型的使用方式，正是为了确保类型的安全性。

## 类型擦除
使用泛型的时候加上的类型参数，会被编译器在编译的时候去掉。这个过程就称为类型擦除。
如在代码中定义的`List<Object>和List<String>`等类型，在编译之后都会变成List。JVM看到的只是List，而由泛型附加的类型信息对JVM来说是不可见的。Java编译器会在编译时尽可能的发现可能出错的地方，但是仍然无法避免在运行时刻出现类型转换异常的情况。

* 泛型的类型参数不能用在Java异常处理的catch语句中。因为异常处理是由JVM在运行时刻来进行的。由于类型信息被擦除，JVM是无法区分两个异常类型MyException<String>和MyException<Integer>的。对于JVM来说，它们都是 MyException类型的。也就无法执行与异常对应的catch语句。 



 
# 泛型的引入
## 优点
泛型的引入可以解决之前的集合类框架在使用过程中通常会出现的运行时刻类型错误，因为编译器可以在编译时刻就发现很多明显的错误。
泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。

## 缺点
而从不好的地方来说，为了保证与旧有版本的兼容性，Java泛型的实现上存在着一些不够优雅的地方。
当然这也是任何有历史的编程语言所需要承担的历史包袱。后续的版本更新会为早期的设计缺陷所累。  

# 使用场景
假定我们有这样一个需求：写一个排序方法，能够对整形数组、字符串数组甚至其他任何类型的数组进行排序，该如何实现？
答案是可以使用 Java 泛型。
使用 Java 泛型的概念，我们可以写一个泛型方法来对一个对象数组排序。然后，调用该泛型方法来对整型数组、浮点数数组、字符串数组等进行排序。


# 误用
开发人员在使用泛型的时候，很容易根据自己的直觉而犯一些错误。比如一个方法如果接收`List<Object>`作为形式参数，那么如果尝试将一个`List<String>`的对象作为实际参数传进去，却发现无法通过编译。
虽然从直觉上来说，Object是String的父类，这种类型转换应该是合理的。但是实际上这会产生隐含的类型转换问题，因此编译器直接就禁止这样的行为。本文试图对Java泛型做一个概括性的说明。

所声明的类型参数在Java类中可以像一般的类型一样作为`方法的参数和返回值`，或是`作为域和局部变量的类型`。
但是由于类型擦除机制，类型参数并`不能用来创建对象或是作为静态变量的类型`。

	class ClassTest<X extends Number, Y, Z> {    
	    private X x;    
	    private static Y y; //编译错误，不能用在静态变量中    
	    public X getFirst() {
	        //正确用法        
	        return x;    
	    }    
	    public void wrong() {        
	        Z z = new Z(); //编译错误，不能创建对象    
	    }
	}  

# 分类
## 泛型方法
## 泛型类
## 通配符和上下界
在使用泛型类的时候，既可以指定一个具体的类型，如List<String>就声明了具体的类型是String；
也可以用通配符?来表示未知类型，如`List<?>`就声明了List中包含的元素类型是未知的。 

** 通配符所代表的其实是一组类型，但具体的类型是未知的。** 

`List<?>`所声明的就是所有类型都是可以的。但是`List<?>`并不等同于`List<Object>`。

`List<Object>`实际上确定了List中包含的是Object及其子类，在使用的时候都可以通过Object来进行引用。
而`List<?>`则其中所包含的元素类型是不确定。其中可能包含的是String，也可能是 Integer。如果它包含了String的话，往里面添加Integer类型的元素就是错误的。

正因为类型未知，就不能通过`new ArrayList<?>()`的方法来创建一个新的ArrayList对象。因为编译器无法知道具体的类型是什么。
但是对于 List<?>中的元素确总是可以用Object来引用的，因为虽然类型未知，但肯定是Object及其子类。考虑下面的代码：


	public void wildcard(List<?> list) {
	    list.add(1);//编译错误 
	}  

如上所示，试图对一个带通配符的泛型类进行操作的时候，总是会出现编译错误。其原因在于通配符所表示的类型是未知的。


** 因为对于List<?>中的元素只能用Object来引用，在有些情况下不是很方便。在这些情况下，可以使用上下界来限制未知类型的范围。**  

如`List<? extends Number>`说明List中可能包含的元素类型是Number及其子类。
而`List<? super Number>`则说明List中包含的是Number及其父类。

当引入了上界之后，在使用类型的时候就可以使用上界类中定义的方法。比如访问` List<? extends Number>`的时候，就可以使用Number类的intValue等方法。

# 继承体系结构
## 泛型类/接口自身的继承体系结构
* List<String>是Collection<String> 的子类型,List<String>可以替换Collection<String>。

## 类型参数自身的继承体系结构
List<String>是List<?>

## 综合案例分析
Collection<? extends Number>的子类:

**从泛型类自身的结构分析,子类有:**

1. List<? extends Number>
2. Set<? extends Number>

**从类型参数自身的结构分析,子类有:**

1. Collection<Double>
2. Collection<Integer>


# 参考地址

* [Java深度历险（五）——Java泛型](http://www.infoq.com/cn/articles/cf-java-generics)
* [第12 章泛型](https://github.com/JustinSDK/JavaSE6Tutorial/blob/master/docs/CH12.md)
