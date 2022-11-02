package cn.whu.wy.learnjava.base.multiextend;

/**
 * @author WangYong
 * Date 2022/11/02
 * Time 16:13
 */
public class XiaoMing implements Someone{
    @Override
    public void work() {
        System.out.println("xiao ming is working");
    }

    @Override
    public void takeBaby() {
        System.out.println("xiao ming is taking baby");
    }
}
