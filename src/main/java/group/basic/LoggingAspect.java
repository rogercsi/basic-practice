package group.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

    @Before("execution(* group.basic.Mybean.testAop())")
    public void logBefore() {
        System.out.println("Before calling testAop()");
    }

    @After("execution(* group.basic.Mybean.testAop())")
    public void logAfter() {
        System.out.println("After calling testAop()");
    }
}