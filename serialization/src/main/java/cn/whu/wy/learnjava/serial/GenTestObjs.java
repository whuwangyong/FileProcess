package cn.whu.wy.learnjava.serial;

import cn.whu.wy.learnjava.serial.pojo.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GenTestObjs {
    public List<User> genObjs() {
        final int n = 100_0000;
        List<User> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            User user = new User();
            user.setAddress("广东省深圳市福田区深南大道");
            user.setId(System.nanoTime());
            user.setMale(user.getId() % 200 == 0);
            user.setName("hello" + i);
            user.setMoney(BigDecimal.valueOf(i));
            user.setScore(i + 3.141592653);

            list.add(user);
        }
        return list;
    }
}
