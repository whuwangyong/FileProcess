package cn.whu.wy.learnjava.ioc.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 10:57
 */
@SuperBuilder
@Data
public class BaseMsg {
    String seq;
}
