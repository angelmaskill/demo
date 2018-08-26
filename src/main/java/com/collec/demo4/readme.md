## Java ConcurrentModificationException 异常分析与解决方案

出现异常的情况有一个共同的特点，都是使用Iterator进行遍历，且都是通过ArrayList.remove(Object) 进行删除操作。

## 考虑方案:
(1) 在所有遍历增删地方都加上synchronized或者使用Collections.synchronizedList，虽然能解决问题但是并不推荐，因为增删造成的同步锁可能会阻塞遍历操作。
(2) 推荐使用ConcurrentHashMap或者CopyOnWriteArrayList。

## CopyOnWriteArrayList注意事项

(1) CopyOnWriteArrayList不能使用Iterator.remove()进行删除。
(2) CopyOnWriteArrayList使用Iterator且使用List.remove(Object);会出现如下异常：
java.lang.UnsupportedOperationException: Unsupported operation remove
at java.util.concurrent.CopyOnWriteArrayList$ListIteratorImpl.remove(CopyOnWriteArrayList.java:804)

<div id="test1" style="display:none">
	http://blog.csdn.net/kingzone_2008/article/details/41368989
	http://arron-li.iteye.com/blog/645008
	http://www.2cto.com/kf/201403/286536.html
</div>

