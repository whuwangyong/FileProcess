package cn.whu.wy.query.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Author WangYong
 * Date 2022/05/22
 * Time 10:59
 */
@SuperBuilder
@Data
public class QueryById extends BaseMsg {
    int id;
}
