package cn.whu.wy.learnjava.base.gc.mystack;

import cn.whu.wy.learnjava.base.gc.User;

import java.util.Arrays;

public class MyStack {

    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 16;

    public MyStack() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void push(Object o) {
        ensureCapacity();
        elements[size++] = o;
    }

    /**
     * 该方法有内存泄露的缺陷（参考《Effective Java 第3版 第7条》）
     * 修正：
     * Object result = elements[--size]
     * elements[size] = null; // Eliminate obsolete reference
     * return result;
     *
     * @return
     */
    public Object pop() {
        if (size == 0) {
            throw new RuntimeException("empty");
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, size * 2 + 1);
        }
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    public static void main(String[] args) {
        User a = new User("a");
        User b = new User("b");
        User c = new User("c");
        User d = new User("d");

        MyStack myStack = new MyStack();
        myStack.push(a);
        myStack.push(b);
        myStack.push(c);
        System.out.println(myStack);

        System.out.println(myStack.pop());
        System.out.println(myStack);

        myStack.push(d);
        System.out.println(myStack);

    }
}
