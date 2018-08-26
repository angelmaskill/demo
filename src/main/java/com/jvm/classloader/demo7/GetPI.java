package com.jvm.classloader.demo7;

import java.io.PrintStream;

public class GetPI
{
  public static double Darts(int n)
  {
    int k = 0;
    double x = 0.0D;
    double y = 0.0D;
    for (int i = 0; i < n; i++)
    {
      x = Math.random();
      y = Math.random();

      if (x * x + y * y <= 1.0D)
        k++;
    }
    return 4 * k / n;
  }
  // 本热部署实验中，上面的Darts函数没有用到，请忽略
  public static void Output() {
    System.out.println("bbbOutput");
  }
}