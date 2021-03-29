package com.algorithm.array.queue;

/**
 * @ClassName Queue
 * @Description 数组模拟队列
 * @Author yanlu.myl
 * @Date 2021-03-29 6:59
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        // 添加元素,看是否已满
        queue.addElement(1);
        queue.addElement(2);
        queue.addElement(3);
        queue.addElement(4);
        // 显示队列头元素
        int headQueue = queue.headQueue();
        System.out.println("the head is: " + headQueue);
        // 打印队列内容
        queue.showQueue();
        // 获取队列元素,直至为空
        int element = queue.getElement();
        System.out.println("current pop value is : " + element);
        element = queue.getElement();
        System.out.println("current pop value is : " + element);
        element = queue.getElement();
        System.out.println("current pop value is : " + element);
        element = queue.getElement();
        System.out.println("current pop value is : " + element);


    }
}

class ArrayQueue {
    private int front;// 队列头
    private int rear;//队列尾
    private int maxSize; //标识队列的最大容量
    private int[] array;//该数组用于存放数据,模拟队列

    public ArrayQueue(int arrayMaxSize) {
        this.maxSize = arrayMaxSize;
        array = new int[maxSize];
        front = -1; /*指向隊列头部, front是指向队列头的前一个位置*/
        rear = -1; /*指向队列尾部的数据(即就是队列最后一个数据)*/
    }

    private boolean isFull() {
        return rear == (maxSize - 1);
    }

    private boolean isEmpty() {
        return front == rear;
    }

    public void addElement(int element) {
        if (isFull()) {
            System.out.println("队列满,不能加入数据~");
            return;
        }
        rear++;// rear后移
        array[rear] = element;
    }

    public int getElement() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空,无法获取数据~");
        }
        front++;
        return array[front];
    }

    // 显示整个队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据~");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据~");
            throw new RuntimeException("队列已空,无法获取数据~");
        }
        return array[front + 1];
    }
}
