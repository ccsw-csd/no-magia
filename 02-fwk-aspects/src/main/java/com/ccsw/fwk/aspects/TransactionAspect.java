package com.ccsw.fwk.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

   @Pointcut("within(@org.springframework.stereotype.Repository *)")
   public void repositoryAnnotation() {

   }

   @Pointcut("execution(public * *(..))")
   public void publicMethods() {

   }

   @Around("repositoryAnnotation() && publicMethods()")
   public Object aroundCall(ProceedingJoinPoint joinPoint) throws Throwable {

      openTransaction();

      Object proceed = joinPoint.proceed();

      closeTransaction();

      return proceed;
   }

   private void openTransaction() {

      System.out.println("Abrimos la transacción");
   }

   private void closeTransaction() {

      System.out.println("Cerramos la transacción");
   }

}
