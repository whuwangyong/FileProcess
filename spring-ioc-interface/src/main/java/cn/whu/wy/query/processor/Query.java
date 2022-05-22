package cn.whu.wy.query.processor;

import cn.whu.wy.query.request.BaseMsg;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 12:23
 */
public interface Query<T extends BaseMsg> {
    Object query(T msg);
}
