package com.jprofessionals.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.jprofessionals.di.core",
        "com.jprofessionals.di.gateway",
        "com.jprofessionals.di.web",
        "com.jprofessionals.di.persistence",
})
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
