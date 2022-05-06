package com.ccsw.app.database;

public interface HelloRepository {

   /**
   * Recupera el nombre de la persona en base a un ID
   * @param id
   * @return
   */
   String getName(Long id);
}