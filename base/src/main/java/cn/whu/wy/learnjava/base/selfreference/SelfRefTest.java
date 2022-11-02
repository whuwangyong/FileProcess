package cn.whu.wy.learnjava.base.selfreference;

import lombok.Data;

import java.util.List;

/**
 * @author WangYong
 * Date 2022/08/26
 * Time 10:32
 */

@Data
public class SelfRefTest {


    public static void main(String[] args) {
        TaskContext taskContext = new TaskContext();
        List<TaskRunnable> taskRunnables = taskContext.getTaskRunnables();
        taskRunnables.add(new TaskRunnable("task-1", taskContext));
        taskRunnables.add(new TaskRunnable("task-2", taskContext));
        taskRunnables.add(new TaskRunnable("task-3", taskContext));

        taskRunnables.forEach(r -> {
            Thread t = new Thread(r, "t_" + r.name);
            taskContext.getThreadsMap().put(t.getName(), t);
            t.start();
        });
    }


}
