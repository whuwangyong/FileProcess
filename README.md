
本项目是一些学习java语言的测试代码。包含多个模块，如无特殊说明，每个模块是单独的示例，可独立运行。

## base
一些java基本特性的测试：

- Exception
  当运行时异常未被catch时，会导致当前线程的终止。但不影响其他线程的正常运行。因此，可以考虑两个线程互相守护，发现对方不在了就重启一个。就像流氓APP。
  运行时异常，如空指针异常，是由于外部条件触发的。在不修改程序的情况下，当外部条件改变后，程序能正确运行。
- LocalDateTime 测试LocalDateTime.now()返回的精度，以及如何生成流水号
- Enum
- ClassName
- jdbc 不使用jdbcTemplate、jpa、Mybatis等等，使用原始的jdbc操作数据库
- lambda 表达式
- 多线程
- 继承与组合  
- utils.log：实现了一个简单的异步打印日志的方法。涉及到获取调用者的className、打印固定长度的字符串等技巧
- lock.LockTest：测试了synchronized block 提供的内置锁。wait()方法会释放锁，sleep()不会
- ...

## spring-ioc-interface
后端在提供服务时，如果一个interface有多个implementations，根据不同的参数类型，调用不同的implementation，应该怎么做。

Spring 可以自动注入该interface的各种实现，key为beanName，value为bean。

```java
@Autowired
Map<String, Query> queryMap;
```
### Reference:
1. [spring-boot-autowiring-an-interface-with-multiple-implementations](https://stackoverflow.com/questions/51766013/spring-boot-autowiring-an-interface-with-multiple-implementations)
2. [dynamic-dependency-injection-for-multiple-implementations-of-the-same-interface](https://stackoverflow.com/questions/53273923/dynamic-dependency-injection-for-multiple-implementations-of-the-same-interface)

## jackson-localdatetime
SpringBoot默认的json序列化使用的是jackson，该实例测试LocalDateTime在jackson中的处理

## spring-web-async
测试DeferredResult的特性。

注意`HelloController`里面的这两个方法，从日志可以看出，前者是直接在`http-nio-8080-exec`线程里面做的业务逻辑，
而使用`DeferredResult`之后，是在单独的线程池里面做的。

但是对前端来说，都是`doSomething()`执行完后才得到结果。
```java
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
```

