package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

@SpringBootApplication
public class App
{
  public static void main(String[] args)
  {
    SpringApplication.run(App.class, args);
  }
  @Bean
  public PaginationInterceptor paginationInterceptor() {
      PaginationInterceptor page = new PaginationInterceptor();
      page.setDialectType("mysql");
      return page;
  }
}