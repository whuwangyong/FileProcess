package cn.whu.wy.learnjava.base.lock;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/17
 */
public class TestBizRunnable {



    @SneakyThrows
    @Test
    public void test(){
        BizRunnable runnable = new BizRunnable();
        Thread thread = new Thread(runnable);
        thread.setName("t_biz");
        thread.start();
        TimeUnit.SECONDS.sleep(2);

        runnable.pause();
        TimeUnit.SECONDS.sleep(3);

        runnable.resume();
    }
}
