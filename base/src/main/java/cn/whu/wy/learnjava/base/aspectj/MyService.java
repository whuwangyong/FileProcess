package cn.whu.wy.learnjava.base.aspectj;

public class MyService {

    public void performAction() {
        System.out.println("Performing action...");
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        service.performAction();  // 会先打印 "Before performing action..."
    }


}
