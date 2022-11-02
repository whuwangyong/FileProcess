package cn.whu.wy.learnjava.base.lambda;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WangYong
 * Date 2022/08/26
 * Time 14:18
 */
public class FlatMapTest {

    void test() {

        // 京东用户
        List<User> jd = new ArrayList<>();
        jd.add(new User("a"));
        jd.add(new User("b"));
        jd.add(new User("c"));
        jd.add(new User("d"));

        // 淘宝用户
        List<User> tb = new ArrayList<>();
        tb.add(new User("a"));
        tb.add(new User("c"));
        tb.add(new User("e"));
        tb.add(new User("f"));

        Map<String, List<User>> map = new HashMap<>();
        map.put("jingdong", jd);
        map.put("taobao", tb);

        // 交集
        System.out.println(jd.stream().filter(tb::contains).collect(Collectors.toList()));

        // 并集未去重
        System.out.println(map.values().stream().flatMap(Collection::stream).collect(Collectors.toList()));
        // 并集去重
        System.out.println(map.values().stream().flatMap(Collection::stream).distinct().collect(Collectors.toList()));

        // 查找
        System.out.println(map.values().stream().flatMap(Collection::stream).filter(user -> user.name.equals("a")).findAny());


    }

    public static void main(String[] args) {
        new FlatMapTest().test();
    }


    @Data
    class User {
        String name;


        public User(String name) {
            this.name = name;

        }
    }
}
