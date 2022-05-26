package cn.whu.wy.learnjava.base.extendandimpl;

public class Main {
    public static void main(String[] args) {
        for(int i=0; i<1;i++) {
            Bird bird = new Bird();
            long start = System.nanoTime();
            bird.fly();
            long end = System.nanoTime();
            System.out.println("[new] Fly time = " + (end - start));
            System.out.println("---------------------------");


            Bird2 bird2 = new Bird2();
            start = System.nanoTime();
            bird2.fly();
            end = System.nanoTime();
            System.out.println("[extend] Fly time = " + (end - start));
            System.out.println("---------------------------");

            Bird3 bird3 = new Bird3(bird);
            start = System.nanoTime();
            bird3.fly();
            end = System.nanoTime();
            System.out.println("[implement] Fly time = " + (end - start));
            System.out.println("---------------------------");
        }


    }
}
