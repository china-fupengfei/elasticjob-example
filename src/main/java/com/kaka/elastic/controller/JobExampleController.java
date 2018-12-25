package com.kaka.elastic.controller;

import com.kaka.elastic.job.JobExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobExampleController {

  @Autowired
  private JobExample jobExample;

  @RequestMapping(value="/doSomeThing", method = RequestMethod.GET)
  public void doSomeThing(){
    jobExample.doSomeThing();
  }
}
