package cn.whu.wy.learnjava.base.selfreference;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/08/26
 * Time 10:33
 */
public class TaskRunnable implements Runnable {

    String name;

    TaskContext taskContext;

    public TaskRunnable(String name, TaskContext taskContext) {
        this.name = name;
        this.taskContext = taskContext;
    }

    @Override
    public void run() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(name);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted:");
                e.printStackTrace();
                return;
            }
            // 自己中断自己
            if (i == 3) {
                Thread t = taskContext.getThreadsMap().get(Thread.currentThread().getName());
                t.interrupt();
            }
        }
        System.out.println("name:" + name + " remove " + this);
        taskContext.getTaskRunnables().remove(this);
        System.out.println("还剩：" + taskContext.getTaskRunnables().size());
    }
}
