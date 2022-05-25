package cn.whu.wy.learnjava.lt.controller;

import cn.whu.wy.learnjava.lt.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

/**
 * @Author WangYong
 * @Date 2020/05/26
 * @Time 14:23
 */
@Controller
public class StudentController {

    // return name=wang; birth=2022-05-25 16:46:12
    @RequestMapping(method = RequestMethod.GET, path = "/student")
    public Object show(Model model) {
        Student student = new Student("wang", LocalDateTime.now());
        model.addAttribute("student", student);
        return "student-info-page";
    }
}
