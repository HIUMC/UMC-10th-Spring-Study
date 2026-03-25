package hello.spring_hello.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceApp {

    public Object execute (ProceedingJoinPoint joinPoint) throws  Throwable{

        long start = System.currentTimeMillis();
        try{
            Object result = joinPoint.proceed();
            return result;
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish  - start;
        }
        System.out.println("START: " + joinPoint.toString());

    }
}
