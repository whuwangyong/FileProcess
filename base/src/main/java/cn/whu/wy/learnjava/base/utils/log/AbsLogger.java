package cn.whu.wy.learnjava.base.utils.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WangYong
 * Date 2022/06/17
 */
public abstract class AbsLogger implements Logger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public String format(String s) {
        String threadName = Thread.currentThread().getName();
        String className = getCallerClassName();

        StringBuilder sb = new StringBuilder();
        sb.append(formatter.format(LocalDateTime.now()));
        sb.append(" ").append(ProcessHandle.current().pid()).append(" ");
        sb.append(" --- ");
        sb.append("[").append(getFixedString(threadName, 15)).append("]");
        sb.append(" ").append(getFixedString(className, 30));
        sb.append("\t: ");
        sb.append(s);
        return sb.toString();
    }

    private String getCallerClassName() {
        // way 1：
        String callerClassName = new Exception().getStackTrace()[3].getClassName();
        // String calleeClassName = new Exception().getStackTrace()[0].getClassName();
        return callerClassName;

        // way 2：
        // StackWalker walker = StackWalker.getInstance(RETAIN_CLASS_REFERENCE);
        // return walker.getCallerClass().getName();
    }

    /**
     * 获取定长的字符串，超过的部分截断左边；不足的部分左边补空格
     */
    private String getFixedString(String s, int size) {
        int len = s.length();
        if (len >= size) {
            return s.substring(len - size);
        } else {
            char[] cs = new char[size - len];
            for (int i = 0; i < size - len; i++) {
                cs[i] = ' ';
            }
            return new String(cs) + s;
        }
    }
}
