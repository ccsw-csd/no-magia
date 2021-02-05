
package com.capgemini.fwk.core;

import java.util.HashMap;

/**
 * @author coedevon
 *
 */
public class CacheComponents {

  HashMap<String, Object> cache = new HashMap<>();

  /**
   * Recupera un componente de la cache
   * @param name nombre del componente
   * @return null si no existe o el propio componente si existe
   */
  public Object getComponent(String name) {

    return this.cache.get(name);
  }

  /**
   * Guarda un componente en la cache
   * @param name nombre del componente
   * @param component componente a guardar
   */
  public void putComponent(String name, Object component) {

    System.out.println("Registrado el componente '" + name + "': " + component.getClass().getName());
    this.cache.put(name, component);
  }

}