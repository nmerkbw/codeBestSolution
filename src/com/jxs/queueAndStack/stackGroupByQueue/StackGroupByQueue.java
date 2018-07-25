package com.jxs.queueAndStack.stackGroupByQueue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用两个队列实现一个栈
 *
 * 方法：使用两个队列完成，压栈时使用一个队列dataQueue进行压入，弹出时将dataQueue中的元素依次
 * 出队列并依次入队进入helpQueue中，保留dataQueue队列中队尾的元素并进行出队列，最后再交换dataQueue
 * 和helpQueue的引用,peek时也是对dataQueue中的队尾元素进行出队列操作，
 * 然后再将这个元素重新装入helpQueue中最后再交换dataQueue和helpQueue的引用
 * */
public class StackGroupByQueue {

    private Queue<Integer> dataQueue;
    private Queue<Integer> helpQueue;

    public StackGroupByQueue() {

        dataQueue = new LinkedList<>();
        helpQueue = new LinkedList<>();
    }

    public void push(int num) {

        dataQueue.add(num);
    }

    public int pop() {

        int num = 0;
        if (dataQueue.isEmpty()) {
            throw new RuntimeException("栈为空！");
        } else {
            while (dataQueue.size() > 1) {
                helpQueue.add(dataQueue.poll());
            }
            num = dataQueue.poll();
            swap();
        }
        return num;
    }

    public int peek() {

        int num = 0;
        if (dataQueue.isEmpty()) {
            throw new RuntimeException("栈为空！");
        } else {
            while (dataQueue.size() > 1) {
                helpQueue.add(dataQueue.poll());
            }
            num = dataQueue.poll();
            // 获取值后重新装入
            helpQueue.add(num);
            swap();
        }
        return num;
    }

    // 交换引用，使得dataQueue将队尾元素弹出后重新指向之前移除装在helpQueue中的元素
    private void swap() {

        Queue<Integer> tempQueue = dataQueue;
        dataQueue = helpQueue;
        helpQueue = tempQueue;
    }

    @Test
    public void test() {

        StackGroupByQueue myQueue = new StackGroupByQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);

        for (int i = 0; i< 5; i++) {
            System.out.print(myQueue.pop() + " ");
        }
    }
}
