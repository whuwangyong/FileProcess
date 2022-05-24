package cn.whu.wy.learnjava.base.thread;

import cn.whu.wy.learnjava.base.utils.log.Log;
import cn.whu.wy.learnjava.base.utils.log.impl.BetterLog;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 服务注册中心
 * <p>
 * Author WangYong
 * Date 2020/08/31
 * Time 10:59
 */
public class ServiceRegistry {
    private Log log = new BetterLog();
    private Map<ExecutorService, Service> serviceList = new HashMap<>();

    public void regist(ExecutorService executorService, Service service) {
        serviceList.put(executorService, service);
    }

    public void start() {
        log.info("服务注册中心启动");
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            serviceList.forEach((k, v) -> {
                        if (k.isTerminated() || k.isShutdown()) {
                            v.start();
                        }
                    }
            );

        }, 1, 2, TimeUnit.SECONDS);
    }
}
