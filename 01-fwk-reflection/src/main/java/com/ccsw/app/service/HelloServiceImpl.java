
package com.ccsw.app.service;

import com.ccsw.fwk.annotations.Component;

/**
 * @author coedevon
 *
 */
@Component("helloService")
public class HelloServiceImpl implements HelloService {

  /**
   * {@inheritDoc}
   */
  public void helloWorld() {

    System.out.println("Hello World!");
  }
}
