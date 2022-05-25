package cn.whu.wy.learnjava.lt.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author WangYong
 * @Date 2020/05/26
 * @Time 10:27
 */
@Data
@AllArgsConstructor
public class Student {
    private String name;
    private LocalDateTime birth;
}
