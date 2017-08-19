package microservicewithproxy.aop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LogManager.getLogger(LoggingAspect.class.getName());

    @Around("execution(* microservicewithproxy.controller..*(..))")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        return aroundBusinessDomain(joinPoint);
    }

     public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        return aroundBusinessDomain(joinPoint);
    }

    public Object aroundRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Before repository invocation");
        return aroundBusinessDomain(joinPoint);
    }

    private Object aroundBusinessDomain(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        StringBuffer invokedMethod = new StringBuffer();
        invokedMethod.append(joinPoint.getTarget().getClass().getName());
        invokedMethod.append(".");
        invokedMethod.append(joinPoint.getSignature().getName());
        invokedMethod.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            invokedMethod.append(args[i]).append(",");
        }
        if (args.length > 0) {
            invokedMethod.deleteCharAt(invokedMethod.length() - 1);
        }

        invokedMethod.append(")");

        logger.debug(invokedMethod.toString() + " - invoked");

        Object retVal = joinPoint.proceed();

        stopWatch.stop();

        invokedMethod.append(" execution time: ");
        invokedMethod.append(stopWatch.getTotalTimeMillis());
        invokedMethod.append(" ms");

        logger.debug(invokedMethod.toString());

        return retVal;

    }

}
