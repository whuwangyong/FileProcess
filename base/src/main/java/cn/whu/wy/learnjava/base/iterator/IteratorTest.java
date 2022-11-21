package cn.whu.wy.learnjava.base.iterator;

import java.util.HashMap;
import java.util.Map;

/**
 * 每次返回的iterator都是新的。
 * 多线程使用不同的iterator，互不影响
 *
 * @author WangYong
 * Date 2022/11/21
 * Time 16:27
 */
public class IteratorTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i, "a-" + i);
        }

        System.out.println(map.values().iterator());
        System.out.println(map.values().iterator());

    }
}
