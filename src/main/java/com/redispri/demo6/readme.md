http://blog.csdn.net/lihao21/article/details/49104695

使用Redis的 SETNX 命令可以实现分布式锁，下文介绍其实现方法。

SETNX命令简介

命令格式

SETNX key value
将 key 的值设为 value，当且仅当 key 不存在。 
若给定的 key 已经存在，则 SETNX 不做任何动作。 
SETNX 是SET if Not eXists的简写。

返回值

返回整数，具体为 
- 1，当 key 的值被设置 
- 0，当 key 的值没被设置

例子

redis> SETNX mykey “hello” 
(integer) 1 
redis> SETNX mykey “hello” 
(integer) 0 
redis> GET mykey 
“hello” 
redis>