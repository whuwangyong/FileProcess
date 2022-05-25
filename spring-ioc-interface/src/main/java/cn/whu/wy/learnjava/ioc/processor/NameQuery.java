package cn.whu.wy.learnjava.ioc.processor;

import cn.whu.wy.learnjava.ioc.request.QueryByName;
import org.springframework.stereotype.Component;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 12:25
 */
@Component
public class NameQuery implements Query<QueryByName> {

    @Override
    public Object query(QueryByName msg) {
        return "query by name=" + msg.getName();
    }
}
