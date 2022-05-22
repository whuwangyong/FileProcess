package cn.whu.wy.query.processor;

import cn.whu.wy.query.request.QueryById;
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
