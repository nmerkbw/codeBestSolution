package com.jxs.queueAndStack.queueGroupByStack;

import org.junit.Test;

import java.util.Stack;

/**
 * 使用两个栈实现一个队列
 *
 * 方法：一个栈stackPush将元素压入后全部弹出依次装入另一个栈stackPop中
 * stackPop再将元素弹出即可完成
 * 注意：①stackPush在压栈时必须一次性全部压入完成
 * ②stackPop不为空时，stackPush中的元素不可弹出并压入到stackPop中
 * */
public class QueueGroupByStack {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public QueueGroupByStack() {

        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void add(int num) {

        stackPush.push(num);
    }

    public int poll() {

        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("队列为空！");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {

        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return stackPop.peek();
    }

    @Test
    public void test() {

        QueueGroupByStack myQueue = new QueueGroupByStack();
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        for (int i = 0; i < 5; i++) {
            System.out.print(myQueue.poll()+" ");
        }
    }
}
