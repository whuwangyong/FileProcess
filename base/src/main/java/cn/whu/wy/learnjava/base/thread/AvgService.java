package cn.whu.wy.learnjava.base.thread;

import cn.whu.wy.learnjava.base.utils.log.Log;
import cn.whu.wy.learnjava.base.utils.log.impl.BetterLog;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计算学生平均分的服务
 * <p>
 * Author WangYong
 * Date 2020/08/31
 * Time 11:01
 */
public class AvgService implements Service {

    private Log log = new BetterLog();


    @Override
    public ExecutorService start() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this::doStart);
        return executorService;
    }

    private void doStart() {
        DataHolder dataHolder = new DataHolder();
        Map<Integer, Grade> gradeMap = dataHolder.getGradeMap();
        List<Integer> processedList = dataHolder.getProcessedList();
        gradeMap.forEach((k, v) -> {
            if (!processedList.contains(k)) {
                log.info("正在处理sno=" + k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                v.avg = (v.chineseScore + v.mathScore + v.englishScore) / 3;
                processedList.add(k);

            }
            if (k % 10 == 0) {
                throw new RuntimeException("假如出现了运行时异常，服务挂了……");
            }
        });
    }
}
