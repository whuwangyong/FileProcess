package cn.whu.wy.learnjava.serial;

import java.math.BigDecimal;

public class MetaTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("100.123");


        byte b = bigDecimal.byteValueExact();

        System.out.println(b);


    }
}
