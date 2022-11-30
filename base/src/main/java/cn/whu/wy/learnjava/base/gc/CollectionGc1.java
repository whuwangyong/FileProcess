package cn.whu.wy.learnjava.base.gc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CollectionGc1 {
    public static void main(String[] args) throws InterruptedException {

        Map<Integer, User> map = new HashMap<>();
        List<User> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            map.put(i, new User("map-user" + i));
            list.add(new User("list-user" + i));
        }

        map.clear();
        list.clear();
        System.gc();
        TimeUnit.SECONDS.sleep(4);
    }

}

/**
 * output：
 * user0: Finalize method is called
 * user1: Finalize method is called
 * user2: Finalize method is called
 * user3: Finalize method is called
 * user4: Finalize method is called
 * user5: Finalize method is called
 * user9: Finalize method is called
 * user6: Finalize method is called
 * user7: Finalize method is called
 * user8: Finalize method is called
 */