package cn.whu.wy.learnjava.base.localdatetime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 注意：LocalDateTime.now()在openjdk8和11上返回的精度不一致
 * <p>
 * Author WangYong
 * Date 2020/08/26
 * Time 16:58
 */
public class TestNow {

    LocalDateTime t1 = LocalDateTime.of(2020, 8, 26, 16, 0, 0);
    LocalDateTime t2 = LocalDateTime.of(2020, 8, 26, 16, 5, 0);
    LocalDateTime t3 = LocalDateTime.of(2020, 8, 26, 16, 5, 5);
    LocalDateTime t4 = LocalDateTime.of(2020, 8, 26, 16, 5, 5, 5);
    LocalDateTime t5 = LocalDateTime.of(2020, 8, 26, 16, 5, 5, 125_000_000);

    public static void main(String[] args) {
        TestNow test = new TestNow();
        test.testToString();
        test.localTimeTest();
        test.getFormatedTimeStamp();
        test.testNanoTime();
        test.getBizNo();
        test.getBizNo2();
    }


    /**
     * 测试 toString() 方法
     * output:
     * 2020-08-26T16:00
     * 2020-08-26T16:05
     * 2020-08-26T16:05:05
     * 2020-08-26T16:05:05.000000005
     * 2020-08-26T16:05:05.125
     * 2020-08-26T16:10:39.037   # jdk11: 2022-05-24T17:43:19.891620
     * 结论：toString()方法输出的字符串长度不固定。因此，对其调用 substring() 方法时注意 StringIndexOutOfBoundsException
     */
    private void testToString() {
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(LocalDateTime.now());
    }


    /**
     * 测试LocalTime.now()的时间精度
     * output:
     * 19:32:07.492 # jdk11: 17:43:19.906620900
     * 08:19
     * 08:19:40
     * 08:19:40.000000025
     * 08:19:40.000001
     * 08:19:40.001
     * 08:19:40.999999999
     * 08:19:40.001000200
     * a=0,b=999436,c=564 # jdk11: a=1000000, b=0 ,c=0
     * 结论：精确到毫秒。多数时候获取的时间戳为12位长度，当毫秒数为0时，长度为8
     */
    private void localTimeTest() {
        LocalTime t1 = LocalTime.now();
        LocalTime t2 = LocalTime.of(8, 19);
        LocalTime t3 = LocalTime.of(8, 19, 40);
        LocalTime t4 = LocalTime.of(8, 19, 40, 25);
        LocalTime t5 = LocalTime.of(8, 19, 40, 1000);
        LocalTime t6 = LocalTime.of(8, 19, 40, 1000_000);
        LocalTime t7 = LocalTime.of(8, 19, 40, 999_999_999);
        LocalTime t8 = LocalTime.of(8, 19, 40, 1000_200);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);
        System.out.println(t8);

        int a = 0, b = 0, c = 0;
        for (int i = 0; i < 1000_000; i++) {
            LocalTime t = LocalTime.now();
            if (t.toString().length() > 12) {
//                System.out.println(t);
                a++;
            }
            if (t.toString().length() == 12) b++;
            if (t.toString().length() < 12) c++;
        }
        System.out.printf("a=%d,b=%d,c=%d", a, b, c);
    }

    /**
     * 获取指定格式/长度的时间戳，可以用于生成序列号
     * output:
     * 2020-08-26 16:00:00.000
     * 2020-08-26 16:05:00.000
     * 2020-08-26 16:05:05.000
     * 2020-08-26 16:05:05.000
     * 2020-08-26 16:05:05.125
     * 2020-08-27 17:14:23.394
     */
    private void getFormatedTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(formatter.format(t1));
        System.out.println(formatter.format(t2));
        System.out.println(formatter.format(t3));
        System.out.println(formatter.format(t4));
        System.out.println(formatter.format(t5));
        System.out.println(formatter.format(LocalDateTime.now()));
    }


    /**
     * 测试本机的时间精度
     * output:
     * 19274592174415
     * 19274592385631
     * 19274592412116
     * 19274592434297
     * 19274592454160
     * <p>
     * 今天再次运行，output：
     * 329294542345655
     * 329294542741603
     * 329294542764777
     * 329294542789275
     * 329294542810132
     * <p>
     * openjdk11: 又多了1位
     * 1325689474384274
     * 1325689474420691
     * 1325689474446513
     * 1325689474478295
     * 1325689474502793
     * 结论：
     * 1.精度为纳秒；
     * 2.输出的长度不固定；
     * 3.不能通过纳秒数推算当前时间，因为它是以一个随机时刻作为基点，不是1970-1-1
     */
    private void testNanoTime() {
        for (int i = 0; i < 5; i++) {
            System.out.println(System.nanoTime());
        }
    }

    /**
     * 测试以毫秒为精度能否用于流水号
     * output:
     * 20200827204521588
     * 20200827204521591
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521592
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521593
     * 20200827204521594
     * <p>
     * 结论：不行。1毫秒会导致10个冲重复的流水号。对于性能强的机器，重复的更多
     */
    private void getBizNo() {
        for (int i = 0; i < 50; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String timeStamp = formatter.format(LocalDateTime.now());
            System.out.println(timeStamp);
        }
    }

    /**
     * 一共17+6=23位
     * 改进的流水号生成器，后面加上当前的微秒数和纳秒数
     * 20220524174320402865922
     * 20220524174320432295386
     * 20220524174320433706890
     * 20220524174320433986302
     * 20220524174320433237244
     * 20220524174320434490502
     * 20220524174320434732836
     * 20220524174320434975170
     * 20220524174320434243658
     * 20220524174320435518435
     * ...
     */
    private void getBizNo2() {
        for (int i = 0; i < 50; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            LocalDateTime now = LocalDateTime.now();
            long ns = System.nanoTime();
            String s = String.valueOf(ns);
            System.out.println(formatter.format(now) + s.substring(s.length() - 6));
        }
    }

}
