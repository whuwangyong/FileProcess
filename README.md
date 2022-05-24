[toc]

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
- ...

## spring-ioc-interface
后端在提供服务时，如果一个interface有多个implementations，根据不同的参数类型，调用不同的implementation，应该怎么做。

Spring 可以自动注入该interface的各种实现，key为beanName，value为bean。

```
@Autowired
Map<String, Query> queryMap;
```
### Reference:
1. [spring-boot-autowiring-an-interface-with-multiple-implementations](https://stackoverflow.com/questions/51766013/spring-boot-autowiring-an-interface-with-multiple-implementations)
2. [dynamic-dependency-injection-for-multiple-implementations-of-the-same-interface](https://stackoverflow.com/questions/53273923/dynamic-dependency-injection-for-multiple-implementations-of-the-same-interface)


