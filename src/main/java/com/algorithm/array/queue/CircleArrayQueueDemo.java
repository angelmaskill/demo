package com.algorithm.array.queue;

import java.util.Scanner;

/**
 * @ClassName Queue
 * @Description 数组模拟队列
 * @Author yanlu.myl
 * @Date 2021-03-29 6:59
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(5);//说明：设置4，但是有效数据最大是3，有一个空间是作为约定的
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("a(add):添加数据");
            System.out.println("g(get):得到数据");
            System.out.println("s(show):显示数据");
            System.out.println("q(quit):退出程序");
            System.out.println("h(head):查看队列的第一个数据");
            char text = s.next().charAt(0);
            switch (text) {
                case 'a':
                    System.out.println("请输入要添加的数据");
                    circleArrayQueue.addElement(s.nextInt());
                    break;

                case 'g':
                    try {
                        System.out.println("得到的数据为" + circleArrayQueue.getElement());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'q':
                    s.close();//关闭输入器防止异常
                    loop = false;
                    break;
                case 'h':
                    circleArrayQueue.headQueue();
                    break;
            }
        }
    }
}

class CircleArrayQueue {
    private int front;// 队列头
    private int rear;//队列尾
    private int maxSize; //标识队列的最大容量
    private int[] array;//该数组用于存放数据,模拟队列

    public CircleArrayQueue(int arrayMaxSize) {
        this.maxSize = arrayMaxSize;
        array = new int[maxSize];
        front = 0;
        rear = 0;
    }

    private boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    private boolean isEmpty() {
        return front == rear;
    }

    public void addElement(int element) {
        if (isFull()) {
            System.out.println("队列满,不能加入数据~");
            return;
        }
        array[rear] = element;
        //将 rear 后移，若到最后则转移到数组头部
        rear = (rear + 1) % maxSize;
    }

    public int getElement() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空,无法获取数据~");
        }
        int value = array[front];
        /*front 指针后移一个位置，若到了最后则转到数组头部*/
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示整个队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据~");
            return;
        }
        /*从 front 开始遍历，遍历有效个元素*/
        for (int i = front; i < front + size(); i++) {
            int index = i % maxSize;
            System.out.printf("array[%d]=%d\n", index, array[index]);
        }
    }

    /*求出当前队列有效数据的个数*/
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据~");
            throw new RuntimeException("队列已空,无法获取数据~");
        }
        return array[front];
    }
}
