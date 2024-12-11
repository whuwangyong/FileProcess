package cn.whu.wy.learnjava.base.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    @Before("execution(* cn.whu.wy.learnjava.base.aspectj.MyService.performAction(..))")
    public void beforePerformAction() {
        System.out.println("Before performing action...");
    }
}
