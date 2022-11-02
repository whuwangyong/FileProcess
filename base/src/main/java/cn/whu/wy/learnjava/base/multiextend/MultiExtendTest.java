package cn.whu.wy.learnjava.base.multiextend;

/**
 * @author WangYong
 * Date 2022/11/02
 * Time 16:12
 */
public class MultiExtendTest {
    public static void main(String[] args) {
        Father father = new SomeFather();
        Someone someone = (Someone) father; // ClassCastException
        someone.takeBaby();
        someone.work();

    }
}
