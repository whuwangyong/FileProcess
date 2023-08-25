package cn.whu.wy.learnjava.base.testkafkaresend;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BizMsg {

    String bizType;
    int bizNo;

    public BizMsg(String bizType, int bizNo) {
        this.bizType = bizType;
        this.bizNo = bizNo;
    }

    @Override
    public String toString() {
        return bizType + "-" + bizNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizMsg bizMsg = (BizMsg) o;
        return bizNo == bizMsg.bizNo && Objects.equals(bizType, bizMsg.bizType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bizType, bizNo);
    }

    public static void main(String[] args) {
        List<BizMsg> a = new ArrayList<>();
        a.add(new BizMsg("A", 1));
        a.add(new BizMsg("A", 2));

        List<BizMsg> b = new ArrayList<>();
        b.addAll(a);
        System.out.println(b.size());
        a.clear();
        System.out.println(b.size());


    }
}
