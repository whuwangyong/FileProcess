package cn.whu.wy.learnjava.swa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {
    @Autowired
    MyService myService;

    @Autowired
    Log log;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);


    /**
     * output:
     * 2022-05-26 10:00:00.953280600 http-nio-8080-exec-3 : sync start
     * 2022-05-26 10:00:00.958280900 http-nio-8080-exec-3 : do something...
     * 2022-05-26 10:00:02.958395300 http-nio-8080-exec-3 : done
     * 2022-05-26 10:00:02.958395300 http-nio-8080-exec-3 : sync end
     *
     * @return
     */
    @GetMapping("/hello")
    public String sayHello() {
        log.info("sync start");
        myService.doSomething();
        log.info("sync end");

        return "success";
    }

    /**
     * output:
     * 2022-05-26 10:00:26.142721400 http-nio-8080-exec-4 : async start
     * 2022-05-26 10:00:26.143721500 http-nio-8080-exec-4 : async end
     * 2022-05-26 10:00:26.144721500 pool-1-thread-1 : do something...
     * 2022-05-26 10:00:28.144835900 pool-1-thread-1 : done
     *
     * @return
     */
    @GetMapping("/hello-async")
    public DeferredResult<String> sayHelloAsync() {
        log.info("async start");
        DeferredResult<String> result = new DeferredResult<>();
        executorService.execute(() -> {
            myService.doSomething();
            result.setResult("ok");
        });


        log.info("async end");

        return result;
    }


}
