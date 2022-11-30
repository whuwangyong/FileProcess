package cn.whu.wy.learnjava.base.gc;

public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected void finalize() {
        System.out.println(name + ": Finalize method is called");
    }
}
