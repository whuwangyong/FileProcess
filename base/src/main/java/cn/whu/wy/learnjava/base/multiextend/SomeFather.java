package cn.whu.wy.learnjava.base.multiextend;

/**
 * @author WangYong
 * Date 2022/11/02
 * Time 16:15
 */
public class SomeFather implements Father {
    @Override
    public void takeBaby() {
        System.out.println("some father is taking baby");
    }
}
