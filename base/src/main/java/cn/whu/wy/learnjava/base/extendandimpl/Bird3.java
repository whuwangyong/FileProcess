package cn.whu.wy.learnjava.base.extendandimpl;

public class Bird3 implements Flyable {
    private Bird bird;

    public Bird3(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void fly() {
        long start = System.nanoTime();

        bird.fly();

        long end = System.nanoTime();
        System.out.println("[implement] Fly time = " + (end - start));
    }
}
