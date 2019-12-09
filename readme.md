##用途
lombok最近更新版本提供了@SuperBulder注解。
sample目录下的类来自旧系统，使用的是父类全参构造器 + 叶子类@Builder注解来间接实现@SuperBuilder的效果。

运行Main程序，会将sample目录下的示例类，改为支持lombok @SuperBuilder的形式；
主要做法是删掉构造器、添加@SuperBuilder和相关import。

##注意
运行Main程序，会直接覆盖原.java文件！
