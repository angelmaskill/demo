package com.algorithm.linkedlist;

/**
 * 单向循环链表解决约瑟夫问题
 *
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/4/3 10:21 下午
 * @Modified By:
 */
public class Josepfu {
    public static void main(String[] args) {
        SingleCircleLink list = new SingleCircleLink();
        list.init();

        /*Boy boy1 = new Boy(1);
        Boy boy2 = new Boy(2);
        Boy boy3 = new Boy(3);
        Boy boy4 = new Boy(4);
        list.add(boy1);
        list.add(boy2);
        list.add(boy3);
        list.add(boy4);*/

        list.addBatch(125);
        list.list("显示添加元素之后的单向环形链表");
        list.outBoy(2);
    }
}

class SingleCircleLink {
    int beginIndex = 1;
    Boy first = new Boy(beginIndex);
    Boy curBoy;

    /*给单向循环链表添加元素*/
    public void add(Boy boy) {
        // 如果当前指针为空，说明尚未初始化单向循环链表
        if (curBoy == null) {
            init();
        }
        curBoy.next = boy;
        boy.next = first;
        curBoy = boy;
    }

    /*给单向循环链表批量添加元素*/
    public void addBatch(int nums) {
        // 如果当前指针为空，说明尚未初始化单向循环链表
        if (curBoy == null) {
            init();
        }
        for (int i = beginIndex + 1; i <= beginIndex + nums - 1; i++) {
            Boy boy = new Boy(i);
            curBoy.next = boy;
            boy.next = first;
            curBoy = boy;
        }
    }

    public void init() {
        curBoy = first;
        first.setNext(first);
    }

    /**
     * 遍历单向循环链表
     */
    public void list(String desc) {
        System.out.println(desc);
        if (first == null) {
            System.out.println("没有任何小孩！");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy);
            curBoy = curBoy.next;
            if (curBoy.equals(first)) {
                break;
            }
        }
    }

    /**
     * 出圈代码实现 指定出圈第几号
     */
    public void outBoy(int num) {
        Boy curentBoy = first;
        Boy helper = findFirstPre(curentBoy);
        // 小孩开始报数，让 first和helper 同时移动m-1次
        for (int i = 1; i <= num - 1; i++) {
            helper = helper.next;
            first = first.next;
        }
        System.out.printf("即将出圈的小孩：%s\n", first.getNo());
        first = first.next;
        helper.next = first;
        // 当圈中只有一个小孩的时候，直接把小孩出圈
        if (helper.equals(first)) {
            System.out.printf("最后留在圈中的小孩：%s\n", first.getNo());
            return;
        }
        // 如果 first不为空， 继续迭代
        if (first != null) {
            outBoy(num);
        }
    }

    private Boy findFirstPre(Boy curentBoy) {
        Boy helper = null;
        while (true) {
            if (curentBoy.next.equals(first)) {
                helper = curentBoy;
                break;
            }
            curentBoy = curentBoy.next;
        }
        //System.out.printf("first 前一个节点为：%s\n", helper);
        return helper;
    }
}

class Boy {
    private int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{ boy no=" + no + '}';
    }
}