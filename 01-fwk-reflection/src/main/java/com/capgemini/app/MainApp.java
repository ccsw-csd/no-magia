package com.capgemini.app;

import com.capgemini.app.service.HelloService;
import com.capgemini.fwk.annotations.Autowired;
import com.capgemini.fwk.core.FwkContext;

/**
 * @author pajimene
 *
 */
public class MainApp {

  @Autowired("helloService")
  private HelloService helloService;

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {

    FwkContext.run(MainApp.class);

  }

  private void init() {

    this.helloService.helloWorld();
  }

}
