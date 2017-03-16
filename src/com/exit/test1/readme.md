运行上述程序后在命令行：
ps aux | grep java
找到这个程序的PID。
然后执行命令：
kill -s SIGUSR2 $PID
命令中$PID是指PID的数值，如：
kill -s SIGUSR2 1360
表示向1360进程发一个SIGUSR2的信号。
java收到这个信号后，将isContinue置为false。程序就退出了。