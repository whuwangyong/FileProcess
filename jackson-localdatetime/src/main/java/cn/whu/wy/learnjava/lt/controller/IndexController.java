package cn.whu.wy.learnjava.lt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author WangYong
 * @Date 2020/05/26
 * @Time 09:22
 */
@RestController
public class IndexController {

    // return "2022-05-25 16:45:25"
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public Object hello() {
        return LocalDateTime.now();
    }

    // return 2022-05-25T16:45:30.331641700
    @RequestMapping(method = RequestMethod.GET, path = "/hello2")
    public String hello2() {
        return LocalDateTime.now().toString();
    }
}
