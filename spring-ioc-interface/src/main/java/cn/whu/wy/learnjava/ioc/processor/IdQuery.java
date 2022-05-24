package cn.whu.wy.learnjava.ioc.processor;

import cn.whu.wy.learnjava.ioc.request.QueryById;
import org.springframework.stereotype.Component;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 12:26
 */
@Component
public class IdQuery implements Query<QueryById> {
    @Override
    public Object query(QueryById msg) {
        return "query by id=" + msg.getId();
    }
}
