当log4j遇上序列化的时候，会无意中报错，
例如：这位外国仁兄的询问
How to fix java.io.NotSerializableException: org.apache.log4j.Logger Error in Java
嗯，很对！也就是说java.io.NotSerializableException: org.apache.log4j.Logger error 
org.apache.lo4j.Logger 不能序列化，问题出现的原因是，序列化的类用上了log4j没有申明静态的实例logger
出错代码：
public class Customer implements Serializable{
private Logger logger =   Logger.getLogger(Customer.class)
}
修正代码：
public class Customer implements Serializable{
private static final Logger logger =   Logger.getLogger(Customer.class)
 
}
 
原因【答案本身来自外文，这里就摘了吧】：
 because here logger instance is neither static or transient and it doesn't implement Serializable or Externalzable interface.
Solving java.io.NotSerializableException: org.apache.log4j.Logger   is simple, you have to prevent logger instance from default serializabtion process, either make it transient or static . Making it static final is preferred option due to many reason because if you make it transient than after deserialization logger instance will be null and any logger.debug() call will result in NullPointerException in Javabecause neither constructor not instance initializer block is called during deserialization. By making it static and final you ensure that its thread-safe and all instance of Customerclass can share same logger instance, By the way this error is also one of the reasonWhy Logger should be declared static and final in Java program .
 log4j路径的控制： $CATALINA_HOME/logs/
 
 
[log4j 与序列化出现的问题](http://fxrz12.iteye.com/blog/1870356)