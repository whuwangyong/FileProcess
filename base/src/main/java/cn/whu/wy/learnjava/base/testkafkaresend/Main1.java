package cn.whu.wy.learnjava.base.testkafkaresend;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 有业务逻辑：
 * 两个生产者，从各自的db读数据，发到同一个topicPartition。
 * 如果该逻辑做两次，消息在topicPartition的顺序肯定是不一样的。
 * 本代码用于测试差异有多大。
 */
public class Main1 {

    // 生成业务消息
    static List<BizMsg> genBizMsgs(String biz, int total) {
        List<BizMsg> list = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            list.add(new BizMsg(biz, i));
        }
        return list;
    }


    // 模拟topic的一个分区
    static volatile List<BizMsg> q = new ArrayList<>();

    // 模拟往分区发消息
    static synchronized void send(BizMsg bizMsg) {
        q.add(bizMsg);
    }


    // 模拟两个业务同时往一个分区写消息
    static void run(List<BizMsg> bizAMsgs, List<BizMsg> bizBMsgs) throws InterruptedException {
        q.clear();

        new Thread(() -> bizAMsgs.forEach(Main1::send)).start();
        new Thread(() -> bizBMsgs.forEach(Main1::send)).start();

        while (q.size() < bizAMsgs.size() + bizBMsgs.size()) {
            TimeUnit.SECONDS.sleep(1);
        }
    }

    // 模拟从分区拉消息
    static List<BizMsg> poll(BizMsg[] partition, int start, int size) {
        List<BizMsg> res = new LinkedList<>();

        for (int i = start; i < (start + size) && i < partition.length; i++) {
            res.add(partition[i]);
        }
//        System.out.println("poll, start=" + start);
        return res;

    }

    public static void main(String[] args) throws InterruptedException {

        // 每个业务发送的消息数量
        final int BIZ_COUNT = 10_0000;

        List<BizMsg> bizAMsgs = genBizMsgs("A", BIZ_COUNT);
        List<BizMsg> bizBMsgs = genBizMsgs("B", BIZ_COUNT);

        // 假设这是生产机房的顺序
        run(bizAMsgs, bizBMsgs);
        BizMsg[] production = q.toArray(new BizMsg[0]);

        // 备机房重跑，生成的新的顺序
        run(bizAMsgs, bizBMsgs);
        BizMsg[] backup = q.toArray(new BizMsg[0]);

        System.out.println("run done");


        // 每次poll消息的批次大小
        int batchSize = 10000;
        // 记录从poll下来的批次里面直接匹配上的数量
        int okSum = 0;
        // 记录从缓存里面匹配上的消息数量
        int cacheSum = 0;
        // 记录未命中，需再次poll的次数
        int badSum = 0;
        // 缓存
        List<BizMsg> cache = new LinkedList<>();
        // 记录缓存达到的最大值
        int cacheMax = 0;

        int pollStart = 0;
        int currentIndex = 0; // 当前需要的消息

        long startTime = System.currentTimeMillis();
        while (true) {
            List<BizMsg> poll = poll(backup, pollStart, batchSize);
            if (poll.size() == 0) break;
            pollStart += batchSize;


            for (int i = currentIndex; i < production.length; i++) {
                if (poll.contains(production[i])) {
                    // 这一批包含的，可以处理
                    okSum++;
                    poll.remove(production[i]);
                } else if (cache.contains(production[i])) {
                    // 如果缓存里有，也行
                    cacheSum++;
                    // 将命中的移除
                    cache.remove(production[i]);
                } else {
                    badSum++;
                    // 这批消息里面没有需要的消息，说明需要的消息去了后面，那只能拉下一批
                    // 但是当前这一批剩下的怎么办？全部缓存
                    cache.addAll(poll);
                    cacheMax = Math.max(cacheMax, cache.size());

                    // 记下当前需要的这个消息，下一批拉下来了再匹配
                    currentIndex = i;
                    break;
                }
            }

            System.out.println("首次命中:" + okSum);
            System.out.println("缓存命中:" + cacheSum);
            System.out.println("合计命中:" + (okSum + cacheSum));
            System.out.println("未命中需再次poll:" + badSum);
            System.out.println("缓存大小:" + cache.size());
            System.out.println("---------------------------");

        }

        long cost = System.currentTimeMillis() - startTime;
        System.out.println("缓存最大容量:" + cacheMax
                + ", 缓存比例:" + 100 * cacheMax / (BIZ_COUNT * 2) + "%, "
                + "耗时:" + cost / 1000 + "s");

    }


}

