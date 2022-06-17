package cn.whu.wy.learnjava.base.lock;

import cn.whu.wy.learnjava.base.utils.log.Logger;
import cn.whu.wy.learnjava.base.utils.log.impl.SimpleLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/17
 */
public class SellTickets {

    int tickets = 1000;
    int sold = 0;

    private static final Logger log = new SimpleLogger();

    public static void main(String[] args) {
        SellTickets sellTickets = new SellTickets();
        Runnable r = () -> {
            while (sellTickets.tickets > 0) {
                sellTickets.sellOne();
                log.info("sold one");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("sold=" + sellTickets.sold);
        };

        Thread windows1 = new Thread(r);
        Thread windows2 = new Thread(r);
        Thread windows3 = new Thread(r);

        windows1.start();
        windows2.start();
        windows3.start();
    }

    /**
     * 该方法如果不加锁，搜索日志里面的 sold one，会超过1000个；最后输出的结果sold也大于1000
     */
    synchronized void sellOne() {
        tickets--;
        sold++;
    }
}
