package com.ccsw.app;

import com.ccsw.app.service.HelloService;
import com.ccsw.fwk.annotations.Autowired;
import com.ccsw.fwk.core.FwkContext;

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
