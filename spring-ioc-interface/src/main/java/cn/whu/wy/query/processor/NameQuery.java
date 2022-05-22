package cn.whu.wy.query.processor;

import cn.whu.wy.query.request.QueryByName;
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
