package com.jxs.queueAndStack.recursiveStack;

import org.junit.Test;

import java.util.Stack;

public class RecursiveStack {

    public int getDeleteLastElement(Stack<Integer> stack) {

        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = getDeleteLastElement(stack);
        stack.push(result);
        return last;
    }

    public void reverse(Stack<Integer> stack) {

        if (stack.isEmpty()) {
            return;
        }
        int i = getDeleteLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    @Test
    public void test() {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        reverse(stack);
        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop() + " ");
        }
    }
}
