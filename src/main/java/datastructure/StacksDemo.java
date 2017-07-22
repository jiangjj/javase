package datastructure;

import java.util.Stack;

/**
 * Created by jiangjiajie on 2017/4/6.
 */
public class StacksDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}
