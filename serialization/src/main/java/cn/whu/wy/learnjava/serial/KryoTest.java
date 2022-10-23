package cn.whu.wy.learnjava.serial;

import cn.whu.wy.learnjava.serial.pojo.User;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * outPut:
 * se:OptionalDouble[4033800.0]
 * de:OptionalDouble[1349600.0]
 * seAndDe:OptionalDouble[1085300.0]
 */
public class KryoTest {

    public static void main(String[] args) {
        KryoTest test = new KryoTest();
        List<User> list = new GenTestObjs().genObjs();

        // 跑10轮取均值
        List<Long> se = new ArrayList<>();
        List<Long> de = new ArrayList<>();
        List<Long> seAndDe = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            se.add(test.se(list));
            de.add(test.de(list));
            seAndDe.add(test.seAndDe(list));
        }
        System.out.println("se:" + se.stream().mapToLong(e -> e).average());
        System.out.println("de:" + de.stream().mapToLong(e -> e).average());
        System.out.println("seAndDe:" + seAndDe.stream().mapToLong(e -> e).average());
    }


    long se(List<User> list) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        long start = System.currentTimeMillis();
        list.forEach(o -> {
            kryo.writeObject(output, o);
            output.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.reset();
        });
        long end = System.currentTimeMillis();
        long tps = list.size() / (end - start) * 1000;
        return tps;
    }

    long de(List<User> list) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        List<byte[]> lb = new ArrayList<>(list.size());
        list.forEach(o -> {
            kryo.writeObject(output, o);
            output.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.reset();
            lb.add(bytes);
        });

        long start = System.currentTimeMillis();
        lb.forEach(bytes -> {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Input input = new Input(byteArrayInputStream);
            User user = kryo.readObject(input, User.class);
        });
        long end = System.currentTimeMillis();
        long tps = list.size() / (end - start) * 1000;
        return tps;
    }

    long seAndDe(List<User> list) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);

        long start = System.currentTimeMillis();
        list.forEach(o -> {
            kryo.writeObject(output, o);
            output.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.reset();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Input input = new Input(byteArrayInputStream);
            User user = kryo.readObject(input, User.class);
            assert user.equals(o);
        });
        long end = System.currentTimeMillis();
        long tps = list.size() / (end - start) * 1000;
        return tps;
    }


}
