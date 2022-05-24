package cn.whu.wy.learnjava.base.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Author WangYong
 * Date 2020/08/31
 * Time 14:44
 */
public class IocContainer {
    private Map<String, Object> iocMap = new HashMap<>();

    public void registBean(String className, Object instance) {
        iocMap.put(className, instance);
    }

    public Object removeBean(String className) {
        return iocMap.remove(className);
    }

    public Object getBean(String className) {
        return iocMap.get(className);
    }

}
