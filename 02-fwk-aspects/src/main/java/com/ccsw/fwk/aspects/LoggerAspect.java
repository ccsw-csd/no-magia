package com.ccsw.fwk.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

   @Around("@annotation(com.ccsw.fwk.annotations.Logger)")
   public Object aroundCall(ProceedingJoinPoint joinPoint) throws Throwable {

      long startTime = System.currentTimeMillis();
      Object proceed = joinPoint.proceed();
      long endTime = System.currentTimeMillis();

      System.out.println("\t\t***Se ha invocado al método: " + joinPoint.getSignature().getName());
      System.out.println("\t\t***Con los argumentos: ");
      for (Object arg : joinPoint.getArgs()) {
         System.out.println("\t\t\t: " + arg.toString());
      }
      System.out.println("\t\t***Tiempo de ejecución: " + (endTime - startTime) + " ms");

      return proceed;
   }

}
