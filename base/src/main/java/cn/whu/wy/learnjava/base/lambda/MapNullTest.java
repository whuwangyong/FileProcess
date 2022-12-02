package cn.whu.wy.learnjava.base.lambda;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 输出：
 * [test, null]
 * [test]
 *
 * @author WangYong
 * Date 2022/12/02
 * Time 18:50
 */
public class MapNullTest {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("test"));
        users.add(new User());

        List<String> names1 = users.stream().map(User::getName).collect(Collectors.toList());
        List<String> names2 = users.stream().map(User::getName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(names1);
        System.out.println(names2);
    }

    @Data
    static class User {
        String name;


        public User(String name) {
            this.name = name;
        }

        public User() {
        }
    }
}
