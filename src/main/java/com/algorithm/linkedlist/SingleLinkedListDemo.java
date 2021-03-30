package com.algorithm.linkedlist;

/**
 * @Author: AngelMa
 * @Description: 模拟单向链表
 * @Date: Created on 2021/3/30 11:22 下午
 * @Modified By:
 */

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        /*声明元素*/
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        /*创建链表*/
        SingleLinkedList list = new SingleLinkedList();
        list.add(heroNode1);
        list.add(heroNode2);
        list.add(heroNode3);
        list.add(heroNode4);

        /*显示所有元素*/
        list.showAll();

    }
}

class SingleLinkedList {
    HeroNode headNode = new HeroNode(0, "", "");

    public void add(HeroNode newHero) {
        /*从头结点找起*/
        HeroNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = newHero;
    }

    public void showAll() {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = headNode.next;
        while (true) {
            /*判断是否到了最后一个节点*/
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            /*将 temp 后移*/
            temp = temp.next;
        }
    }
}

class HeroNode {
    int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName
               + '\'' + '}';
    }
}