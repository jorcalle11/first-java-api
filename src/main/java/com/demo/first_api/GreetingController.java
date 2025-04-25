package com.demo.first_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
  // create a template for the greeting message
  private static final String template = "Hello, %s!";
  // create a counter to keep track of the number of greetings
  private final AtomicLong counter = new AtomicLong();
  
  
  // create a method to return a greeting message
  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    
    // return a new Greeting object with the id and content
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }
  
}