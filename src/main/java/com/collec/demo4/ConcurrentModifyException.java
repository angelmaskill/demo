package com.collec.demo4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentModifyException {
    static Lock lock = new ReentrantLock();// 锁

    public static void main(String[] args) throws InterruptedException {

        ConcurrentModifyException con = new ConcurrentModifyException();
        //List<String> strList  =  con.init();
        //List<String> strList  =  con.init2();
        List<String> strList = con.initCon();
        //List<String> strList = new ArrayList<>();
        //List<String> strList = new CopyOnWriteArrayList<>();

        new Thread(new Runnable() {
            public void run() {
                /**
                 * 测试一遍添加,一边删除,不行.
                 */

//				for (int i = 0; i < 100; i++) {
//					System.out.println("添加数据"+i);
//					strList.add("string" + i);
//					}
//				}
                /**
                 * 测试一遍读,一边删除,可以,读写分离
                 */
                for (String string : strList) {
                    System.out.println("遍历集合 value = " + string);

//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
                }
            }
        }).start();


        new Thread(new Runnable() {
            public void run() {
                //con.test6(strList);
				/*try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
            }
        }).start();


        Thread.currentThread().join();
        System.out.println("最终条数:" + strList.size());

    }

    private List<String> init() {
        List<String> strList = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            strList.add("string" + i);
        }
        return strList;
    }

    /**
     * 线程安全的list
     *
     * @return
     */
    private List<String> init2() {
        List<String> strList = Collections.synchronizedList(new ArrayList<String>());
        for (int i = 0; i < 10000; i++) {
            strList.add("string" + i);
        }
        return strList;
    }


    private List<String> initCon() {
        List<String> strList = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 100; i++) {
            strList.add("string" + i);
        }
        return strList;
    }

    // 操作方式1：while（Iterator）；报错
    public void test1(List<String> strList) {
        Iterator<String> it = strList.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if ("string2".equals(s)) {
                strList.remove(s);
            }
        }
    }

    public void test2(List<String> strList) {
        // 解决方案1：使用Iterator的remove方法删除元素
        // 操作方式1：while（Iterator）：不报错
        Iterator<String> it = strList.iterator();
        while (it.hasNext()) {
            String s = it.next();
            // if ("string2".equals(s)) {
            it.remove();
            // }
        }
    }

    public void test22(List<String> strList) {
        // 解决方案1：使用Iterator的remove方法删除元素
        // 操作方式1：while（Iterator）：不报错
        synchronized (strList) {
            Iterator<String> it = strList.iterator();
            while (it.hasNext()) {
                String s = it.next();
                it.remove();
            }
        }
    }


    private void test3(List<String> strList) {
        // 操作方式2：foreach（Iterator）；报错
        int i = 0;
        for (String s : strList) {
            i++;
            System.out.println("删除第" + i + "个");
            // if ("string2".equals(s)) {
            strList.remove(s);
            // }
        }
    }


    private void test33(List<String> strList) {
        // 操作方式2：foreach（Iterator）；报错
        int i = 0;
        for (String s : strList) {
            i++;
            System.out.println("删除第" + i + "个");
            // if ("string2".equals(s)) {
            strList.remove(s);
            // }
        }
    }

    private void test4(List<String> strList) {
        // 解决方案2：不使用Iterator遍历，注意索引的一致性
        // 操作方式3：for（非Iterator）；不报错；注意修改索引
        for (int i = 0; i < strList.size(); i++) {
            String s = strList.get(i);
            // if ("string2".equals(s)) {
            strList.remove(s);
            strList.remove(i);
            i--;// 元素位置发生变化，修改i
            // }
        }
    }

    private void test5(List<String> strList) {
        // 解决方案3：新建一个临时列表，暂存要删除的元素，最后一起删除
        List<String> templist = new ArrayList<String>();
        for (String s : strList) {
            // if (s.equals("string2")) {
            templist.add(s);
            // }
        }
        // 查看removeAll源码，其使用Iterator进行遍历
        strList.removeAll(templist);
    }

    private void test6(List<String> strList) {
        // 解决方案4：使用线程安全CopyOnWriteArrayList进行删除操作
        Iterator<String> it = strList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            System.out.println("删除第" + i + "个");
            String s = it.next();
            strList.remove(s);
        }
    }
}
