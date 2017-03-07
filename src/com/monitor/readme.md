# 方案一、使用 Tomcat提供的manager应用进行数据采集 
◆ 采集Web应用列表信息

通过list命令查看Web应用列表和会话数信息
     http://localhost:8080/manager/list

Tomcat 7.x的查询URL有变化：
     http://localhost:8080/manager/text/list
     
     
◆ 采集服务器基本信息

通过serverinfo命令查看服务器基本信息
     http://localhost:8080/manager/serverinfo

Tomcat 7.x的查询URL有变化：
     http://localhost:8080/manager/text/serverinfo    
     
     
# 方案二、使用JMX 接口开发监控程序 

Tomcat激活JMX远程配置 
 
① 先修改Tomcat的启动脚本，window下tomcat的bin/catalina.bat（linux为catalina.sh），添加以下内 容，8999是jmxremote使用的端口号，第二个false表示不需要鉴权：
Java代码  收藏代码
set JMX_REMOTE_CONFIG=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false  
set CATALINA_OPTS=%CATALINA_OPTS% %JMX_REMOTE_CONFIG%  
 
可以加在if "%OS%" == "Windows_NT" setlocal 一句后的大段的注释后面。

参考官方说明：
http://tomcat.apache.org/tomcat-6.0-doc/monitoring.html#Enabling_JMX_Remote 

② 上面的配置是不需要鉴权的，如果需要鉴权则添加的内容为：
Java代码  收藏代码
set JMX_REMOTE_CONFIG=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=true -Dcom.sun.management.jmxremote.password.file=../conf/jmxremote.password -Dcom.sun.management.jmxremote.access.file=../conf/jmxremote.access  
set CATALINA_OPTS=%CATALINA_OPTS% %JMX_REMOTE_CONFIG%  
 
然后复制并修改授权文件，$JAVA_HOME/jre/lib/management下有 jmxremote.access和jmxremote.password的模板文件，将两个文件复制到$CATALINA_BASE/conf目录下


● 修改$CATALINA_BASE/conf/jmxremote.access 添加内容：
     monitorRole readonly
     controlRole readwrite

● 修改$CATALINA_BASE/conf/jmxremote.password 添加内容：
     monitorRole tomcat
     controlRole tomcat

注意：如果只做第一步没有问题，进行了第二步Tomcat就启动不了，那么很可能是密码文件的权限问题！ 
需要修改jmxremote.password文件的权限，只有运行Tomcat的用户有访问权限：
Windows的NTFS文件系统下，选中文件，点右键 -->“属性”-->“安全”--> 点“高级”--> 点“更改权限”--> 去掉“从父项继承....”--> 弹出窗口中选“删除”，这样就删除了所有访问权限。再选“添加”--> “高级”--> “立即查找”，选中你的用户，例administrator，点“确定"，“确定"。来到权限项目窗口，勾选“完全控制”，点“确定”，OK了。 
官方的提示
The password file should be read-only and only accessible by the operating system user Tomcat is running as.

③ 重新启动Tomcat，在Windows命令行输入“netstat -a”查看配置的端口号是否已打开，如果打开，说明上面的配置成功了。 

④ 使用jconsole测试JMX。
 
运行$JAVA_HOME/bin目录下的jconsole.exe，打开J2SE监视和管理控制台，然后建立连接，如 果是本地的Tomcat则直接选择然后点击连接，如果是远程的，则进入远程选项卡，填写地址、端口号、用户名、口令即可连接。Mbean属性页中给出了相 应的数据，Catalina中是tomcat的，java.lang是jvm的。对于加粗的黑体属性值，需双击一下才可看内容。
     
# 其他监控方案
* ELK Stack收集日志信息之后展示在kibana界面上
* Zabbix通过jmx监控tomcat
* Jprofiler 监控tomcat([JProfiler 监控Tomcat](http://blog.csdn.net/catoop/article/details/48755581))
* probe，是web形式的，可以监控tomcat每个应用的servlet、数据库连接池情况等([本地DEMO](http://localhost:8088/probe/logs/follow.htm?logType=jdk&root=true&logIndex=0))
* 监视应用的好工具JavaMelody
* shell 脚本(curl项目路径,重启;)    
* 使用JDK自带jvisualvm监控tomcat


# 参考资料:
* [Tomcat性能监控工具Probe Quick Start](http://blog.csdn.net/a19881029/article/details/36662861)
* [用LambdaProbe监控Tomcat](http://cuisuqiang.iteye.com/blog/2072841)
* [Java程序监控tomcat中部署的项目的状态以及控制某些项目的启动停止](http://blog.csdn.net/liuyuqin1991/article/details/49280777)
* [监控Tomcat解决方案（监控应用服务器系列文章）](http://yunzhu.iteye.com/blog/953387)     
* [Apache Tomcat 6.0 Manager App HOW-TO](http://tomcat.apache.org/tomcat-6.0-doc/manager-howto.html#Using_the_JMX_Proxy_Servlet)


# 未完待续
* [zabbix多实例监控tomcat](http://zhaopeiyan.blog.51cto.com/10522430/1754183)
* [zabbix监控tomcat](http://www.zhengdazhi.com/archives/431)
* [zabbix通过jmx监控tomcat](http://www.cnblogs.com/Eivll0m/p/5446311.html)
* [zabbix 3.0 利用jmx监控 tomcat](http://duanyexuanmu.blog.51cto.com/1010786/1758027)
* [如何监控 Tomcat？Zabbix 与 Cloud Insight 对比](http://blog.oneapm.com/apm-tech/441.html)