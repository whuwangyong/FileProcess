package cn.whu.wy.learnjava.ioc.processor;

import cn.whu.wy.learnjava.ioc.request.BaseMsg;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 12:23
 */
public interface Query<T extends BaseMsg> {
    Object query(T msg);
}
