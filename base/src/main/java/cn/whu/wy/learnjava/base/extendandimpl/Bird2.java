package cn.whu.wy.learnjava.base.extendandimpl;

public class Bird2 extends Bird {
    @Override
    public void fly() {
        long start = System.nanoTime();

        super.fly();

        long end = System.nanoTime();
        System.out.println("[extend] Fly time = " + (end - start));
    }
}
