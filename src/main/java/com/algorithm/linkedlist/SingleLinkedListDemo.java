package com.algorithm.linkedlist;

import java.util.Stack;

/**
 * @Author: AngelMa
 * @Description: 模拟单向链表
 * @Date: Created on 2021/3/30 11:22 下午
 * @Modified By:
 */

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        System.out.println("反转之前！");
        singleLinkedList.List();

        System.out.println("反转打印！");
        singleLinkedList.reversePrint();//未改变链表的结构

        singleLinkedList.reverseList(singleLinkedList.getHead());
        System.out.println("反转之后！");
        singleLinkedList.List();

        /*singleLinkedList.delete(hero4);
        singleLinkedList.delete(hero3);
        singleLinkedList.delete(hero2);
        singleLinkedList.delete(hero1);

        System.out.println("删除后");
        singleLinkedList.List();*/

    }
}

class SingleLinkedList {
    //先初始化一个头结点，头节点不要动，不要存放具体的数值
    HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表找到最后一个节点
        while (true) {
            //找到最后一个节点，终止循环
            if (temp.next == null) {
                break;
            }
            //如果没有找到，将temp向后移动
            temp = temp.next;
        }
        //while退出时，temp已经指向的最后的节点
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找到的temp是位于添加位置的前一个结点，否则插入不进去
        HeroNode temp = head;//辅助指针，初始值在head
        boolean flag = false;//标识：表示该插入的对象是否已经在链表中存在了，默认为false（没有存在）
        //遍历，从head开始到链表尾
        while (true) {
            //如果已经在链表尾
            if (temp.next == null) {
                break;
            }
            //如果找到位置，就在temp后面插入
            if (temp.next.heroNo
                > heroNode.heroNo) {//temp的heroNo不大于，但是temp.next大于，说明插入位置在temp和temp.next之间
                break;//找到位置，退出循环
            }
            if (temp.next.heroNo == heroNode.heroNo) {
                flag = true;//说明该节点存在
                break;
            }
            //如果以上条件都不满足，将temp后移
            temp = temp.next;
        }
        //此时得到了一个flag值或者一个temp位置
        //首先判断flag
        if (flag) {
            //说明该节点已经存在
            System.out.printf("节点%d已经存在，不能再添加\n", heroNode.heroNo);
        } else {
            //该节点不存在的话，就在temp后面插入新的节点
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 使用栈的方式来逆序打印单向链表
     */
    public void reversePrint() {
        Stack<HeroNode> stack = new Stack<>();
        if (head.next == null) {
            return;
        }
        HeroNode current = head.next;
        while (current != null) {
            stack.add(current);
            current = current.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    public void reverseList(HeroNode head) {
        /*一个节点都没有，或者只有一个节点，无需反转*/
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode current = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (current != null) {
            next = current.next;//先把当前节点的下一个节点暂存起来，后边要用到
            current.next = reverseHead.next;//让当前节点指向新的链表的头的下一个。
            reverseHead.next = current;//让新链表头指向当前节点
            current = next;
        }
        head.next = reverseHead.next;

    }

    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //修改节点
    public void update(HeroNode newHeroNode) {
        //首先确定一下链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法修改");
        }
        //创建辅助结点来遍历链表
        HeroNode temp = head.next;
        //创建flag变量，判断该节点是否找到
        boolean flag = false;
        while (true) {
            //如果已经遍历到尾节点，终止
            if (temp == null) {
                break;
            }
            if (temp.heroNo == newHeroNode.heroNo) {
                //找到该节点
                flag = true;
                break;
            } else {
                temp = temp.next;
            }
        }
        //循环结束后通过flag值判断是否修改
        if (flag) {
            temp.heroName = newHeroNode.heroName;
            temp.nickName = newHeroNode.nickName;
            //next和no都不用变
        } else {
            System.out.printf("没有编号为%d的节点,无法修改\n", newHeroNode.heroNo);
        }
    }

    //删除节点
    //思路：
    //1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.heroNo 和要删除的节点的heroNo比较
    public void delete(HeroNode delHeroNode) {
        //如果链表为空，无法删除
        if (head.next == null) {
            System.out.println("链表为空，无法删除\n");
            return;
        }
        //构建辅助节点，帮忙遍历链表
        HeroNode temp = head;
        //flag表示是否找到该节点的前一位，默认为false
        boolean flag = false;
        while (true) {
            //如果遍历到最后一位，说明该节点不存在，终止循环
            if (temp.next == null) {
                break;
            }
            if (temp.next.heroNo == delHeroNode.heroNo) {
                //找到了要删除的节点的上一位
                flag = true;
                break;
            } else {
                //没有找到，继续往后走
                temp = temp.next;
            }
        }
        //循环终止后通过判断flag值决定是否删除节点
        if (flag) {
            //如果要删除的节点在最后一位，则将上一位的next指向null
            /*if (temp.next.next==null){
                temp.next = null;
            }
            else {
                //要删除的节点不在最后一位
                temp.next = temp.next.next;
            }*/
            //如果要删除的节点在最后一位，则temp.next.next本身就等于null，以上两种情况可以合并
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有编号为%d的节点，无法删除\n", delHeroNode.heroNo);
        }
    }

    public void List() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助节点来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //如果还没有遍历到最后
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

//定义HeroNode，每一个HeroNode对象就是一个节点
class HeroNode {
    public int heroNo;
    public String heroName;
    public String nickName;
    HeroNode next;

    public HeroNode(int heroNo, String heroName, String nickName) {
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