package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP 사용할 때 쓰는 annotation
@Component //이 annotation을 써서 빈에 등록할 수 있지만 특별한 거기때문에 보통 빈에 직접 등록해서 사용함.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 어디에 적용할 것인지. (패키지 하위에 다적용해라는 뜻)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
