package cn.whu.wy.learnjava.swa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    Log log;


//    @Async
    void doSomething()  {
        log.info("do something...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("done");

    }

}
