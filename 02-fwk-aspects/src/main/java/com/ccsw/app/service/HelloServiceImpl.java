package com.ccsw.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.app.database.HelloRepository;
import com.ccsw.fwk.annotations.Logger;

@Service
public class HelloServiceImpl implements HelloService {

   @Autowired
   HelloRepository helloRepository;

   /**
    * {@inheritDoc}
    */
   @Override
   @Logger
   public void helloWorld() {

      String name = helloRepository.getName(1L);

      System.out.format("Hello %s !!\n", name);
   }

}
