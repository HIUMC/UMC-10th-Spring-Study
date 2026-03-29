package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("start= " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long TimeMS = end - start;

            System.out.println("TimeMS = "+joinPoint.toString() + TimeMS);
        }
    }
}