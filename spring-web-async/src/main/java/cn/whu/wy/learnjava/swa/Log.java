package cn.whu.wy.learnjava.swa;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Log {
    public void info(String s) {
        System.out.println(LocalDateTime.now().toString().replace("T", " ") +
                " " + Thread.currentThread().getName() + " : " + s);
    }
}
