package cn.whu.wy.learnjava.base.tryexception;

/**
 * try catch finally，如果catch里面抛出了异常，finally还会执行吗？
 * 除了System.exit(0)，return和throw都能执行finally
 *
 * @author WangYong
 * Date 2022/11/16
 * Time 16:42
 */
public class TryCatchException {

    public static void main(String[] args) {

        f();
    }

    private static int f() {
        try {
            System.out.println("try");
            return 1 / 0;
        } catch (Exception e) {
            System.out.println("catch:" + e.getMessage());
//            throw e;
//            System.exit(0);
            return -1;
        } finally {
            System.out.println("finally");
        }
    }
}
