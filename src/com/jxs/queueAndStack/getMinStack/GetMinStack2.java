package com.jxs.queueAndStack.getMinStack;

import org.junit.Test;

import java.util.Stack;

/**
 * 设置一个有getMin功能的栈
 *
 * 方法2：使用两个栈，stackData用来存放数据，stackMin用来存放最小值
 * 当压栈时先压入到stackData中，然后判断压入的值是否比stackMin栈顶的值小，
 * 如果小于等于stackMin栈顶元素则压入到stackMin中，
 * 如果大于等于stackMin栈顶元素则将栈顶的元素重复压入
 *
 * 当弹栈时将stackData和stackMin的栈顶元素一起弹出
 *
 * 获取栈中的最小元素只需要获取stackMin的栈顶元素即可
 * */
public class GetMinStack2 {

    Stack<Integer> stackData;
    Stack<Integer> stackMin;

    public GetMinStack2() {

        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int num) {

        stackData.push(num);
        if (stackMin.isEmpty() || stackMin.peek() >= num) {
            stackMin.push(num);
        } else {
            stackMin.push(stackMin.peek());
        }
    }

    public int pop() {

        if (stackData.isEmpty() || stackMin.isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        int num = stackData.pop();
        stackMin.pop();
        return num;
    }

    public int getMin() {

        if (stackMin.isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        return stackMin.peek();
    }

    @Test
    public void test() {

        GetMinStack2 mystack = new GetMinStack2();
        mystack.push(5);
        mystack.push(7);
        mystack.push(2);
        mystack.push(4);
        mystack.push(3);
        mystack.pop();
        mystack.pop();
        System.out.println(mystack.getMin());
    }
}
