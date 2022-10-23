package cn.whu.wy.learnjava.serial.pojo;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class User {
    long id;
    String name;
    boolean male;
    String address;
    BigDecimal money;
    double score;

    public byte[] toBytes() {
        ByteBuffer bf = ByteBuffer.allocate(1024);
        bf.putLong(this.id);
        bf.put(this.name.getBytes(StandardCharsets.UTF_8));
        bf.put((byte) (this.male ? 1 : 0));
        bf.put(this.address.getBytes(StandardCharsets.UTF_8));
        byte[] b_money = this.money.toString().getBytes(StandardCharsets.UTF_8);
        bf.put(b_money);
        bf.putDouble(this.score);
        return bf.array();
    }

    public User fromBytes(byte[] bytes) {
        ByteBuffer bf = ByteBuffer.wrap(bytes);
        User user = new User();
        user.setId(bf.getLong());
        user.setName(bf.get());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && male == user.male && Double.compare(user.score, score) == 0 && Objects.equals(name, user.name) && Objects.equals(address, user.address) && Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, male, address, money, score);
    }
}
