package com.jxs.queueAndStack.getMinStack;

import org.junit.Test;

import java.util.Stack;

/**
 * 设置一个有getMin功能的栈
 *
 * 方法1：使用两个栈，stackData用来存放数据，stackMin用来存放最小值
 * 当压栈时先压入到stackData中，然后判断压入的值是否比stackMin栈顶的值小，
 * 如果小于等于stackMin栈顶元素则压入到stackMin中，如果大于等于stackMin栈顶元素则不压入
 *
 * 当弹栈时判断stackData弹出的元素是否与stackMin栈顶的元素相当，如果相等就一并弹出，
 * 如果不相等就只弹出stackData的栈顶元素
 *
 * 获取栈中的最小元素只需要获取stackMin的栈顶元素即可
 * */
public class GetMinStack1 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinStack1() {

        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int num) {

        stackData.push(num);
        if (stackMin.isEmpty() || stackMin.peek() >= num) {
            stackMin.push(num);
        }
    }

    public int pop() {
        // 边界条件判断千万别忘了写！
        if (stackData.isEmpty()) {
            throw new RuntimeException("栈为空，无法弹出元素！");
        }
        int num = stackData.pop();
        if (stackMin.peek() == num) {
            stackMin.pop();
        }
        return num;
    }

    public int getMin() {

        // 边界条件判断千万别忘了写！
        if (stackMin.isEmpty()) {
            throw new NullPointerException("栈为空，不可再进行操作！");
        }
        return stackMin.peek();
    }

    @Test
    public void test() {

        GetMinStack1 mystack = new GetMinStack1();
        mystack.push(5);
        mystack.push(7);
        mystack.push(2);
        mystack.push(4);
        mystack.push(3);

        System.out.println(mystack.getMin());
    }
}
