package com.jxs.queueAndStack.sortStackByStack;

import org.junit.Test;

import java.util.Stack;

/**
 * 使用一个栈实现另一个栈的排序
 *
 * 使用一个help栈，将需要排序的栈stack的栈顶弹出保存在一个临时变量中，然后将help栈的栈顶元素和要
 * 排序的栈的弹出元素做比较，如果help栈顶的元素更小，就将help栈全部压入到stack中，然后将保存的临时
 * 变量存入到help栈中，然后stack按之前的规则进行装入到help栈中。如果stack弹出的元素更小，就直接装
 * 入help栈中
 * */
public class SortStackByStack {

    private Stack<Integer> helpStack;

    public SortStackByStack() {
        helpStack = new Stack<>();
    }

    public void sortStackByStack(Stack<Integer> stack) {

        while (!stack.isEmpty()) {
            int temp = stack.pop();
            if (helpStack.isEmpty()) {
                helpStack.push(temp);
            } else if (temp <= helpStack.peek()) {
                helpStack.push(temp);
            } else if (temp > helpStack.peek()) {
                while (!helpStack.isEmpty()) {
                    stack.push(helpStack.pop());
                }
                helpStack.push(temp);
            }
        }
    }

    @Test
    public void test() {

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(5);
        stack.push(2);
        stack.push(4);
        stack.push(1);

        SortStackByStack s = new SortStackByStack();
        s.sortStackByStack(stack);
        while (!s.helpStack.isEmpty()) {
            System.out.println(s.helpStack.pop());
        }
    }
}
