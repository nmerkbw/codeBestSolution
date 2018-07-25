package com.jxs.queueAndStack.stackHanoiProblem;

import org.junit.Test;

import java.util.Stack;

public class StackHanoiprobelm {

    public int hanoiProblem(int num, String left, String mid, String right) {

        Stack<Integer> lS = new Stack<>();
        Stack<Integer> mS = new Stack<>();
        Stack<Integer> rS = new Stack<>();

        // 保证第一个数为任意值时都可以进行push
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action record = Action.No;
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, right);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    private int fStackTotStack(Action record, Action preNoAct, Action nowAct,
                               Stack<Integer> fStack, Stack<Integer> tStack,
                               String from, String to) {
        if (record != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record = nowAct;
            return 1;
        }
        return 0;
    }

    @Test
    public void test() {

        StackHanoiprobelm sh = new StackHanoiprobelm();
        sh.hanoiProblem(3, "A", "B", "C");
    }
}
