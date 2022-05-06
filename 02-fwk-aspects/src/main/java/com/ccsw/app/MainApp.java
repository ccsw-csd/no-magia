package com.ccsw.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ccsw.app.service.HelloService;

@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan(basePackages = { "com.ccsw" })
public class MainApp implements CommandLineRunner {

   @Autowired
   HelloService helloService;

   public static void main(String[] args) {

      SpringApplication.run(MainApp.class, args);
   }

   @Override
   public void run(String... args) throws Exception {

      this.helloService.helloWorld();
   }

}
