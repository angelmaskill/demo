package com.BASETEST.ENUM.demo1;

/**
 * <pre>
 * 枚举类型的简单定义方法如下，我们似乎没办法定义每个枚举类型的值。比如我们定义红灯、绿灯和黄灯的代码可能如下：
 * public enum Light {
 *         RED, GREEN, YELLOW;
 *    }
 * 我们只能够表示出红灯、绿灯和黄灯，但是具体的值我们没办法表示出来。
 * 别急，既然枚举类型提供了构造函数，我们可以通过构造函数和覆写toString方法来实现。
 * (1)首先给Light枚举类型增加构造方法，
 * (2)然后每个枚举类型的值通过构造函数传入对应的参数，
 * (3)同时覆写toString方法，在该方法中返回从构造函数中传入的参数，
 * 
 * 改造后的代码见：LightEnumTest
 * 
 * </pre>
 * 
 * @author mayanlu
 *
 */

public enum LightEnum {
	RED, GREEN, YELLOW;
}