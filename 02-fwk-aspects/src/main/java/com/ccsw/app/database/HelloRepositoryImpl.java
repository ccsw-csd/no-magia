package com.ccsw.app.database;

import org.springframework.stereotype.Repository;

import com.ccsw.fwk.annotations.Logger;

@Repository
public class HelloRepositoryImpl implements HelloRepository {

   /**
   * {@inheritDoc}
   */
   @Override
   @Logger
   public String getName(Long id) {

      System.out.format("\tLanzamos query y recuperamos el nombre con ID: %s\n", id);
      return "World";
   }

}