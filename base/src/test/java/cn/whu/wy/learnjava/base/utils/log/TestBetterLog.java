package cn.whu.wy.learnjava.base.utils.log;

import cn.whu.wy.learnjava.base.utils.log.impl.BetterLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author WangYong
 * Date 2022/06/17
 */
public class TestBetterLog {

    BetterLogger betterLog;

    @BeforeEach
    public void setUp() {
        betterLog = new BetterLogger();
    }

    @Test
    public void testGetFixedString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = betterLog.getClass().getDeclaredMethod("getFixedString", String.class, int.class);
        method.setAccessible(true);
        Assertions.assertEquals(method.invoke(betterLog, "123456789", 7), "3456789");
        Assertions.assertEquals(method.invoke(betterLog, "1234567", 7), "1234567");
        Assertions.assertEquals(method.invoke(betterLog, "1234", 7), "   1234");
    }
}
