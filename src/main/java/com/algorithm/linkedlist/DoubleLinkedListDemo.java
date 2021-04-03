package com.algorithm.linkedlist;

/**
 * @Author: AngelMa
 * @Description:
 * @Date: Created on 2021/4/3 10:41 上午
 * @Modified By:
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        Hero2Node hero1 = new Hero2Node(1, "宋江", "及时雨");
        Hero2Node hero2 = new Hero2Node(2, "卢俊义", "玉麒麟");
        Hero2Node hero3 = new Hero2Node(3, "吴用", "智多星");
        Hero2Node hero4 = new Hero2Node(4, "林冲", "豹子头");

        //创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);

        doubleLinkedList.showList("添加元素后的双向链表： ");
        /*doubleLinkedList.delete(2);
        doubleLinkedList.delete(10);
        doubleLinkedList.showList("删除元素后的双向链表： ");
        doubleLinkedList.delete(1);
        doubleLinkedList.delete(3);
        doubleLinkedList.delete(4);
        doubleLinkedList.showList("删空的双向链表： ");*/

    }
}

class DoubleLinkedList {
    //先初始化一个头结点，头节点不要动，不要存放具体的数值
    Hero2Node head = new Hero2Node(0, "", "");

    public Hero2Node getHead() {
        return head;
    }

    /*在链表尾部添加新的节点*/
    public void add(Hero2Node hero2Node) {
        Hero2Node head = getHead();
        /*如果只有一个头，则直接添加到头的后边即可*/
        if (head.next == null) {
            head.next = hero2Node;
            hero2Node.pre = head;
            hero2Node.next = null;
            return;
        }
        Hero2Node temp = head.next;
        while (temp != null) {
            if (temp.next == null) {
                temp.next = hero2Node;
                hero2Node.pre = temp;
                hero2Node.next = null;
                break;
            }
            temp = temp.next;
        }
    }

    /*在链表尾部添加新的节点*/
    public void addByOrder(Hero2Node hero2Node) {
        Hero2Node head = getHead();
        /*如果只有一个头，则直接添加到头的后边即可*/
        if (head.next == null) {
            head.next = hero2Node;
            hero2Node.pre = head;
            hero2Node.next = null;
            return;
        }
        Hero2Node temp = head.next;
        if (temp == null) {
            head.next = hero2Node;
            hero2Node.pre = head;
            return;
        }
        while (temp != null) {
            if (temp.heroNo == hero2Node.heroNo) {
                System.out.printf("当前节点已经存在 ！当前节点：%s ,添加节点 %s", temp.heroName, hero2Node.heroName);
                return;
            }
            // 如果插入的节点比当前节点大
            if (temp.heroNo < hero2Node.heroNo) {
                if (temp.next == null) {
                    temp.next = hero2Node;
                    hero2Node.pre = temp;
                    break;
                }
                temp = temp.next;
            }
            // 插入节点比当前节点小
            if (temp.heroNo > hero2Node.heroNo) {
                hero2Node.next = temp;
                hero2Node.pre = temp.pre;
                temp.pre.next = hero2Node;
                temp.pre = hero2Node;
                break;
            }
        }
    }

    public void delete(int headNo) {
        Hero2Node head = getHead();
        Hero2Node temp = head.next;
        if (isEmpty()) {
            System.out.println("数据节点为空，无法删除！");
            return;
        }
        while (temp != null) {
            if (temp.heroNo == headNo) {
                //说明当前节点是尾结点
                if (temp.next == null) {
                    temp.pre.next = null;
                    temp.pre = null;
                    break;
                }
                temp.next.pre = temp.pre;
                temp.pre.next = temp.next;
            }
            temp = temp.next;
        }
    }

    public void showList(String note) {
        System.out.println(note);
        Hero2Node head = getHead();
        if (isEmpty()) {
            return;
        }
        Hero2Node temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        Hero2Node head = getHead();

        if (head == null || head.next == null) {
            System.out.println("链表为空");
            return true;
        }
        return false;
    }
}

//定义HeroNode，每一个HeroNode对象就是一个节点
class Hero2Node {
    public int heroNo;
    public String heroName;
    public String nickName;
    Hero2Node next;
    Hero2Node pre;

    public Hero2Node(int heroNo, String heroName, String nickName) {
        this.heroNo = heroNo;
        this.heroName = heroName;
        this.nickName = nickName;
    }

    //为了显示方法，我们重写toString
    public String toString() {
        return "HeroNode [no = " + heroNo + ", name = " + heroName + ", nickname = " + nickName
               + "]";
    }
}
