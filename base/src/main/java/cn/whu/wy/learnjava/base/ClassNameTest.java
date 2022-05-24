package cn.whu.wy.learnjava.base;

public class ClassNameTest {
    public static void main(String[] args) {
        Person p = new Person();
        System.out.println("getName:" + p.getClass().getName());
        System.out.println("getTypeName:" + p.getClass().getTypeName());
        System.out.println("getSimpleName:" + p.getClass().getSimpleName());
        System.out.println("getCanonicalName:" + p.getClass().getCanonicalName());
    }

}

class Person {
    String name;
}

/**
 * output:
 * getName:cn.whu.wy.learnjava.base.Person
 * getTypeName:cn.whu.wy.learnjava.base.Person
 * getSimpleName:Person
 * getCanonicalName:cn.whu.wy.learnjava.base.Person
 */