这个很久之前的代码，我也不清楚当时想测试什么了。看起来是想测试**继承与组合**的区别。

Bird具备飞行的能力，Bird2通过继承Bird，使用父类的方法，具备了飞行的能力；
Bird3实现了飞行的接口，通过组合的方式将Bird的实例以构造函数的方式传递进来，具备了飞行的能力。

运行日志如下：
```
Bird is flying...
[inner] Fly time = 1000306226
[new] Fly time = 1046679450
---------------------------
Bird is flying...
[inner] Fly time = 999563669
[extend] Fly time = 999665634
[extend] Fly time = 1000243988
---------------------------
Bird is flying...
[inner] Fly time = 999459055
[implement] Fly time = 999612335
[implement] Fly time = 1000437324
---------------------------
```