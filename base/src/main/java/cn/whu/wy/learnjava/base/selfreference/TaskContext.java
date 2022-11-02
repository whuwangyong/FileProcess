package cn.whu.wy.learnjava.base.selfreference;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangYong
 * Date 2022/08/26
 * Time 10:42
 */
@Data
public class TaskContext {

    List<TaskRunnable> taskRunnables = new ArrayList<>();
    Map<String, Thread> threadsMap = new HashMap<>();
}
