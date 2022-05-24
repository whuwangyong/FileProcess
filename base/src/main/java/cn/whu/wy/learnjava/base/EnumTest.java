package cn.whu.wy.learnjava.base;

public class EnumTest {

    public static void main(String[] args) {

        System.out.println(Operation.insert);
        System.out.println(Operation.insert.name());

        System.out.println(Operation.insert.ordinal());
        System.out.println(Operation.update.ordinal());
        System.out.println(Operation.delete.ordinal());

    }

    enum Operation {insert, update, delete}
}

/**
 * output:
 * insert
 * insert
 * 0
 * 1
 * 2
 */
