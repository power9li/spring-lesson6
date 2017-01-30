package com.power.spring.lesson6;

import java.util.Stack;

/**
 * Created by shenli on 2017/1/30.
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        String s = stack.iterator().next();
        System.out.println("s = " + s);
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack.peek() = " + stack.peek());
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack.peek() = " + stack.peek());
        System.out.println("stack.size() = " + stack.size());

        System.out.println();
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.size() = " + stack.size());

    }
}
