1. 第一步: 必须得打成JAR包
2. 第二步: jar包中的demo.jar\META-INF 目录,必须包含:Premain-Class: com.jvm.util.MemoryCalculator,这个类为包含premain方法的类.键值对中间有空格.
3. 第三步: 执行命令.java -javaagent:demo.jar com.jvm.util.TestSizeOf